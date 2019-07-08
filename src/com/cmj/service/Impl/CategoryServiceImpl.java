package com.cmj.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmj.service.CategoryService;
import com.cmj.dao.CategoryMapper;
import com.cmj.domain.Category;
import com.cmj.domain.CategoryExample;
import com.cmj.domain.CategoryExample.Criteria;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public List<Category> findAllCategory() {
		CategoryExample categoryExample = new CategoryExample();
		Criteria createCriteria = categoryExample.createCriteria();
		createCriteria.andPidIsNull();
		List<Category> parentList = categoryMapper.selectByExample(categoryExample);
		for (Category parent : parentList) {
			CategoryExample categoryExample2 = new CategoryExample();
			Criteria createCriteria2 = categoryExample2.createCriteria();
			createCriteria2.andPidEqualTo(parent.getCid());
			List<Category> children = categoryMapper.selectByExample(categoryExample2);
			parent.setChildren(children);
		}
		return parentList;
	}

	@Override
	public String findcname(String cname) {
		return categoryMapper.findcname(cname);
	}

	@Override
	public void add(Category parent) {
		categoryMapper.insert(parent);
	}

	@Override
	public int findChildrenCountByParent(String cid) {
		return categoryMapper.findChildrenCountByParent(cid);
	}

	@Override
	public void deleteCategory(String cid) {
		categoryMapper.deleteByPrimaryKey(cid);
	}

	@Override
	public void editCategory(Category category) {
		categoryMapper.updateByPrimaryKeySelective(category);
	}

	@Override
	public List<Category> findParents() {
		return categoryMapper.findParents();
	}


}
