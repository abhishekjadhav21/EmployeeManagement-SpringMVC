package com.jspider.springmvc1.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class EmployeePojo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String nameString;
	private String email;
	private long Contact;
	private String designation;
	private double salary;
	

}
