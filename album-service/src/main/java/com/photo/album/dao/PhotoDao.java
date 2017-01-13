package com.photo.album.dao;

import java.util.List;

import com.photo.album.model.Photo;

public interface PhotoDao {

	public List<Photo> getPhotosByType(Integer photoType);
	
}
