package com.photo.album.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.photo.album.model.Photo;
import com.photo.album.service.photo.PhotoService;

@Controller
public class PhotoController {
	
	@Autowired
	PhotoService photoService;

	@RequestMapping("/Nature")
	public String showNature(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("nature");
		List<Photo> photos = photoService.getPhotosByType(1);
		mv.addObject("photos", photos);
		return "nature";
	}
	
	
}
