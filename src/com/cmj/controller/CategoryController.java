package com.cmj.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cmj.domain.Category;
import com.cmj.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping("/findAllCategory")
	public String findAllCategory(HttpServletRequest request){
		List<Category> parents = categoryService.findAllCategory();
		request.setAttribute("parents", parents);
		return "category/query";
	}
	
	
	
	
}
