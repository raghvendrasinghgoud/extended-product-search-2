package com.nagarro.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.nagarro.entities.Tshirt;
import com.nagarro.utility.AppContext;
import com.nagarro.utility.TshirtUtility;
import com.nagarro.validation.InvalidCategoryException;

@Repository
public class TshirtDao {
	
	@Autowired
	public SessionFactory sessionFactory;
	





	/**
 * @param sessionFactory
 */
public TshirtDao(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
}

		public void saveTshirt(Tshirt tshirt) {
		System.out.println("inside save tshirt");		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		try {
			
			//checking if object already present or not
			if(session.get(Tshirt.class, tshirt.getId())==null)
				session.save(tshirt);
		tx.commit();
		}catch(PersistenceException e) {
			
			//System.out.println("entry alredy present in db");
			tx.rollback();
		}
		System.out.println("inside end save tshirt");
	}
	
	public List<Tshirt> getTshirts(String color,String size,String gender) throws InvalidCategoryException{
		System.out.println("inside get tshirt");
		Session session=sessionFactory.openSession();
		TshirtUtility tsu=new TshirtUtility();
		String queryString = "from Tshirt t where t.color = :color and t.size = :size and t.gender = :gender and t.availability= :available"; 
		Query query = session.createQuery(queryString);
		query.setParameter("color", color);
		query.setParameter("size", tsu.getTSize(size) );
		query.setParameter("gender", tsu.getGender(gender));
		query.setParameter("available", tsu.getTAvailability("Y"));
		List<Tshirt> t = (List<Tshirt>)query.list();
		System.out.println("inside end get tshirt");
		return t;
		}
		
	public static void main(String[] args) throws InvalidCategoryException {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring.xml");
		TshirtDao tdao=ctx.getBean("tshirtDao", TshirtDao.class);
		System.out.println(tdao.getTshirts("green", "l", "m"));
	}
}
