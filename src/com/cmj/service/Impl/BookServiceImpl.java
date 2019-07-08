package com.cmj.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmj.dao.BookMapper;
import com.cmj.domain.Book;
import com.cmj.domain.BookExample;
import com.cmj.domain.BookExample.Criteria;
import com.cmj.service.BookService;

@Service("bookService")
public class BookServiceImpl implements BookService {

	@Autowired
	private BookMapper bookService;

	@Override
	public List<Book> findAllBook(String cid) {
		BookExample bookExample = new BookExample();
		Criteria createCriteria = bookExample.createCriteria();
		createCriteria.andCidEqualTo(cid);
		return bookService.selectByExample(bookExample);
		
	}

	@Override
	public Book selectByBid(String bid) {
		return bookService.selectByPrimaryKey(bid);
	}

	@Override
	public List<Book> FindBook(String bname,String author,String press) {
		BookExample bookExample = new BookExample();
		Criteria createCriteria = bookExample.createCriteria();
		createCriteria.andPressLike("%"+press+"%");
		createCriteria.andAuthorLike("%"+author+"%");
		createCriteria.andBnameLike("%"+bname+"%");
		return bookService.selectByExample(bookExample);
	}

	@Override
	public List<Book> adminfindallbook() {
		return bookService.selectByExample(null);
	}

	@Override
	public int findBookCountByCategory(String cid) {
		return bookService.findBookCountByCategory(cid);
	}

	@Override
	public List<Book> adminSearchBook(String bname) {
		BookExample bookExample = new BookExample();
		Criteria createCriteria = bookExample.createCriteria();
		createCriteria.andBnameLike("%"+bname+"%");
		return bookService.selectByExample(bookExample);
	}

	@Override
	public void add(Book book) {
		bookService.insert(book);
	}

	@Override
	public void adminDeleteBook(String bid) {
		bookService.deleteByPrimaryKey(bid);
	}

	@Override
	public Book adminLoadBook(String bid) {
		return bookService.adminLoadBook(bid);
	}

	@Override
	public void adminEditBook(Book book) {
		bookService.updateByPrimaryKeySelective(book);
	}


}
