package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.dao.UserDaoImpl;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementType;
import com.revature.models.ReimbursementTypeEnum;
import com.revature.models.User;
import com.revature.service.ReimbursementService;
import com.revature.service.UserService;

public class RequestHelper {

	private static UserService uServ = new UserService(new UserDaoImpl());
	
	private static ReimbursementService rServ = new ReimbursementService(new ReimbursementDaoImpl());

	

	
	private static ObjectMapper om = new ObjectMapper();
	
	public static void processEmployees(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		//1. set the content type to be application/json
		//response.setContentType("application/json");
		response.setContentType("text/html");
		
		//2. Call the findAll() method from the employee service
		List<User> emps = uServ.getAll();
		//3. transform the list into a string
		String jsonString = om.writeValueAsString(emps);
		//write it out
		PrintWriter out = response.getWriter();
		out.write(jsonString);
		
	}

	public static void processRegistration(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String username = request.getParameter("username");

		String password = request.getParameter("password");

		String firstName = request.getParameter("firstname");

		String lastName = request.getParameter("lastname");
		
		String email = request.getParameter("email");
		
		User u = new User(firstName, lastName, username, password, email);
		
		int pk = uServ.register(u);
		
		if (pk > 0) {
			u.setId(pk);
			
			HttpSession session = request.getSession();
			session.setAttribute("the-user", u);
			
			request.getRequestDispatcher("welcome.html").forward(request, response);
		} else {
			PrintWriter out = response.getWriter();
			
			response.setContentType("text/html");
			
			out.println("<h1> Registration failed: User already exists </h1>");
			out.println("<a href=\"index.html\"");
		}

	}
	
	public static void processLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//1. Extract the parameters from the request (username and password)
		String username = request.getParameter("username");
		
		String password = request.getParameter("password");
		
		User u = uServ.confirmLogin(username, password);
		
		if(u.getId() > 0) {
			HttpSession session = request.getSession();
			
			session.setAttribute("the-user", u);
			
//			PrintWriter out = response.getWriter();
//			response.setContentType("text/html");
			
			request.getRequestDispatcher("welcome.html").forward(request, response);
			
//			out.println("<h1>Welcome " + u.getFirstName() + "</h1>");
//			out.println("<h3>You have successfully logged in <h3>");
//			
//			String jsonString = om.writeValueAsString(u);
//			out.println(jsonString);

		} else {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("No user found, sorry!");
		}
		
		
		//4 Alternatively you can redirect to another servlet	
	}
	
	public static void processCreateReimbursement(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		double amount = Double.parseDouble(request.getParameter("amount"));
		
		String description = request.getParameter("description");
		
		HttpSession session = request.getSession();
		
		User u = (User) session.getAttribute("the-user");
		
		//String type = request.getParameter("reimbursement-type");
		ReimbursementTypeEnum typeEnum = ReimbursementTypeEnum.valueOf(request.getParameter("reimbursement-type"));
		ReimbursementType type = new ReimbursementType(typeEnum);
		
		Reimbursement r = new Reimbursement(amount, "null", "null", description, u.getId(), 0, 0, type);
		
		int pk = rServ.createReimbursement(r);
		
		if (pk > 0) {
			
			// Not sure if we want to save this in the session
			//session.setAttribute("reimbursement", r);
			
			request.getRequestDispatcher("welcome.html").forward(request, response);
		} else {
			PrintWriter out = response.getWriter();
			
			response.setContentType("text/html");
			
			out.println("<h1> Failed to create reimbursement request</h1>");
			out.println("<a href=\"index.html\"");
		}
	}
	
	public static void processGetFiledReimbursements(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		
		User u = (User) session.getAttribute("the-user");
		
		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");
		
		List<Reimbursement> userReims = rServ.getUserReimbursements(u.getId());
		
		session.setAttribute("user-reimbursements", userReims);
		
		String jsonString = om.writeValueAsString(userReims);
		
		PrintWriter out = response.getWriter();
		
		out.println(jsonString);
	}
}
