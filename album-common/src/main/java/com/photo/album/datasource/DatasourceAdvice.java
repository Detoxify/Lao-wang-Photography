package com.photo.album.datasource;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Created by nathaniel on 16/8/18.
 */
public class DatasourceAdvice implements MethodInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(DatasourceAdvice.class) ;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        Method method = invocation.getMethod() ;
        String methodName = method.getName() ;
        try {
            if(methodName.startsWith("get")){
                CustomerContextHolder.setCustomerType("SLAVE");
            } else  {
                CustomerContextHolder.setCustomerType("MASTER");
            }
            Object result = invocation.proceed();
            logger.info("point cut class = {} , and method = {} , and db = {}",
                    new Object[]{ method.getDeclaringClass(),methodName,CustomerContextHolder.getCustomerType() }  );
            return result ;
        } finally {
            logger.info("clear context holder.");
            CustomerContextHolder.clearCustomerType();
        }
    }
}
