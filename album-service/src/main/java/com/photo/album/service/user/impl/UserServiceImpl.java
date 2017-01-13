package com.photo.album.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.photo.album.dao.UserDao;
import com.photo.album.model.User;
import com.photo.album.service.user.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;

	public User getByName(String name) {
		User user = userDao.getByName(name);
		return user;
	}

}
