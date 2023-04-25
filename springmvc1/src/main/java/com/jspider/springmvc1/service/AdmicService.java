package com.jspider.springmvc1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jspider.springmvc1.pojo.AdminPojo;
import com.jspider.springmvc1.repository.AdminRepository;
@Service
public class AdmicService {
	@Autowired
	AdminRepository repository;

	public AdminPojo login(String username, String password) {
		AdminPojo admin=repository.login(username, password);
		return admin;
	}

}
