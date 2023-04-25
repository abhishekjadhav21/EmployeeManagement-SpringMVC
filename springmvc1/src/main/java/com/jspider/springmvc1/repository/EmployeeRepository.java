package com.jspider.springmvc1.repository;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


import org.springframework.stereotype.Repository;

import com.jspider.springmvc1.pojo.AdminPojo;
import com.jspider.springmvc1.pojo.EmployeePojo;

@Repository
public class EmployeeRepository {
	
	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transaction;
	private static Query query;
	private static String jpqlQuery;
	
	private static void openConnection() {
		factory=Persistence.createEntityManagerFactory("emp");
		manager=factory.createEntityManager();
		transaction=manager.getTransaction();
	}
	
	private static void closeConnection() {
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
	
	public EmployeePojo  addEmployee(String name,String email,long contact,String designation,double salary) {
		openConnection();
		transaction.begin();
		
		EmployeePojo emp1=new EmployeePojo();
		emp1.setNameString(name);
		emp1.setEmail(email);
		emp1.setContact(contact);
		emp1.setDesignation(designation);
		emp1.setSalary(salary);
		
		manager.persist(emp1);
		transaction.commit();
		closeConnection();
		return emp1;
	}

	public EmployeePojo searchEmployee(int id) {
		openConnection();
		transaction.begin();
		EmployeePojo employee=manager.find(EmployeePojo.class, id);
		transaction.commit();
		closeConnection();
		return employee;
	}

	public List<EmployeePojo> employeeList() {
		openConnection();
		transaction.begin();
		jpqlQuery="from EmployeePojo";
		query=manager.createQuery(jpqlQuery);
	    List<EmployeePojo> employees=query.getResultList();
	    transaction.commit();
	    closeConnection();
		
		return employees;
	}

	public EmployeePojo removeEmployee(int id) {
		openConnection();
		transaction.begin();
		EmployeePojo emp1=manager.find(EmployeePojo.class, id);
		manager.remove(emp1);
		transaction.commit();
		closeConnection();
		
		return emp1;
	}

	public EmployeePojo updateEmployee(int id, String name, String email, long contact, String designation,
			double salary) {
		openConnection();
		transaction.begin();
		EmployeePojo employee=manager.find(EmployeePojo.class, id);
		employee.setNameString(name);
		employee.setEmail(email);
		employee.setContact(contact);
		employee.setDesignation(designation);
		employee.setSalary(salary);
		manager.persist(employee);
		transaction.commit();
		closeConnection();
		return employee;
	}

	public AdminPojo createAdmin(String username, String password) {
		openConnection();
		transaction.begin();
		AdminPojo admin=new AdminPojo();
		admin.setUsername(username);
		admin.setPassword(password);
		manager.persist(admin);
		transaction.commit();
		closeConnection();
		return admin;
	}

}
