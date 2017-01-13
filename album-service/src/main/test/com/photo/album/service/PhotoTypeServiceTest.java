package com.photo.album.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.photo.album.model.User;
import com.photo.album.service.user.impl.UserServiceImpl;

public class PhotoTypeServiceTest {

	private ApplicationContext applicationContext;
	
	@Before
	public void setUp(){
		applicationContext = new FileSystemXmlApplicationContext("classpath:spring/applicationContext.xml");
	}
	
	@Test
	public void testGet(){
		UserServiceImpl service = (UserServiceImpl)applicationContext.getBean("UserServiceImpl");
		User user = service.getByName("laowang");
		String name = user.getPassword();
		System.out.println(name);
	}
}
