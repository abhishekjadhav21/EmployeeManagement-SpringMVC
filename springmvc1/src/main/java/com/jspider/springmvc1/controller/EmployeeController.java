package com.jspider.springmvc1.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.jspider.springmvc1.pojo.AdminPojo;
import com.jspider.springmvc1.pojo.EmployeePojo;
import com.jspider.springmvc1.service.AdmicService;
import com.jspider.springmvc1.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService service;

	@Autowired
	AdmicService admicService;

	// Home Controller
	@GetMapping("/home")
	public String home(@SessionAttribute(name = "login", required = false) AdminPojo login, ModelMap map) {
		if (login != null) {
			return "Home";

		}
		map.addAttribute("msg", "Please login to proceed..!!");
		return "Login";
	}

	// Add Controller
	@GetMapping("/add")
	public String addPage(@SessionAttribute(name = "login", required = false) AdminPojo login, ModelMap map) {
		if (login != null) {
			return "Add";
		}

		map.addAttribute("msg", "Please login to proceed..!!");
		return "Login";
	}

	// Add Employee Controller
	@PostMapping("/add")
	public String addData(@SessionAttribute(name = "login", required = false) AdminPojo login,
			@RequestParam String name, @RequestParam String email, @RequestParam long contact,
			@RequestParam String designation, @RequestParam double salary, ModelMap map) {

		if (login != null) {
			EmployeePojo employee = service.addEmployee(name, email, contact, designation, salary);
			if (employee != null) {
				// Successs Statement
				map.addAttribute("msg", "Data added Successfully...!!!");
				return "Add";
			}
			// Failure response
			map.addAttribute("msg", "Data not added");
			return "Add";

		}
		map.addAttribute("msg", "Please login to proceed..!!");
		return "Login";

	}

	// Search Controller
	@GetMapping("/search")
	public String searchPage(@SessionAttribute(name = "login", required = false) AdminPojo login, ModelMap map) {
		if (login != null) {
			return "Search";
		}

		map.addAttribute("msg", "Please login to proceed..!!");
		return "Login";

	}

	// Search Employee Controller
	@PostMapping("/search")
	public String search(@SessionAttribute(name = "login", required = false) AdminPojo login, @RequestParam int id,
			ModelMap map) {
		if (login != null) {
			EmployeePojo employee = service.search(id);
			if (employee != null) {
				map.addAttribute("msg", "Employee Record Found Successfully....!!");
				map.addAttribute("emp", employee);
				return "Search";
			}
			// Failure response
			map.addAttribute("msg", "Employee Record Not Found...!!!");
			return "Search";
		}
		map.addAttribute("msg", "Please login to proceed..!!");
		return "Login";

	}

	// Remove Page Controller
	@GetMapping("/remove")
	public String removePage(@SessionAttribute(name = "login", required = false) AdminPojo login, ModelMap map) {

		if (login != null) {
			List<EmployeePojo> employee = service.employeeList();
			map.addAttribute("emplist", employee);
			return "Remove";
		}

		map.addAttribute("msg", "Please login to proceed..!!");
		return "Login";

	}

	// Remove data Controller
	@PostMapping("/remove")
	public String removeEmployee(@SessionAttribute(name = "login", required = false) AdminPojo login,
			@RequestParam int id, ModelMap map) {
		if (login != null) {
			EmployeePojo employee = service.search(id);
			if (employee != null) {
				// Success Response
				service.remove(id);
				List<EmployeePojo> employeePojo = service.employeeList();
				map.addAttribute("emplist", employeePojo);
				map.addAttribute("msg", "Data removed successfully..!!");
				return "Remove";

			}
			// Failure Response
			List<EmployeePojo> employeePojo = service.employeeList();
			map.addAttribute("emplist", employeePojo);
			map.addAttribute("msg", "Data does not exist..!!");
			return "Remove";
		}

		map.addAttribute("msg", "Please login to proceed..!!");
		return "Login";

	}

	@GetMapping("/update")
	public String updatePage(@SessionAttribute(name = "login", required = false) AdminPojo login, ModelMap map) {
		if (login != null) {
			List<EmployeePojo> employee = service.employeeList();
			map.addAttribute("emplist", employee);
			return "Update";
		}

		map.addAttribute("msg", "Please login to proceed..!!");
		return "Login";

	}

	@PostMapping("/update")
	public String select(@SessionAttribute(name = "login", required = false) AdminPojo login, @RequestParam int id,
			ModelMap map) {
		if (login != null) {
			EmployeePojo employee = service.search(id);
			if (employee != null) {
				map.addAttribute("emp", employee);
				return "Update";

			}

			map.addAttribute("msg", "Empoyee Record Not Exist");
			List<EmployeePojo> employeeList = service.employeeList();
			map.addAttribute("emplist", employeeList);
			return "Update";
		}

		map.addAttribute("msg", "Please login to proceed..!!");
		return "Login";

	}

	@PostMapping("/updateData")
	public String updateData(@SessionAttribute(name = "login", required = false) AdminPojo login, @RequestParam int id,
			@RequestParam String name, @RequestParam String email, @RequestParam long contact,
			@RequestParam String designation, @RequestParam double salary, ModelMap map) {
		if (login != null) {
			EmployeePojo employee = service.updateEmployee(id, name, email, contact, designation, salary);
			if (employee != null) {
				// Success Response
				map.addAttribute("msg", "update Successfully");
				map.addAttribute("emp", employee);
				List<EmployeePojo> employeeList = service.employeeList();
				map.addAttribute("emplist", employeeList);
				return "Update";
			}
			map.addAttribute("msg", "Not Updtae");
			return "Update";
		}

		map.addAttribute("msg", "Please login to proceed..!!");
		return "Login";
	}

	@PostMapping("/login")
	public String login(HttpServletRequest request, @RequestParam String username, @RequestParam String password, ModelMap map) {
		AdminPojo admin = admicService.login(username, password);
		if (admin != null) {
			//Success Response
			HttpSession session = request.getSession();
			session.setAttribute("login", admin);
			return "Home";
		}
		map.addAttribute("msg", "Invalid username or password");
		return "Login";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session,ModelMap map) {
		map.addAttribute("msg", "Logged out successfully ");
		session.invalidate();
		return "Login";
	}

	@GetMapping("/createAdmin")
	public String adminPage() {
		return "Admin";
	}

	@PostMapping("/createAccount")
	public String crateAdmin(@RequestParam String username, @RequestParam String password, ModelMap map) {
		AdminPojo admin = service.crateAdmin(username, password);
		if (admin != null) {
			// Success response
			map.addAttribute("msg", "Account created successfully..!!");
			return "Login";
		}
		// Failure response
		map.addAttribute("msg", "Account not created..!!");
		return "Login";
	}

}
