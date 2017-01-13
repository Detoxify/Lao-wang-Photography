package com.photo.album.service.photo;

import java.util.List;

import com.photo.album.model.Photo;

public interface PhotoService {

	public List<Photo> getPhotosByType(Integer photoType);
	
}
