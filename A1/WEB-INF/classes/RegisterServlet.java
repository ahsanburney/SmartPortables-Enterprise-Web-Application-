

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class RegisterServlet extends HttpServlet
{
	CustomerDetails customerDetails;
	HashMap<String,CustomerDetails> customerDetailsHashMap;
	String fName;
	String lName;
    String emailId;
	String password;

	
	CustomerHashmap customerHashmap;
	
	public void init()
	{
		customerHashmap = new CustomerHashmap();
		//customerDetailsHashMap = customerHashmap.getCustomerHashMap();
		customerDetailsHashMap = MySqlDataStoreUtilities.fetchCustomerDetails();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	response.setContentType("text/html");
	PrintWriter pw = response.getWriter();
	
	fName = request.getParameter("firstName");
	lName = request.getParameter("lastName");
	emailId = request.getParameter("emailId");
	password = request.getParameter("pass");

	
	if(fName != null && fName.length() != 0) {
            fName = fName.trim();
        }
	if(lName != null && lName.length() != 0) {
            lName = lName.trim();
		}
	if(emailId != null && emailId.length() != 0) {
            emailId = emailId.trim();
        }
	if(password != null && password.length() != 0) {
            password = password.trim();
		}
	
	if(fName != "" && lName != "" && emailId != "" && password != "")
	{
		//customerDetailsHashMap = customerHashmap.getCustomerHashMap();
		customerDetailsHashMap = MySqlDataStoreUtilities.fetchCustomerDetails();
		boolean flag = false;
		{
		for(Map.Entry<String,CustomerDetails> map : customerDetailsHashMap.entrySet())
		{
			CustomerDetails details = map.getValue();
			
			if(details.getemailId().equals(emailId))
			{
				flag = true;
			}
		}
		}
		if(flag)
		{
			pw.println("<p>Email already present!</p>");
		}
		else
		{
			MySqlDataStoreUtilities.insertIntoregisterTable(fName, lName, emailId, password);
		//customerDetails = new CustomerDetails(firstName, lastName, emailId, pass);
		//customerDetailsHashMap = customerHashmap.getCustomerHashMap();
		//customerDetailsHashMap.put(customerDetails.getemailId(), customerDetails);
		//customerHashmap.writeCustomerHashMap(customerDetailsHashMap);
		//customerHashmap.getCustomerHashMap();
		
		pw.println("<docType html>"+
		"<html><head>" +
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />"+
		"<title>Smart&nbsp <span>Portables</title>"+
		"<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />"+
		"</head><body>" +
				"<div id=\"container\">"+
		"<header><h1>" +
				"<a href=\"/\">Smart&nbsp <span>Portables</a></h1></header>"+
		"<h3 align=\"center\">Signedup successfully. Please Login Below</h3>"+
				"<center>Click <a href='login.html'>Log In</a> to login!</center>"+
		"<footer>"+
        "<div class='footer-bottom'>"+
        "<p></p>"+
        "</div>"+
        "</footer>"+
		"</div></div></body></html>");
		}
	}
	
	else
	{
		pw.println("<h3>Error Occured<h3>");
	}
	
	pw.close();
	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
}