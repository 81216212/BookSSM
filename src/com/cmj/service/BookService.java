package com.cmj.service;

import java.util.List;

import com.cmj.domain.Book;

public interface BookService {

	List<Book> findAllBook(String cid);

	Book selectByBid(String bid);

	List<Book> FindBook(String bname,String author,String press);

	List<Book> adminfindallbook();

	int findBookCountByCategory(String cid);

	List<Book> adminSearchBook(String bname);

	void add(Book book);

	void adminDeleteBook(String bid);

	Book adminLoadBook(String bid);

	void adminEditBook(Book book);
	
}
