package com.cmj.service.Impl;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cmj.dao.UserMapper;
import com.cmj.domain.User;
import com.cmj.domain.UserExample;
import com.cmj.domain.UserExample.Criteria;
import com.cmj.service.UserService;
import com.cmj.util.getStringRandom;
import www.cmj.mail.Mail;
import www.cmj.mail.MailUtils;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	public List<User> findAll() {
		return userMapper.selectByExample(null);
	}

	public User ajaxLoginName(String loginname) {
		return userMapper.ajaxLoginName(loginname);

	}

	@Override
	public User ajaxEmail(String email) {
		return userMapper.ajaxEmail(email);
	}

	@Override
	public int regist(User user) {
		user.setUid(getStringRandom.getNumber(30));
		user.setStatus(false);
		user.setActivationcode(getStringRandom.getNumber(20));
		int insert = userMapper.insert(user);
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("email.properties"));
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}
		String host = prop.getProperty("host");// 服务器主机名
		String name = prop.getProperty("username");// 登录名
		String pass = prop.getProperty("password");// 登录密码
		Session session = MailUtils.createSession(host, name, pass);
		String from = prop.getProperty("from");
		String to = user.getEmail();
		String subject = prop.getProperty("subject");
		String content = MessageFormat.format(prop.getProperty("content"), user.getActivationcode());
		Mail mail = new Mail(from, to, subject, content);
		try {
			MailUtils.send(session, mail);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return insert;
	}

	@Override
	public void activatioin(String activationCode) throws Exception {
		User activatioin = userMapper.activatioin(activationCode);
		if (activatioin == null) {
			throw new Exception("无效的激活码！");
		}
		if (activatioin.getStatus()==true) {
			throw new Exception("该用户已激活！");
		}
		activatioin.setStatus(true);
		userMapper.updateByPrimaryKey(activatioin);
	}

	@Override
	public User login(User user) {
		return userMapper.selectUser(user);
	}

	@Override
	public void updatePassword(String uid, String newpass, String loginpass) throws Exception {
		UserExample userExample = new UserExample();
		Criteria createCriteria = userExample.createCriteria();
		createCriteria.andLoginpassEqualTo(loginpass);
		createCriteria.andUidEqualTo(uid);
		long countByExample = userMapper.countByExample(userExample);
		if (countByExample==0) {
			throw new Exception("旧密码错误！");
		}
		userMapper.updatePassUid(newpass,uid);
		
	}

	@Override
	public User forget(User user) {
		return userMapper.forget(user);
	}

	@Override
	public void forgetEmail(User user) {
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("forget.properties"));
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}
		String host = prop.getProperty("host");// 服务器主机名
		String name = prop.getProperty("username");// 登录名
		String pass = prop.getProperty("password");// 登录密码
		Session session = MailUtils.createSession(host, name, pass);
		String from = prop.getProperty("from");
		String to = user.getEmail();
		String subject = prop.getProperty("subject");
		String content = MessageFormat.format(prop.getProperty("content"), user.getActivationcode(),user.getLoginname(),user.getEmail(),user.getLoginpass());
		Mail mail = new Mail(from, to, subject, content);
		try {
			MailUtils.send(session, mail);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void forgetPawssord(User user) {
		userMapper.forgetPawssord(user);
	}

}
