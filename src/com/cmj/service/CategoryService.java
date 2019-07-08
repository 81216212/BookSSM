package com.cmj.service;

import java.util.List;

import com.cmj.domain.Category;

public interface CategoryService {

	List<Category> findAllCategory();

	String findcname(String cname);

	void add(Category parent);

	int findChildrenCountByParent(String cid);

	void deleteCategory(String cid);

	void editCategory(Category category);

	List<Category> findParents();


}
