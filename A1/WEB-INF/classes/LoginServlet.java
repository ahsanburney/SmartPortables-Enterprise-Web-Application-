

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class LoginServlet extends HttpServlet
{
	HashMap<String,CustomerDetails> customerDetailsHashMap;
	HashMap<String,String> adminHashmap;
	String emailId;
	String password;
	CustomerHashmap hashmap;

	public void init()
	{
		hashmap = new CustomerHashmap();
		customerDetailsHashMap = MySqlDataStoreUtilities.fetchCustomerDetails();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		pw.println("<!doctype html><html>"+
				"<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />"+
				"<title>Smart&nbspPortables</title>"+
				"<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />"+
				"</head><body><div id=\"container\">"+
				"<header><h1><a href=\"/\">Smart&nbsp <span>Portables</span></a></h1></header>"+
				"<h3 align=\"center\">Customer Login</h3>"+
				"<div>"+
				"<center><form action=\"/A1/LoginServlet\" method=\"POST\">"+
				"<p><label>User Name:</label>"+
				"<input name=\"emailId\" id=\"name\" value=\"\" type=\"text\" /></p>"+
				"<p><label>Password:</label>"+
				"<input name=\"pass\" id=\"email\" value=\"\" type=\"pass\" /></p>"+
				"<p><input name=\"send\"value=\"Login\" type=\"submit\" /></p>"+
				"</form></center>"+
				"<footer>"+
				"<div class='footer-bottom'>"+
				"<p></p>"+
				"</div>"+
				"</footer>"+
				"</div></div></body></html>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession httpSession = request.getSession(true);

		emailId = request.getParameter("emailId");
		password = request.getParameter("pass");
		String customerCategory = request.getParameter("usertype");

		if (emailId != null && emailId.length() != 0) {
			emailId = emailId.trim();
		}
		if (password != null && password.length() != 0) {
			password = password.trim();
		}

		if (customerCategory.equals("salesmanager")) {
			adminHashmap = MySqlDataStoreUtilities.getAdminDetails();
			boolean flag = false;
			for (Map.Entry<String, String> m : adminHashmap.entrySet()) {
				String email = m.getKey();
				String pass = m.getValue();
				if (emailId.equals(email) && password.equals(pass)) {
					flag = true;
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("SalesmanServlet");
					requestDispatcher.forward(request, response);
				}
			}

			if (flag == false) {
				out.println("<b>Wrong Username or Password. Try again !<b>");
			}
		}else if (customerCategory.equals("storemanager")) {
			adminHashmap = MySqlDataStoreUtilities.getAdminDetails();
			boolean flag = false;
			for (Map.Entry<String, String> mapping : adminHashmap.entrySet()) {
				String email = mapping.getKey();
				String pass = mapping.getValue();
				if (emailId.equals(email) && password.equals(pass)) {
					flag = true;
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("AdministratorServlet");
					requestDispatcher.forward(request, response);
				}

			}
			if (flag == false) {
				out.println("<b>Wrong username or password!<b>");
			}
		} else
		if (customerCategory.equals("customer")) {
			customerDetailsHashMap = MySqlDataStoreUtilities.fetchCustomerDetails();
			boolean b = false;
			for (Map.Entry<String, CustomerDetails> mapping : customerDetailsHashMap.entrySet()) {
				CustomerDetails value = mapping.getValue();
				if (value.getemailId().equals(emailId) && value.getpassword().equals(password)) {
					b = true;
					httpSession.setAttribute("firstName", value.getfirstName());
					httpSession.setAttribute("userid", emailId);
					RequestDispatcher view = request.getRequestDispatcher("HomeServlet");
					view.forward(request, response);
				}
			}
			if (b == false) {
				out.println("<b>Wrong Username or Password. Try again !<b>");
			}
			out.close();
		}
	}
}