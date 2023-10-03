package com.nagarro.comparator;

import java.util.Comparator;

import org.springframework.web.servlet.DispatcherServlet;

import com.nagarro.entities.Tshirt;

public class CompareBy{

	public static Comparator<Tshirt> price(){
		Comparator<Tshirt> comp=(Tshirt t1,Tshirt t2)->{
			if(t1.getPrice()<t2.getPrice()) return 1;
			else if(t1.getPrice()>t2.getPrice()) return -1;
			else return 0;
		};
		
		return comp;
	}
	
	public static Comparator<Tshirt> rating(){
		Comparator<Tshirt> comp=(Tshirt t1,Tshirt t2)->{
			if(t1.getRating()<t2.getRating()) return 1;
			else if(t1.getRating()>t2.getRating()) return -1;
			else return 0;
		};
		
		return comp;
	}

}
