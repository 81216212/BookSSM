package com.cmj.service;

import java.util.List;

import com.cmj.domain.User;

public interface UserService {
	
	public List<User> findAll();
	
	public User ajaxLoginName(String loginname);

	public User ajaxEmail(String email);

	public int regist(User user);

	public void activatioin(String activationCode) throws Exception;

	public User login(User user);

	public void updatePassword(String uid, String newpass, String loginpass) throws Exception;

	public User forget(User user);

	public void forgetPawssord(User user);

	public void forgetEmail(User user);
	
}
