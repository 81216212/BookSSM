package com.cmj.service;

import java.util.List;

import com.cmj.domain.Cartitem;


public interface CartitemService {

	List<Cartitem> myCart(String uid);

	Cartitem findtotal(String cartstring);

	void add(Cartitem cartitem);

	Cartitem updateQuantity(String cartitemid, Integer quantity);

	void batchDelete(String[] cartitemid);

	List<Cartitem> settleCart(String[] cartitemid);

	

}
