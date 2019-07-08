package com.cmj.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmj.domain.Book;
import com.cmj.domain.Cartitem;
import com.cmj.domain.User;
import com.cmj.service.CartitemService;;

@Controller
@RequestMapping("/cart")
public class CartitemController {
	@Autowired
	CartitemService cartitemService;
	
	
	@RequestMapping("/myCart")
	public String myCart(HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("sessionUser");
		String uid = user.getUid();
		List<Cartitem> cartItemLIst = cartitemService.myCart(uid);
		request.setAttribute("cartItemList", cartItemLIst);
		return "cart/list";
	}
	
	@RequestMapping("/add")
	public String add(Cartitem cartitem,HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("sessionUser");
		if (user!=null) {
			String uid = user.getUid();
			cartitem.setUid(uid);
			cartitemService.add(cartitem);
			request.setAttribute("cartmsg", "添加购物车成功");
			return "redirect:/book/selectByBid?bid="+cartitem.getBid();
		}else {
			request.setAttribute("cartmsg", "您还没有登录！请先登录");
			return "redirect:/book/selectByBid?bid="+cartitem.getBid();
		}
	}
	

	@RequestMapping("/updateQuantity")
	@ResponseBody
    public Cartitem updateQuantity(String cartitemid,Integer quantity){
		Cartitem cartItem2 = cartitemService.updateQuantity(cartitemid, quantity);
		return cartItem2;
    }
	
	@RequestMapping("/batchDelete")
    public String batchDelete(String[] cartitemid,HttpServletRequest request){
		cartitemService.batchDelete(cartitemid);
		return myCart(request);
    }
	
	@RequestMapping("/settle")
    public String settle(String[] cartitemid,Double total,HttpServletRequest request){
		for (String cartstring : cartitemid) {
			Cartitem findtotal = cartitemService.findtotal(cartstring);
			if (findtotal.getQuantity()>findtotal.getBook().getTotal()) {
				request.setAttribute("cartmsg", findtotal.getBook().getBname());
				return myCart(request);
			}
		}
		List<Cartitem> settleCart = cartitemService.settleCart(cartitemid);
		request.setAttribute("cartItemList", settleCart);
		request.setAttribute("total", total);
		request.setAttribute("cartItemIds", Arrays.toString(cartitemid));
		return "cart/showitem";
    }
	
}
