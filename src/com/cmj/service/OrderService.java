package com.cmj.service;

import java.util.Date;
import java.util.List;

import com.cmj.domain.Order;

public interface OrderService {

	List<Order> findOrderUser(String uid);
	
	Order findOrderOid(String oid);

	String findStatus(String oid);

	void updateStatus(String i,String oid);

	void delete(String oid);

	void createOrder(Order order);

	List<Order> adminfindallorder();

	List<Order> adminOrderByFind(String oid, String ordertime, String status);
}
