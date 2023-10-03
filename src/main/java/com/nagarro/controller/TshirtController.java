package com.nagarro.controller;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.categories.OutputPreferenceType;
import com.nagarro.comparator.CompareBy;
import com.nagarro.dao.TshirtDao;
import com.nagarro.entities.Tshirt;
import com.nagarro.utility.AppContext;
import com.nagarro.utility.CSVUtility;
import com.nagarro.utility.LoadFiles;
import com.nagarro.utility.SessionObj;
import com.nagarro.validation.DataNotSufficientException;
import com.nagarro.validation.InvalidCategoryException;
import com.nagarro.validation.InvalidHeadingException;

@Controller
public class TshirtController {
	
		@RequestMapping("/tshirtpath")
		public ModelAndView getRequestedTshirts(HttpServletRequest request, HttpServletResponse response){
			
			HttpSession session=request.getSession();
//			// load files thread
			LoadFiles lf=(LoadFiles)session.getAttribute("loadfiles");
			CSVUtility csu=AppContext.getContext().getBean("csvUtility",CSVUtility.class);
			try {
//				String path=request.getRealPath("data");
//				System.out.println(path);
//				File f=new File(path);
//				File[] files=f.listFiles();
				csu.storeTshirtsInDBFromFiles(lf.getAllCsv());
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvalidHeadingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvalidCategoryException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (DataNotSufficientException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			ModelAndView mv=new ModelAndView();
			
			mv.setViewName("manage");
			
			try {
			String color=request.getParameter("color");
			String gender=request.getParameter("gender");
			String size=request.getParameter("size");
			String outputPreference=request.getParameter("op");
			
				
			TshirtDao tdao=new TshirtDao(SessionObj.getSession());
//			List<Tshirt> tshirts=AppContext.getContext().getBean("tshirtDao",TshirtDao.class)
//										.getTshirts(color,size,gender);
			List<Tshirt> tshirts=new TshirtDao(SessionObj.getSession()).getTshirts(color,size,gender);

			if(outputPreference.equalsIgnoreCase(OutputPreferenceType.RATING.toString())) {
				Collections.sort(tshirts,CompareBy.rating());
			}else {
				Collections.sort(tshirts,CompareBy.price());
			}
			
			mv.addObject("tshirtsList",tshirts);
			
			}catch(InvalidCategoryException e) {
				System.out.println(e.getMessage());
			}catch(NullPointerException e) {
				mv.addObject("error","All fields are mandatory");
			}
			
			return mv;
		}
	}

