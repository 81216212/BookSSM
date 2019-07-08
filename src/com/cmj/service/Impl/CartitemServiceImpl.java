package com.cmj.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmj.dao.CartitemMapper;
import com.cmj.domain.Cartitem;
import com.cmj.domain.CartitemExample;
import com.cmj.domain.CartitemExample.Criteria;
import com.cmj.service.CartitemService;
import com.cmj.util.getStringRandom;


@Service("cartitemService")
public class CartitemServiceImpl implements CartitemService {

	@Autowired
	private CartitemMapper cartitemMapper;

	public List<Cartitem> myCart(String uid) {
		return cartitemMapper.myCart(uid);
	}

	@Override
	public Cartitem findtotal(String cartstring) {
		return cartitemMapper.findtotal(cartstring);
	}

	@Override
	public void add(Cartitem cartitem) {
		Cartitem findByUidBid = cartitemMapper.findByUidBid(cartitem);
		if (findByUidBid==null) {
			cartitem.setCartitemid(getStringRandom.getNumber(20));
			cartitemMapper.insert(cartitem);
		}else {
			int quantity = cartitem.getQuantity() + findByUidBid.getQuantity();
			cartitemMapper.updateQuantity(quantity,findByUidBid.getCartitemid());
		}
		
	}

	@Override
	public Cartitem updateQuantity(String cartitemid, Integer quantity) {
		cartitemMapper.updateQuantity(quantity,cartitemid);
		return cartitemMapper.findByCartItemId(cartitemid);
	}

	@Override
	public void batchDelete(String[] cartitemid) {
		cartitemMapper.batchDelete(cartitemid);
	}

	@Override
	public List<Cartitem> settleCart(String[] cartitemid) {
		return cartitemMapper.settleCart(cartitemid);
	}

	

}
