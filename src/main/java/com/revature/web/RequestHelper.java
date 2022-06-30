package com.revature.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InaccessibleObjectException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.dao.ReimbursementStatusDaoImpl;
import com.revature.dao.ReimbursementTypeDaoImpl;
import com.revature.dao.UserDaoImpl;
import com.revature.dao.UserTypeDaoImpl;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementStatusEnum;
import com.revature.models.ReimbursementType;
import com.revature.models.ReimbursementTypeEnum;
import com.revature.models.User;
import com.revature.models.UserType;
import com.revature.models.UserTypeEnum;
import com.revature.service.ReimbursementService;
import com.revature.service.ReimbursementStatusService;
import com.revature.service.ReimbursementTypeService;
import com.revature.service.UserService;
import com.revature.service.UserTypeService;

public class RequestHelper {

	private static UserService uServ = new UserService(new UserDaoImpl());

	private static ReimbursementService rServ = new ReimbursementService(new ReimbursementDaoImpl());

	private static ReimbursementTypeService rtServ = new ReimbursementTypeService(new ReimbursementTypeDaoImpl());

	private static ReimbursementStatusService rsServ = new ReimbursementStatusService(new ReimbursementStatusDaoImpl());

	private static UserTypeService utServ = new UserTypeService(new UserTypeDaoImpl());

	private static ObjectMapper om = new ObjectMapper();

	public static void processEmployees(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// 1. set the content type to be application/json
		// response.setContentType("application/json");
		response.setContentType("text/html");

		// 2. Call the findAll() method from the employee service
		List<User> emps = uServ.getAll();
		// 3. transform the list into a string
		String jsonString = om.writeValueAsString(emps);
		// write it out
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

		UserType userType = new UserType();

		userType.setUser_type(UserTypeEnum.EMPLOYEE);
		userType = utServ.createUserType(userType);

		User u = new User(firstName, lastName, username, password, email, userType);

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

	public static void processLogin(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// 1. Extract the parameters from the request (username and password)
		String username = request.getParameter("username");

		String password = request.getParameter("password");

		User u = uServ.confirmLogin(username, password);

		if (u.getId() > 0) {
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

		// 4 Alternatively you can redirect to another servlet
	}

	public static void processCreateReimbursement(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		response.setContentType("application/josn");
		response.addHeader("Access-Control-Allow-Origin", "*");

		System.out.println("In the processeCreateReimbursementMethod within the Request Helper");

		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		JsonObject payload = new JsonObject();

		JsonParser jsonParser = new JsonParser();

		JsonElement root = jsonParser.parse(new InputStreamReader((InputStream) request.getInputStream()));

		JsonObject rootObject = root.getAsJsonObject();

		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("the-user");

		ReimbursementStatus status = new ReimbursementStatus();
		status.setReim_status(ReimbursementStatusEnum.PENDING);
		status = rsServ.createReimbursementStatus(status);

		double amount = rootObject.get("amount").getAsDouble();

		String description = rootObject.get("description").getAsString();

		ReimbursementTypeEnum typeEnum = ReimbursementTypeEnum.valueOf(rootObject.get("type").getAsString());
		ReimbursementType type = new ReimbursementType(typeEnum);
		type = rtServ.createReimbursementType(type);
	
		Instant curTime = Instant.now();
		curTime = curTime.truncatedTo(ChronoUnit.SECONDS);

		Reimbursement r = new Reimbursement(amount, curTime, null, description, u, null, status, type);

		r = rServ.createReimbursement(r);

		PrintWriter pw = response.getWriter();
		
		try {
			String json = gson.toJson(r);
			pw.write(json);
			
		} catch (InaccessibleObjectException e) {
			
		}
	}

	public static void processGetFiledReimbursements(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		HttpSession session = request.getSession();

		User u = (User) session.getAttribute("the-user");

		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");

		List<Reimbursement> userReims = rServ.getUserReimbursements(u.getId());

		// session.setAttribute("user-reimbursements", userReims);

		String jsonString = om.writeValueAsString(userReims);

		PrintWriter out = response.getWriter();

		out.println(jsonString);
	}
}
