package com.photo.album.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.photo.album.model.PhotoType;
import com.photo.album.service.photoType.PhotoTypeService;

@Controller
public class HomeController {
	
	@Autowired
	PhotoTypeService photoTypeService;

	@RequestMapping("/home")
	public ModelAndView toHome(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		List<PhotoType> typeList =  photoTypeService.getTypeList();
		System.out.println("123");
		mv.addObject("typeList", typeList);
		return mv;
	}
	
	@RequestMapping("/portfolio")
	public String toPortfolio(){
		return "portfolio";
	}
	
	@RequestMapping("/about")
	public String toAbout(){
		return "about";
	}
	
	@RequestMapping("/contact")
	public String toContact(){
		return "contact";
	}
}
