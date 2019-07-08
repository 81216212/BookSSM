package com.cmj.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmj.util.VerifyCode;
import com.cmj.domain.User;
import com.cmj.service.UserService;

@Controller
@RequestMapping("/system")
public class SystemController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/VerifyCode")
	public void VerifyCode(HttpServletRequest request,HttpServletResponse response){
		VerifyCode vc = new VerifyCode();
		BufferedImage image = vc.getImage();
		try {
			VerifyCode.output(image, response.getOutputStream());
			request.getSession().setAttribute("vCode", vc.getText());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value="/ajaxLoginName",method=RequestMethod.POST)
	@ResponseBody
	public User ajaxLoginName(String loginname){
		User ajaxLoginname = userService.ajaxLoginName(loginname);
		return ajaxLoginname;
	}
	
	@RequestMapping(value="/ajaxEmail",method=RequestMethod.POST)
	@ResponseBody
	public User ajaxEmail(String email){
		User ajaxemail = userService.ajaxEmail(email);
		return ajaxemail;
	}
	
	@RequestMapping(value="/ajaxVerifyCode",method=RequestMethod.POST)
	@ResponseBody
	public boolean ajaxVerifyCode(HttpServletRequest request,String verifyCode){
		String vcode = (String) request.getSession().getAttribute("vCode");
		boolean b = verifyCode.equalsIgnoreCase(vcode);
		return b;
	}

	@RequestMapping(value="/regist",method=RequestMethod.POST)
	public String regist(User user,HttpServletRequest request){
		String verifyCode = user.getVerifyCode();
		String vcode = (String) request.getSession().getAttribute("vCode");
		if (verifyCode == null || verifyCode.trim().isEmpty()) {
			request.setAttribute("verifyCode", "验证码不能为空！");
			return jumpRegist();
		} else if (!verifyCode.equalsIgnoreCase(vcode)) {
			request.setAttribute("verifyCode", "验证码错误！");
			request.setAttribute("form", user);
			return jumpRegist();
		}
		userService.regist(user);
		request.setAttribute("code", "success");
		request.setAttribute("msg", "注册成功，请到邮箱激活！");
		return "system/msg";
	}
	
	@RequestMapping("/activation")
	public String activation(String activationCode,HttpServletRequest request,HttpServletResponse response){
		try {
			userService.activatioin(activationCode);
			request.setAttribute("msg", "激活成功！");
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
		}
		return "system/msg";
	}

	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(User user,HttpServletRequest request,HttpServletResponse response){
		String loginname = user.getLoginname();
		if (loginname == null || loginname.trim().isEmpty()) {
			request.setAttribute("errloginname", "用户名不能为空！");
			return jumpLogin();
		}
		String loginpass = user.getLoginpass();
		if (loginpass == null || loginpass.trim().isEmpty()) {
			request.setAttribute("errloginpass", "密码不能为空！");
			return jumpLogin();
		}
		String verifyCode = user.getVerifyCode();
		String vcode = (String) request.getSession().getAttribute("vCode");
		if (verifyCode == null || verifyCode.trim().isEmpty()) {
			request.setAttribute("errverifyCode", "验证码不能为空！");
			return jumpLogin();
		} else if (!verifyCode.equalsIgnoreCase(vcode)) {
			request.setAttribute("form", user);
			request.setAttribute("errverifyCode", "验证码错误！");
			return jumpLogin();
		}
		User login = userService.login(user);
		if (login == null) {
			request.setAttribute("msg", "用户名或密码错误！");
			request.setAttribute("user", user);
			return jumpLogin();
		} else {
			if (login.getStatus()!=true) {
				request.setAttribute("msg", "您还没有激活！");
				request.setAttribute("user", user);
				return jumpLogin();
			} else {
				// 保存用户到session
				request.getSession().setAttribute("sessionUser", login);
				// 获取用户名保存到cookie中
				String loginname2 = login.getLoginname();
				try {
					loginname = URLEncoder.encode(loginname2, "utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				Cookie cookie = new Cookie("loginname", loginname2);
				cookie.setMaxAge(60 * 60 * 24 * 10);// 保存10天
				response.addCookie(cookie);
				return jumpIndex();
			}
		}
	}
	
	@RequestMapping(value="/forget",method=RequestMethod.POST)
	public String forget(User user,HttpServletRequest request){
		String loginname = user.getLoginname();
		if (loginname == null || loginname.trim().isEmpty()) {
			request.setAttribute("errloginname", "用户名不能为空！");
			return jumpForget();
		}
		String loginpass = user.getLoginpass();
		if (loginpass == null || loginpass.trim().isEmpty()) {
			request.setAttribute("errloginpass", "密码不能为空！");
			return jumpForget();
		}
		String email = user.getEmail();
		if (email == null || email.trim().isEmpty()) {
			request.setAttribute("erremail", "邮箱不能为空！");
			return jumpForget();
		}
		String reloginpass = user.getReloginpass();
		if (reloginpass == null || reloginpass.trim().isEmpty()) {
			request.setAttribute("reloginpass", "确认密码不能为空！");
			return jumpForget();
		}
		String verifyCode = user.getVerifyCode();
		String vcode = (String) request.getSession().getAttribute("vCode");
		if (verifyCode == null || verifyCode.trim().isEmpty()) {
			request.setAttribute("errverifyCode", "验证码不能为空！");
			return jumpForget();
		} else if (!verifyCode.equalsIgnoreCase(vcode)) {
			request.setAttribute("form", user);
			request.setAttribute("errverifyCode", "验证码错误！");
			return jumpForget();
		}
		User forget = userService.forget(user);
		if (forget == null) {
			request.setAttribute("msg", "用户信息错误！");
			request.setAttribute("form", user);
			return jumpForget();
		} else {
			if (forget.getStatus()!=true) {
				request.setAttribute("msg", "您还没有激活！");
				request.setAttribute("form", user);
				return jumpForget();
			} else {
				user.setActivationcode(forget.getActivationcode());
				userService.forgetEmail(user);
				request.setAttribute("msg", "修改成功，请到邮箱确认修改密码");
				return jumpLogin();
			}
		}
	}
	
	@RequestMapping("/forgetCode")
	public String forgetCode(User user,HttpServletRequest request){
		try {
			userService.forgetPawssord(user);
			request.setAttribute("msg", "修改成功！");
		} catch (Exception e) {
			request.setAttribute("msg", "修改失败");
		}
		return "system/msg";
	}
	
	@RequestMapping("/updatePassword")
	public String updatePassword(User user,HttpServletRequest request){
		String verifyCode = user.getVerifyCode();
		String vcode = (String) request.getSession().getAttribute("vCode");
		if (verifyCode == null || verifyCode.trim().isEmpty()) {
			request.setAttribute("errverifyCode", "验证码不能为空！");
			return "system/pwd";
		} else if (!verifyCode.equalsIgnoreCase(vcode)) {
			request.setAttribute("user", user);
			request.setAttribute("errverifyCode", "验证码错误！");
			return "system/pwd";
		}
		User user2 = (User) request.getSession().getAttribute("sessionUser");
		if (user2 == null) {
			request.setAttribute("msg", "您还没有登录！");
			return jumpLogin();
		}
		try {
			userService.updatePassword(user2.getUid(),user.getNewpass(),user.getLoginpass());
			request.setAttribute("msg", "修改密码成功");
			request.setAttribute("code", "success");
			request.getSession().invalidate();//销毁用户Session
			return "system/pwdmsg";
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());// 保存异常信息到request
			request.setAttribute("user", user);// 为了回显
			return "system/pwd";
		}
		
	}
	
	@RequestMapping("/quit")
	public String quit(HttpServletRequest request){
		request.getSession().invalidate();
		return jumpIndex();
	}
	
	@RequestMapping("/jumpIndex")
	public String jumpIndex(){
		return "redirect:/index.jsp";
	}
	
	@RequestMapping("/jumpRegist")
	public String jumpRegist(){
		return "system/regist";
	}

	@RequestMapping("/jumpLogin")
	public String jumpLogin(){
		return "system/login";
	}
	
	@RequestMapping("/jumpForget")
	public String jumpForget(){
		return "system/forget";
	}
	
}
