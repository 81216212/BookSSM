package com.cmj.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmj.dao.OrderMapper;
import com.cmj.domain.Order;
import com.cmj.domain.OrderExample;
import com.cmj.domain.OrderExample.Criteria;
import com.cmj.domain.OrderItem;
import com.cmj.domain.OrderItemExample;
import com.cmj.service.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService{
	@Autowired
	OrderMapper ordermapper;
	
	@Override
	public List<Order> findOrderUser(String uid) {
		List<Order> findOrder = ordermapper.findOrderUser(uid);
		for (Order order : findOrder) {
			List<OrderItem> myOrderitem = ordermapper.myOrderitem(order.getOid());
			order.setOrderItems(myOrderitem);
		}
		return findOrder;
	}

	@Override
	public Order findOrderOid(String oid) {
		Order findOrderOid = ordermapper.findOrderOid(oid);
		List<OrderItem> myOrderitem = ordermapper.myOrderitem(findOrderOid.getOid());
		findOrderOid.setOrderItems(myOrderitem);
		return findOrderOid;
	}

	@Override
	public String findStatus(String oid) {
		return ordermapper.findStatus(oid);
		
	}

	@Override
	public void updateStatus(String i,String oid) {
		ordermapper.updateStatus(i,oid);
	}

	@Override
	public void delete(String oid) {
		ordermapper.delete(oid);
	}

	@Override
	public void createOrder(Order order) {
		ordermapper.createOrder(order);
		List<OrderItem> orderItems = order.getOrderItems();
		ordermapper.createOrderitem(orderItems);
	}

	@Override
	public List<Order> adminfindallorder() {
		List<Order> adminfindallorder = ordermapper.adminfindallorder();
		for (Order order : adminfindallorder) {
			List<OrderItem> myOrderitem = ordermapper.myOrderitem(order.getOid());
			order.setOrderItems(myOrderitem);
		}
		return adminfindallorder;
	}

	@Override
	public List<Order> adminOrderByFind(String oid, String ordertime, String status) {
		oid="%"+oid+"%";
		ordertime="%"+ordertime+"%";
		status="%"+status+"%";
		List<Order> adminfindbyfind = ordermapper.adminOrderByFind(oid, ordertime, status);
		for (Order order : adminfindbyfind) {
			List<OrderItem> myOrderitem = ordermapper.myOrderitem(order.getOid());
			order.setOrderItems(myOrderitem);
		}
		return adminfindbyfind;
	}

}
