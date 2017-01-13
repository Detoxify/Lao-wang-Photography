package com.photo.album.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.HashSet;
import java.util.Set;

/**
 * redis工具类，通过本工具类使用redis连接池操作redis
 *
 */
public class RedisCache {

    private static final Logger logger = LoggerFactory.getLogger( RedisCache.class ) ;

    private static int expired = 60 * 60 * 6 ;

    private ShardedJedisPool manage ;

	/**
     * 获取数据
     * 
     * @param key
     * @return
     */
    public String get(String key){
        String value = null;

        ShardedJedis jedis = null;
        try {
            jedis = manage.getResource() ;
            value = jedis.get(key);
        } catch (Exception e) {
            //释放redis对象
            logger.error(" get key : {} error ." , key , e );
        } finally {
            //返还到连接池
            returnResource( manage, jedis );
        }
        return value;
    }
    
    /**
     * 删除数据
     * 
     * @param keys
     * @return
     */
    public Long del(String... keys){

        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = manage.getResource() ;
            for ( String key : keys ) {
                Jedis jedis = shardedJedis.getShard(key) ;
                jedis.del(key) ;
            }
        } catch (Exception e) {
            //释放redis对象
            logger.error(" get key : {} error ." , keys , e );
        } finally {
            //返还到连接池
            returnResource(manage,shardedJedis);
        }
        return 0L;
    }
    
    /**
     * 存入数据
     * 
     * @param key
     * @return
     */
    public String set(String key,String value){

        ShardedJedis jedis = null;
        try {
            jedis = manage.getResource() ;
            value = jedis.setex(key,expired,value);
        } catch (Exception e) {
            //释放redis对象
            logger.error(" set key : {} error ." , key , e );
        } finally {
            //返还到连接池
            returnResource(manage,jedis);
        }

        return value;
    }
    
    /**
     * 存入数据
     * 
     * @param key
     * @return
     */
    public String set(String key,int seconds,String value){

        ShardedJedis jedis = null;
        try {
            jedis = manage.getResource() ;
            value = jedis.setex(key,seconds,value);
        } catch (Exception e) {
            //释放redis对象
            logger.error(" get key : {} error ." , key , e );
        } finally {
            //返还到连接池
            returnResource(manage,jedis);
        }
        return value;
    }
    
    /**
     * set if not exist；如果key不存在，这设置成功，否则什么都不做
     * @param key
     * @param value
     * @param seconds
     * @return 设置成功放回1；否则返回0；-1表示出错
     */
    public long setnx(String key,String value,int seconds){

        ShardedJedis jedis = null;
        try {
            jedis = manage.getResource() ;
            long result = jedis.setnx(key, value);
            if(result == 1){
                jedis.expire(key, seconds);
            }
            return result;
        } catch (Exception e) {
            //释放redis对象
            manage.returnBrokenResource(jedis);
            logger.error(" get key : {} error ." , key , e );
        } finally {
            //返还到连接池
            returnResource(manage,jedis);
        }
        return -1;
    }

    public static void returnResource( ShardedJedisPool pool, ShardedJedis jedis ) {

        if ( null != jedis ) {
            pool.returnResource(jedis);
        }
    }
    
    /**
     * 向sets集合中加入元素
     * 
     * @param setsName
     * @return
     */
    public Long setAdd(String setsName,String element){

        ShardedJedis jedis = null;
        Long reply = 0L;
        try {
            jedis = manage.getResource() ;
            reply = jedis.sadd(setsName, element);
            jedis.expire(setsName, expired);
        } catch (Exception e) {
        	reply = -1l;
            //释放redis对象
            logger.error(" setAdd setsName:{}, {} error ." , setsName ,e );
        } finally {
            //返还到连接池
            returnResource(manage,jedis);
        }
        return reply;
    }
    /**
     * 存入数据
     * 
     * @param setsName
     * @return
     */
    public Long getSetSize(String setsName){

        ShardedJedis jedis = null;
        Long setSize = 0L ;
        try {
            jedis = manage.getResource() ;
            setSize = jedis.scard(setsName);
        } catch (Exception e) {
            //释放redis对象
        	setSize = -1l;
            logger.error(" get setSize exception : {} ."  , e );
        } finally {
            //返还到连接池
            returnResource(manage,jedis);
        }
        return setSize;
    }
    
    /**
     * 存入数据
     * 
     * @param key
     * @return
     */
    public Long incr(String setsName){

        ShardedJedis jedis = null;
        Long newValue = 0L ;
        try {
            jedis = manage.getResource() ;
            newValue = jedis.incr(setsName);
            jedis.expire(setsName, expired);
        } catch (Exception e) {
        	newValue = -1l;
            //释放redis对象
            logger.error(" get setSize exception : {} ."  , e );
        } finally {
            //返还到连接池
            returnResource(manage,jedis);
        }
        return newValue;
    }
	public Set<String> smembers(String setName) {

		ShardedJedis jedis = null;
		Set<String> set = new HashSet<String>();
		try {
		    jedis = manage.getResource() ;
		    set = jedis.smembers(setName);
		} catch (Exception e) {
		    //释放redis对象
			logger.error(" get set exception : {} ."  , e );
		} finally {
		    //返还到连接池
		    returnResource(manage,jedis);
		}
		return set;
	}

	/**
	 * @Description (将数组放入到redis中去)
	 * @param setsName
	 * @param array
	 */
	public void setArray(String setsName, String[] array) {

        ShardedJedis jedis = null;
        try {
            jedis = manage.getResource() ;
            jedis.sadd(setsName, array);
        } catch (Exception e) {
            //释放redis对象
            logger.error(" setArray setsName:{}, {} error ." , setsName ,e );
        } finally {
            //返还到连接池
            returnResource(manage,jedis);
        }
	}

    public ShardedJedisPool getManage() {
        return manage;
    }

    public void setManage(ShardedJedisPool manage) {
        this.manage = manage;
    }
}
