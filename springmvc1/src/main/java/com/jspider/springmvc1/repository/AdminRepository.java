package com.jspider.springmvc1.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.jspider.springmvc1.pojo.AdminPojo;
@Repository
public class AdminRepository {
	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transaction;
	private static Query query;
	private static String jpql;
	
	
	public void openConnection() {
		factory=Persistence.createEntityManagerFactory("emp");
		manager=factory.createEntityManager();
		transaction=manager.getTransaction();
		
	}
	public void closeConnection() {
		if (factory!=null) {
			factory.close();
		}
		if (manager!=null) { 
			manager.close();
			
		}
		if (transaction.isActive()) {
			transaction.rollback();
		}
	}
	
	public AdminPojo login(String username,String password) {
		openConnection();
		transaction.begin();
		jpql = "from AdminPojo " 
				+ "where username = '" + username + "'" 
				+ "and password = '" + password + "'";
		query=manager.createQuery(jpql);
		List<AdminPojo> admin=query.getResultList();
		for(AdminPojo pojo:admin) {
			transaction.commit();
			closeConnection();
			return pojo;
		}
		return null;
		
	}

}
