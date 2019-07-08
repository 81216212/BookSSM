package com.cmj.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cmj.domain.Book;
import com.cmj.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/book")
public class BookController {
	@Autowired
	BookService bookService;
	
	@RequestMapping("/bookByPage")
	public String bookByPage(@RequestParam(required=false,defaultValue="1")Integer page,
	@RequestParam(required=false,defaultValue="8")Integer rows,String cid,Model model,HttpServletRequest request){
		PageHelper.startPage(page, rows);
		String url = getUrl(request);
		List<Book> allBook = bookService.findAllBook(cid);
		PageInfo<Book> pageInfo = new PageInfo<Book>(allBook);
		List<Book> bookList = pageInfo.getList();
		model.addAttribute("allBook",bookList);
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("url", url);
		return "book/list";
	}
	
	@RequestMapping("/FindBook")
	public String FindBook(@RequestParam(required=false,defaultValue="1")Integer page,
	@RequestParam(required=false,defaultValue="8")Integer rows,String bname,String author,String press,Model model,HttpServletRequest request){
		PageHelper.startPage(page, rows);
		String url = getUrl(request);
		List<Book> allBook = bookService.FindBook(bname,author,press);
		PageInfo<Book> pageInfo = new PageInfo<Book>(allBook);
		List<Book> bookList = pageInfo.getList();
		model.addAttribute("allBook",bookList);
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("url", url);
		return "book/list";
	}
	
	@RequestMapping("/selectByBid")
	public String selectByBid(String bid,Model model) {
		Book selectByBid = bookService.selectByBid(bid);
		model.addAttribute("book", selectByBid);
		return "book/desc";
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
