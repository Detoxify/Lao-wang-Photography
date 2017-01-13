package com.photo.album.service.photoType.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.photo.album.dao.PhotoTypeDao;
import com.photo.album.model.PhotoType;
import com.photo.album.service.photoType.PhotoTypeService;
@Service
public class PhotoTypeServiceImpl implements PhotoTypeService{

	@Autowired
	PhotoTypeDao photoTypeDao;
	
	@Override
	public List<PhotoType> getTypeList() {
		List<PhotoType> typeList = photoTypeDao.getTypeList();
		return typeList;
	}

	
}
