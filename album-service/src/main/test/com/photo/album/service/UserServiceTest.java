package com.photo.album.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.photo.album.model.PhotoType;
import com.photo.album.service.photoType.impl.PhotoTypeServiceImpl;

public class UserServiceTest {

	private ApplicationContext applicationContext;
	
	@Before
	public void setUp(){
		applicationContext = new FileSystemXmlApplicationContext("classpath:spring/applicationContext.xml");
	}
	
	@Test
	public void testGet(){
		PhotoTypeServiceImpl service = (PhotoTypeServiceImpl)applicationContext.getBean("PhotoTypeServiceImpl");
		List<PhotoType> list = service.getTypeList();
		for(PhotoType type : list){
			System.out.println(type.getTypeName());
		}
	}
}
