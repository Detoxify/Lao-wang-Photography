package com.photo.album.service.photo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.photo.album.cache.RedisCache;
import com.photo.album.dao.PhotoDao;
import com.photo.album.model.Photo;
import com.photo.album.service.photo.PhotoService;

@Service
public class PhotoServiceImpl implements PhotoService{

	@Autowired
	PhotoDao photoDao;
	@Autowired
	RedisCache redisCache;
	
	@Override
	public List<Photo> getPhotosByType(Integer photoType) {
		List<Photo> photos = photoDao.getPhotosByType(photoType);
		redisCache.set("key", "value");
		return photos;
	}

}
