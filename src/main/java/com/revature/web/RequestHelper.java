package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestHelper {
	private static UserService uServ = new UserService(new UserDao());
	
	private static ObjectMapper om = new ObjectMapper();
	
	public static void processLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		//1. Extract the parameters from the request (username and password)
		String username = request.getParameter("username");
		
		String password = request.getParameter("password");
		
		User u = uServ.confirmLogin(username, password);
		
		if(u.getId() > 0) {
			HttpSession session = request.getSession();
			
			session.setAttribute("the-user", u);
			
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			
			out.println("<h1>Welcome " + u.getFirstName() + "</h1>");
			out.println("<h3>You have successfully logged in <h3>");
			
			String jsonString = om.writeValueAsString(u);
			out.println(jsonString);
		} else {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("No user found, sorry!");
		}
		
		
		//4 Alternatively you can redirect to another servlet
		
		
	}
}
