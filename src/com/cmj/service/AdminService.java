package com.cmj.service;

import java.util.List;

import com.cmj.domain.Admin;
import com.cmj.domain.Book;
import com.cmj.domain.User;

public interface AdminService {

	Admin login(Admin admin);

	long ajaxUser();

	List<User> finduser();

	void userActivation(String uid) throws Exception;

	void userDelete(String uid) throws Exception;

}
