package com.revature.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//final String URI = request.getRequestURI().replace("/official-project-one/", "");
		final String URI = request.getRequestURI().replace("/official-project-one/", "");
		
		switch(URI) {
		case "login":
			RequestHelper.processLogin(request, response);
			break;
		case "employees":
			RequestHelper.processEmployees(request, response);
			break;
		case "register":
			RequestHelper.processRegistration(request, response);
			break;
		case "get-user":
			RequestHelper.getUser(request, response);
			break;
		case "update":
			RequestHelper.processUpdate(request, response);
			break;
		case "reimbursements":
			RequestHelper.processCreateReimbursement(request, response);
			break;
		case "view-filed-reimbursements":
			RequestHelper.processGetFiledReimbursements(request, response);
			break;
		case "view-reimbursements-by-status":
			RequestHelper.processGetReimbursementsByStatus(request, response);
			break;
		case "update-reimbursement":
			RequestHelper.processUpdateReimbursement(request, response);
			break;
		default:
			break;
		}
	}
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
