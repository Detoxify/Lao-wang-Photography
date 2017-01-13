package com.photo.album.service.user;

import org.springframework.stereotype.Service;

import com.photo.album.model.User;


public interface UserService {

	public User getByName(String name);
	
}
