package com.cmj.service.Impl;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmj.dao.AdminMapper;
import com.cmj.dao.UserMapper;
import com.cmj.domain.Admin;
import com.cmj.domain.User;
import com.cmj.domain.UserExample;
import com.cmj.domain.UserExample.Criteria;
import com.cmj.service.AdminService;


@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public Admin login(Admin admin) {
		return adminMapper.login(admin.getAdminname(),admin.getAdminpwd());
	}

	@Override
	public long ajaxUser() {
		return userMapper.countByExample(null);
	}

	@Override
	public List<User> finduser() {
		return userMapper.selectByExample(null);
	}

	@Override
	public void userActivation(String uid) throws Exception{
		userMapper.userActivation(true,uid);
	}

	@Override
	public void userDelete(String uid) throws Exception{
		userMapper.deleteByPrimaryKey(uid);
	}
	
	
	
}
