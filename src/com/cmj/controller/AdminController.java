package com.cmj.controller;

import java.awt.Image;
import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.cmj.domain.Admin;
import com.cmj.domain.Book;
import com.cmj.domain.Category;
import com.cmj.domain.Order;
import com.cmj.domain.User;
import com.cmj.service.AdminService;
import com.cmj.service.BookService;
import com.cmj.service.CategoryService;
import com.cmj.service.OrderService;
import com.cmj.util.getStringRandom;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	BookService bookService;
	@Autowired
	OrderService orderService;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(Admin admin,HttpServletRequest request,HttpServletResponse response){
		Admin adminlogin = adminService.login(admin);
		if(adminlogin == null) {
			request.setAttribute("msg", "用户名或密码错误！");
			return index();
		}
		request.getSession().setAttribute("admin", admin);
		return "admin/index";
	}
	
	@RequestMapping("/findAllCategory")
	public String findAllCategory(HttpServletRequest request,HttpServletResponse response){
		List<Category> parents = categoryService.findAllCategory();
		request.setAttribute("parents", parents);
		return "admin/left";
	}
	
	@RequestMapping("/adminTopGj")
	public String adminTopGj(@RequestParam(required=false,defaultValue="1")Integer page,
	@RequestParam(required=false,defaultValue="8")Integer rows,String bname,String author,String press,Model model,HttpServletRequest request){
		PageHelper.startPage(page, rows);
		String url = getUrl(request);
		List<Book> allBook = bookService.FindBook(bname,author,press);
		PageInfo<Book> pageInfo = new PageInfo<Book>(allBook);
		List<Book> bookList = pageInfo.getList();
		model.addAttribute("allBook",bookList);
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("url", url);
		return "backbook/main";
	}
	
	@RequestMapping("/adminBook")
	public String adminBook(@RequestParam(required=false,defaultValue="1")Integer page,
	@RequestParam(required=false,defaultValue="8")Integer rows,String cid,Model model,HttpServletRequest request){
		PageHelper.startPage(page, rows);
		String url = getUrl(request);
		List<Book> allBook = bookService.findAllBook(cid);
		PageInfo<Book> pageInfo = new PageInfo<Book>(allBook);
		List<Book> bookList = pageInfo.getList();
		model.addAttribute("allBook",bookList);
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("url", url);
		return "backbook/list";
	}
	
	@RequestMapping("/adminFindBook")
	public String adminFindBook(@RequestParam(required=false,defaultValue="1")Integer page,
	@RequestParam(required=false,defaultValue="8")Integer rows,String bname,String author,String press,Model model,HttpServletRequest request){
		PageHelper.startPage(page, rows);
		String url = getUrl(request);
		List<Book> allBook = bookService.FindBook(bname,author,press);
		PageInfo<Book> pageInfo = new PageInfo<Book>(allBook);
		List<Book> bookList = pageInfo.getList();
		model.addAttribute("allBook",bookList);
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("url", url);
		return "backbook/list";
	}
	
	@RequestMapping("/adminLoadBook")
	public String adminLoadBook(String bid,HttpServletRequest request){
		Book adminLoadBook = bookService.adminLoadBook(bid);
		List<Category> parents = categoryService.findAllCategory();
		request.setAttribute("parents", parents);
		request.setAttribute("book", adminLoadBook);
		return "backbook/desc";
	}
	
	@RequestMapping("/adminEditBook")
	public String adminEditBook(Book book,HttpServletRequest request){
		bookService.adminEditBook(book);
		return adminfindallbook(1, 10, request);
	}
	
	@RequestMapping("/adminDeleteBook")
	public String adminDeleteBook(String bid,HttpServletRequest request){
		bookService.adminDeleteBook(bid);
		return adminfindallbook(1, 10, request);
	}
	
	@RequestMapping("/adminfindallbook")
	public String adminfindallbook(@RequestParam(required=false,defaultValue="1")Integer page,
	@RequestParam(required=false,defaultValue="10")Integer rows,HttpServletRequest request){
		PageHelper.startPage(page, rows);
		String url = getUrl(request);
		List<Book> allBook = bookService.adminfindallbook();
		PageInfo<Book> pageInfo = new PageInfo<Book>(allBook);
		List<Book> bookList = pageInfo.getList();
		request.setAttribute("allBook",bookList);
		request.setAttribute("pageInfo",pageInfo);
		request.setAttribute("url", url);
		return "backbook/main";
	}
	
	@RequestMapping("/adminfindallorder")
	public String adminfindallorder(@RequestParam(required=false,defaultValue="1")Integer page,
	@RequestParam(required=false,defaultValue="5")Integer rows,HttpServletRequest request){
		PageHelper.startPage(page, rows);
		String url = getUrl(request);
		List<Order> allBook = orderService.adminfindallorder();
		PageInfo<Order> pageInfo = new PageInfo<Order>(allBook);
		List<Order> orderList = pageInfo.getList();
		request.setAttribute("allOrder",orderList);
		request.setAttribute("pageInfo",pageInfo);
		request.setAttribute("url", url);
		return "backorder/list";
	}
	
	@RequestMapping("/adminOrderByFind")
	public String adminOrderByFind(@RequestParam(required=false,defaultValue="1")Integer page,
	@RequestParam(required=false,defaultValue="5")Integer rows,Order order,HttpServletRequest request){
		PageHelper.startPage(page, rows);
		String url = getUrl(request);
		List<Order> allBook = orderService.adminOrderByFind(order.getOid(),order.getOrdertime(),order.getStatus());
		PageInfo<Order> pageInfo = new PageInfo<Order>(allBook);
		List<Order> orderList = pageInfo.getList();
		request.setAttribute("ordertime",order.getOrdertime());
		request.setAttribute("oid",order.getOid());
		request.setAttribute("allOrder",orderList);
		request.setAttribute("pageInfo",pageInfo);
		request.setAttribute("url", url);
		return "backorder/list";
	}
	
	@RequestMapping("/adminDeleteOrder")
    public String adminDeleteOrder(String oid,HttpServletRequest request){
		orderService.delete(oid);
        return adminfindallorder(1, 5, request);
    }
	
	@RequestMapping("/adminLoadOrder")
    public String adminLoadOrder(String oid,HttpServletRequest request){
		Order findOrderOid = orderService.findOrderOid(oid);
		request.setAttribute("order", findOrderOid);
		return "backorder/desc";
    }
	
	@RequestMapping("/adminCancelOrder")
    public String adminCancelOrder(String oid,HttpServletRequest request){
		String findStatus = orderService.findStatus(oid);
		if(findStatus.equals("1")) {
			orderService.updateStatus("5", oid);
			request.setAttribute("ordermsg", "您的订单已取消！");
			return adminfindallorder(1, 5, request);
		}else {
			request.setAttribute("ordermsg", "状态不对，无法取消！");
			return adminfindallorder(1, 5, request);
		}
    }
	
	@RequestMapping("/adminDeliverOrder")
    public String adminDeliverOrder(String oid,HttpServletRequest request){
		String findStatus = orderService.findStatus(oid);
		if(findStatus.equals("2")) {
			orderService.updateStatus("3", oid);
			request.setAttribute("ordermsg", "您的订单已发货，请查看物流！");
			return adminfindallorder(1, 5, request);
		}else {
			request.setAttribute("ordermsg", "状态不对，无法发货！");
			return adminfindallorder(1, 5, request);
		}
    }
	
	
	@RequestMapping("/finduser")
	public String finduser(@RequestParam(required=false,defaultValue="1")Integer page,
	@RequestParam(required=false,defaultValue="10")Integer rows,HttpServletRequest request){
		PageHelper.startPage(page, rows);
		String url = getUrl(request);
		List<User> allUser = adminService.finduser();
		PageInfo<User> pageInfo = new PageInfo<User>(allUser);
		List<User> userList = pageInfo.getList();
		request.setAttribute("allUser",userList);
		request.setAttribute("pageInfo",pageInfo);
		request.setAttribute("url", url);
		return "backuser/user";
	}
	
	@RequestMapping("/ajaxUser")
	@ResponseBody
	public long ajaxUser(){
		long ajaxUser = adminService.ajaxUser();
		return ajaxUser;
	}
	
	@RequestMapping("/userDelete")
	public String userDelete(String uid,HttpServletRequest request){
		try {
			adminService.userDelete(uid);
			request.setAttribute("success", "删除成功");
		} catch (Exception e) {
			request.setAttribute("success", "删除失败");
		}
		return finduser(1,10,request);
	}
	
	@RequestMapping("/userActivation")
	public String userActivation(String uid,HttpServletRequest request){
		try {
			adminService.userActivation(uid);
			request.setAttribute("success", "恭喜，激活成功！");
		} catch (Exception e) {
			request.setAttribute("success", "激活失败！");
		}
		return finduser(1,10,request);
	}
	
	@RequestMapping("/adminCategoryAll")
	public String adminCategoryAll(HttpServletRequest request){
		request.setAttribute("parents", categoryService.findAllCategory());
		return "backcategory/list";
	}
	
	@RequestMapping("/addCategory")
	public String addCategory(Category category,HttpServletRequest request){
		category.setCid(getStringRandom.getNumber(30));
		String findcname = categoryService.findcname(category.getCname());
		if (findcname !=null) {
			request.setAttribute("categorymsg", "该分类已经存在");
			return adminCategoryAll(request);
		}else {
			categoryService.add(category);
			request.setAttribute("categorymsg", "保存成功");
		}
		return adminCategoryAll(request);
	}
	
	@RequestMapping("/deleteParent")
	public String deleteParent(String cid,HttpServletRequest request){
		int cnt = categoryService.findChildrenCountByParent(cid);
		if(cnt > 0) {
			request.setAttribute("categorymsg", "该分类下还有子分类，不能删除！");
			return adminCategoryAll(request);
		} else {
			categoryService.deleteCategory(cid);
			return adminCategoryAll(request);
		}
	}

	@RequestMapping("/deleteChild")
	public String deleteChild(String cid,HttpServletRequest request){
		int cnt = bookService.findBookCountByCategory(cid);
		if(cnt > 0) {
			request.setAttribute("categorymsg", "该分类下还存在图书，不能删除！");
			return adminCategoryAll(request);
		} else {
			categoryService.deleteCategory(cid);
			return adminCategoryAll(request);
		}
	}
	
	@RequestMapping("/editCategory")
	public String editCategory(Category category,HttpServletRequest request){
		String findcname = categoryService.findcname(category.getCname());
		if (findcname !=null) {
			request.setAttribute("categorymsg", "该分类已经存在");
			return adminCategoryAll(request);
		}else {
			categoryService.editCategory(category);
			request.setAttribute("categorymsg", "保存成功");
		}
		return adminCategoryAll(request);
	}
	
	@RequestMapping("/adminSearchBook")
	public String adminSearchBook(@RequestParam(required=false,defaultValue="1")Integer page,
	@RequestParam(required=false,defaultValue="10")Integer rows,String bname,Model model,HttpServletRequest request){
		PageHelper.startPage(page, rows);
		String url = getUrl(request);
		List<Book> searchBook = bookService.adminSearchBook(bname);
		PageInfo<Book> pageInfo = new PageInfo<Book>(searchBook);
		List<Book> bookList = pageInfo.getList();
		request.setAttribute("allBook",bookList);
		request.setAttribute("pageInfo",pageInfo);
		request.setAttribute("url", url);
		return "backbook/main";
	}
	
	@RequestMapping("/addBook")
	public String addBook(HttpServletRequest request){
		List<Category> findParents = categoryService.findAllCategory();
		request.setAttribute("parents", findParents);
		return "backbook/add";
	}
	
	@RequestMapping("/addBookMsg")
	public String addBookMsg(Book book,HttpServletRequest request){
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setFileSizeMax(1024 * 1024);
		List<FileItem> fileItemList = null;
		try {
			fileItemList = sfu.parseRequest(request);
		} catch (FileUploadException e) {
			request.setAttribute("bookmsg", "上传的文件超出了1M");
			return addBook(request);
		}
		FileItem fileItem = fileItemList.get(1);//获取大图
		String filename = fileItem.getName();
		int index = filename.lastIndexOf("\\");
		if(index != -1) {
			filename = filename.substring(index + 1);
		}
		filename = getStringRandom.getNumber(30) + "_" + filename;
		// 校验文件名称的扩展名
		if(!filename.toLowerCase().endsWith(".jpg")) {
			request.setAttribute("bookmsg", "上传的文件超出了1M");
			return addBook(request);
		}
		String savepath = request.getServletContext().getRealPath("/book_img");
		File destFile = new File(savepath, filename);
		try {
			fileItem.write(destFile);//它会把临时文件重定向到指定的路径，再删除临时文件
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		ImageIcon icon = new ImageIcon(destFile.getAbsolutePath());
		Image image = icon.getImage();
		if(image.getWidth(null) > 666 || image.getHeight(null) >666) {
			request.setAttribute("bookmsg", "您上传的图片尺寸超出了666*666！");
			destFile.delete();//删除图片
			return addBook(request);
		}
		book.setImagew("book_img/" + filename);
		fileItem = fileItemList.get(2);//获取小图
		filename = fileItem.getName();
		// 截取文件名，因为部分浏览器上传的绝对路径
		index = filename.lastIndexOf("\\");
		if(index != -1) {
			filename = filename.substring(index + 1);
		}
		filename = getStringRandom.getNumber(30) + "_" + filename;
		// 校验文件名称的扩展名
		if(!filename.toLowerCase().endsWith(".jpg")) {
			request.setAttribute("bookmsg", "上传的图片扩展名必须是JPG");
			return addBook(request);
		}
		savepath = request.getServletContext().getRealPath("/book_img");
		destFile = new File(savepath, filename);
		try {
			fileItem.write(destFile);//它会把临时文件重定向到指定的路径，再删除临时文件
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		icon = new ImageIcon(destFile.getAbsolutePath());
		image = icon.getImage();
		if(image.getWidth(null) > 666 || image.getHeight(null) > 666) {
			request.setAttribute("bookmsg", "您上传的图片尺寸超出了666*666！");
			return addBook(request);
		}
		book.setImageb("book_img/" + filename);
		book.setBid(getStringRandom.getNumber(30));
		System.out.println(book.toString());
		bookService.add(book);
		
		request.setAttribute("msg", "添加图书成功！");
		return "backbook/add";
	}
	
	private String getUrl(HttpServletRequest request) {
		String url = request.getRequestURI() + "?" + request.getQueryString();
		int index = url.lastIndexOf("&page=");
		if(index != -1) {
			url = url.substring(0, index);
		}
		return url;
	}
	
	
	@RequestMapping("/index")
	public String index(){
		return "admin/login";
	}
}
