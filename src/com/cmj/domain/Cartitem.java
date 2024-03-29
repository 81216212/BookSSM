package com.cmj.domain;

import java.math.BigDecimal;

public class Cartitem {
	private Book book;// 条目对应的图书
	private User user;
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public double getSubtotal() {
		BigDecimal b1 = new BigDecimal(book.getCurrprice() + "");
		BigDecimal b2 = new BigDecimal(quantity + "");
		BigDecimal b3 = b1.multiply(b2);
		return b3.doubleValue();
	}
	/**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cartitem.cartItemId
     *
     * @mbg.generated Mon Jun 10 07:59:08 CST 2019
     */
    private String cartitemid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cartitem.quantity
     *
     * @mbg.generated Mon Jun 10 07:59:08 CST 2019
     */
    private Integer quantity;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cartitem.bid
     *
     * @mbg.generated Mon Jun 10 07:59:08 CST 2019
     */
    private String bid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cartitem.uid
     *
     * @mbg.generated Mon Jun 10 07:59:08 CST 2019
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cartitem.orderBy
     *
     * @mbg.generated Mon Jun 10 07:59:08 CST 2019
     */
    private Integer orderby;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cartitem.cartItemId
     *
     * @return the value of cartitem.cartItemId
     *
     * @mbg.generated Mon Jun 10 07:59:08 CST 2019
     */
    public String getCartitemid() {
        return cartitemid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cartitem.cartItemId
     *
     * @param cartitemid the value for cartitem.cartItemId
     *
     * @mbg.generated Mon Jun 10 07:59:08 CST 2019
     */
    public void setCartitemid(String cartitemid) {
        this.cartitemid = cartitemid == null ? null : cartitemid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cartitem.quantity
     *
     * @return the value of cartitem.quantity
     *
     * @mbg.generated Mon Jun 10 07:59:08 CST 2019
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cartitem.quantity
     *
     * @param quantity the value for cartitem.quantity
     *
     * @mbg.generated Mon Jun 10 07:59:08 CST 2019
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cartitem.bid
     *
     * @return the value of cartitem.bid
     *
     * @mbg.generated Mon Jun 10 07:59:08 CST 2019
     */
    public String getBid() {
        return bid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cartitem.bid
     *
     * @param bid the value for cartitem.bid
     *
     * @mbg.generated Mon Jun 10 07:59:08 CST 2019
     */
    public void setBid(String bid) {
        this.bid = bid == null ? null : bid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cartitem.uid
     *
     * @return the value of cartitem.uid
     *
     * @mbg.generated Mon Jun 10 07:59:08 CST 2019
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cartitem.uid
     *
     * @param uid the value for cartitem.uid
     *
     * @mbg.generated Mon Jun 10 07:59:08 CST 2019
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cartitem.orderBy
     *
     * @return the value of cartitem.orderBy
     *
     * @mbg.generated Mon Jun 10 07:59:08 CST 2019
     */
    public Integer getOrderby() {
        return orderby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cartitem.orderBy
     *
     * @param orderby the value for cartitem.orderBy
     *
     * @mbg.generated Mon Jun 10 07:59:08 CST 2019
     */
    public void setOrderby(Integer orderby) {
        this.orderby = orderby;
    }
}