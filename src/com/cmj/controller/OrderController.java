package com.cmj.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alipay.api.request.AlipayTradePagePayRequest;
import com.cmj.domain.Cartitem;
import com.cmj.domain.Order;
import com.cmj.domain.OrderItem;
import com.cmj.domain.User;
import com.cmj.util.getStringRandom;
import com.cmj.service.CartitemService;
import com.cmj.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService orderService;
	@Autowired
	CartitemService cartitemService;
	
	@RequestMapping("/myOrders")
    public String myOrders(@RequestParam(required=false,defaultValue="1")Integer page,
    @RequestParam(required=false,defaultValue="4")Integer rows,HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("sessionUser");
		PageHelper.startPage(page, rows);
		String url = getUrl(request);
		List<Order> findOrder = orderService.findOrderUser(user.getUid());
		PageInfo<Order> pageInfo = new PageInfo<Order>(findOrder);
		List<Order> orderList = pageInfo.getList();
		request.setAttribute("allOrder",orderList);
		request.setAttribute("pageInfo",pageInfo);
		request.setAttribute("url", url);
        return "order/list";
    }
	
	@RequestMapping("/createOrder")
    public String createOrder(String[] cartitemid,HttpServletRequest request){
		List<Cartitem> cartItemList = cartitemService.settleCart(cartitemid);
		if(cartItemList.size() == 0) {
			request.setAttribute("code", "error"); 
			request.setAttribute("msg", "您没有选择要购买的图书，无法下单！");
			return "order/Ordermsg";
		}
		Order order = new Order();
		order.setOid(getStringRandom.getNumber(30));//设置主键
		String date = (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date());
		order.setOrdertime(date);//下单时间
		order.setStatus("1");//设置状态，1表示未付款
		order.setAddress(request.getParameter("address"));
		User owner = (User)request.getSession().getAttribute("sessionUser");
		order.setUid(owner.getUid());
		BigDecimal total = new BigDecimal("0");
		for(Cartitem cartItem : cartItemList) {
			total = total.add(new BigDecimal(cartItem.getSubtotal() + ""));
		}
		order.setTotal(total.doubleValue());//设置总计
		
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		for(Cartitem cartItem : cartItemList) {
			OrderItem orderItem = new OrderItem();
			orderItem.setOrderitemid(getStringRandom.getNumber(30));//设置主键
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setBook(cartItem.getBook());
			orderItem.setOid(order.getOid());
			orderItemList.add(orderItem);
		}
		order.setOrderItems(orderItemList);
		
		orderService.createOrder(order);
		cartitemService.batchDelete(cartitemid);
		request.setAttribute("order", order);
        return "order/index";
    }
	
	@RequestMapping("/returnurl")
    public String returnurl(String payoid,HttpServletRequest request){
		orderService.updateStatus("2", payoid);
		return "redirect:/";
    }
	
	@RequestMapping("/seeOrder")
    public String seeOrder(String btn,String oid,HttpServletRequest request){
		Order findOrderOid = orderService.findOrderOid(oid);
		request.setAttribute("order", findOrderOid);
		request.setAttribute("btn", btn);
        return "order/desc";
    }
	
	@RequestMapping("/pay")
    public String pay(String oid,HttpServletRequest request){
		Order findOrderOid = orderService.findOrderOid(oid);
		request.setAttribute("order", findOrderOid);
        return "order/index";
    }
	
	@RequestMapping("/cancel")
    public String cancel(String oid,HttpServletRequest request){
		String findStatus = orderService.findStatus(oid);
		if(findStatus.equals("1")) {
			orderService.updateStatus("5",oid);
			request.setAttribute("code", "success");
			request.setAttribute("msg", "您的订单已取消！");
	        return "order/Ordermsg";
		}else {
			request.setAttribute("code", "error");
			request.setAttribute("msg", "状态不对，无法取消！");
			return "order/Ordermsg";
		}
    }
	
	@RequestMapping("/confirm")
    public String confirm(String oid,HttpServletRequest request){
		String findStatus = orderService.findStatus(oid);
		if(findStatus.equals("3")) {
			orderService.updateStatus("4",oid);
			request.setAttribute("code", "success");
			request.setAttribute("msg", "交易成功！");
	        return "order/Ordermsg";
		}else {
			request.setAttribute("code", "error");
			request.setAttribute("msg", "确认收货失败！");
			return "order/Ordermsg";
		}
    }
	
	@RequestMapping("/delete")
    public String delete(String oid,HttpServletRequest request){
		orderService.delete(oid);
		request.setAttribute("code", "success");
		request.setAttribute("msg", "您已删除该订单！");
        return "order/Ordermsg";
    }
	
	private String getUrl(HttpServletRequest request) {
		String url = request.getRequestURI() + "?" + request.getQueryString();
		int index = url.lastIndexOf("&page=");
		if(index != -1) {
			url = url.substring(0, index);
		}
		return url;
	}
	
}
