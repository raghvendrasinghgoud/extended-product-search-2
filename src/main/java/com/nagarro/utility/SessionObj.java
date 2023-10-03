package com.nagarro.utility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionObj {
	
	private static Configuration cfg=null;
	private static ServiceRegistry sr=null;
	private static SessionFactory sf=null; 
	public static SessionFactory getSession() {
		if(sf==null) {
			cfg=new Configuration().configure();
			sf=cfg.buildSessionFactory();
			return sf;
		}
		
		return sf;
		
	}
}
