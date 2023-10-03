package com.nagarro.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nagarro.dao.UserDao;
import com.nagarro.entities.User;
import com.nagarro.utility.AppContext;
import com.nagarro.utility.LoadFiles;
import com.nagarro.validation.IdentityNotFound;

@Controller
public class UserController {
	@RequestMapping("/")
	public String goHome() {
		
		System.out.println("inside home controller");
		return "index";
	}
	
	@RequestMapping("/userloginpath")
	public String login(HttpServletRequest request,HttpServletResponse response) {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		UserDao ud=AppContext.getContext().getBean("userDao",UserDao.class);
		
		HttpSession ss=request.getSession();
		try {
			
			User user=ud.getUserByUsername(username);
			System.out.println(user);
			if(user.getPassword().equals(password)) {
				
				ss.setAttribute("user", user);
				
				System.out.println("user logged in");
				
				//load csv files
				String path=request.getRealPath("data");
				System.out.println(path);
				
				LoadFiles lf=new LoadFiles(path);
				ss.setAttribute("loadfiles", lf);
				//end of csv load
				
				return "manage";
			}else {
				ss.setAttribute("msg", "* Password does not matched");
				return "index";
			}
		} catch (IdentityNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ss.setAttribute("msg", "* Invalid username");
			return "index";
		}
	}
	
	@RequestMapping("logoutpath")
	public String logout(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session=request.getSession();
		
		
//		exit load files thread
		LoadFiles lf=(LoadFiles)session.getAttribute("loadfiles");
		lf.setExit(true);
		
		String name[]=session.getValueNames();
		for(String s:name) {
			session.removeAttribute(s);
		}
        session.invalidate();
        
        System.out.println("you got logout");
        return "index";
	}
}
