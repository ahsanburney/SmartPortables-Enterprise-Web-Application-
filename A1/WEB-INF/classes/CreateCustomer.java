

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class CreateCustomer extends HttpServlet {

	CustomerDetails customerDetails;
	HashMap<String,CustomerDetails> customerDetailsHashMap;
	String firstName;
	String lastName;
	String emailId;
	String password;

	CustomerHashmap customerHashmap;
	public void init()
	{
		customerHashmap = new CustomerHashmap();
		customerDetailsHashMap = customerHashmap.getCustomerHashMap();
		customerDetailsHashMap = MySqlDataStoreUtilities.fetchCustomerDetails();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");

		firstName = request.getParameter("firstName");
		lastName = request.getParameter("lastName");
		emailId = request.getParameter("emailId");
		password = request.getParameter("password");

		pw.println("<!doctype html>" +
				"<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		pw.println("<title>Smart Portables</title>" +
				"<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		pw.println("<body><div id=\"container\">");
		pw.println("<header><h1>" +
				"<a href=\"/\">Smart<span>Portables</span></a></h1><h3>Salesman Area</h3></header>");
		pw.println("<nav><ul>");
		pw.println("<li class=\"start selected\">" +
				"<a href=\"SalesmanServlet?type=createCustomer\">Create Customer</a></li>");
		pw.println("<li><a href=\"SalesmanServlet?type=addOrder\">Add Order</a></li>");
		pw.println("<li><a href=\"SalesmanServlet?type=updateOrder\">Update Order</a></li>");
		pw.println("<li><a href=\"SalesmanServlet?type=deleteOrder\">Delete Order</a></li>");
		pw.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");



		if(firstName != null && firstName.length() != 0) {
			firstName = firstName.trim();
		}
		if(lastName != null && lastName.length() != 0) {
			lastName = lastName.trim();
		}
		if(emailId != null && emailId.length() != 0) {
			emailId = emailId.trim();
		}
		if(password != null && password.length() != 0) {
			password = password.trim();
		}


		if(firstName != "" && lastName != "" && emailId != "")
		{
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
				pw.println("<p>User already present!</p>");
			}
			else
			{
				MySqlDataStoreUtilities.insertIntoregisterTable(firstName, lastName, emailId, password);


				pw.println("<div id=\"body\"><article><h3 align=\"center\">Customer Created</h3>");
				pw.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
				pw.println("<h3>Customer created and added to Customer List<h3>");
			}
		}

		else
		{
			pw.println("<h3>Error Occured<h3>");
		}
		pw.println("</div></fieldset></article</div></div></body></html>");
		pw.close();

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}


