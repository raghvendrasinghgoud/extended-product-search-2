package com.nagarro.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.entities.User;
import com.nagarro.utility.AppContext;
import com.nagarro.validation.AlreadyRegisteredException;
import com.nagarro.validation.IdentityNotFound;

@Repository
public class UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	

	/**
	 * @param session
	 */
	public UserDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public boolean isUserExists(String username) {
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		if(session.find(User.class, username)==null) {
			return false;
		}
		return true;
	}
	
	public void save(User user) throws AlreadyRegisteredException {
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		if(isUserExists(user.getUsername())) {
			throw new AlreadyRegisteredException("username already registered");
		}
			session.save(user);
		tx.commit();
	}
	
	public User getUserByUsername(String username) throws IdentityNotFound {
		User user=null;
		Session session=sessionFactory.openSession();
		user=session.find(User.class, username);
		
		if(user==null)
				throw new IdentityNotFound("User not registered");
		return user;
	}
	
	public static void main(String[] args) {
		System.out.println(AppContext.getContext().getBean("userDao",UserDao.class));
	}
}
