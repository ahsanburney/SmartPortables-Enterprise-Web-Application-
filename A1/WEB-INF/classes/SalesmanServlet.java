

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalesmanServlet extends HttpServlet {

	HashMap<String,CustomerDetails> customers;
	String firstName;
	String emailId;

	Order order;
	OrderDataStore orderDataStore;
	HashMap<String,Order> stringOrderHashMap;

	CustomerHashmap hashmap;

	public void init()
	{
		hashmap = new CustomerHashmap();
		customers = MySqlDataStoreUtilities.fetchCustomerDetails();

		orderDataStore = new OrderDataStore();
		stringOrderHashMap= new HashMap<String, Order>();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String category = request.getParameter("type");
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");

		pw.println("<!doctype html>" +
				"<html><head>" +
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		pw.println("<title>Smart Portables</title>" +
				"<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		pw.println("<body>" +
				"<div id=\"container\">");
		pw.println("<header><h1>" +
				"<a href=\"/\">Smart<span>Portables</span></a></h1><h3>Salesman Area</h3></header>");
		pw.println("<nav><ul>");
		pw.println("<li class=\"start selected\">" +
				"<a href=\"SalesmanServlet?type=createCustomer\">Create Customer</a></li>");
		pw.println("<li>" +
				"<a href=\"SalesmanServlet?type=addOrder\">Add Order</a></li>");
		pw.println("<li>" +
				"<a href=\"SalesmanServlet?type=delete\">Delete Order</a></li>");

		pw.println("<li>" +
				"<a href=\"SalesmanServlet?type=update\">Update Order</a></li>");
		pw.println("<li>" +
				"<a href=\"LogoutServlet\">Logout</a></li></ul></nav>");


		if(category == null)
		{

			pw.println("<div id=\"body\">");
			pw.println("<table border='2' bordercolor=\"#ff0000\"><th>");
			pw.println("<article>" +
					"<h3 align=\"center\">Create a new Customer</h3>");
			pw.println("<fieldset>" +
					"<div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
			pw.println("<form action=\"/A1/CreateCustomer\" method=\"get\">");
			pw.println("<p><label for=\"name\">First Name:</label>" +
					"<input name=\"firstName\" id=\"firstName\" value=\"\" type=\"text\" /></p>");
			pw.println("<p><label for=\"email\">Last Name:</label>" +
					"<input name=\"lastName\" id=\"lastName\" value=\"\" type=\"text\" /></p>");
			pw.println("<p><label>User Name:</label>" +
					"<input name=\"emailId\" id=\"email\" value=\"\" type=\"text\" /></p>");
			pw.println("<p><label>Password:</label>" +
					"<input name=\"password\" id=\"email\" value=\"\" type=\"text\" /></p>");
			pw.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Submit\" type=\"submit\" /></p>");
			pw.println("</form></div></fieldset>" +
					"</article</div></div>" +
					"</body></html>");
		}

		else if(category.equals("delete"))
		{
			pw.println("<div id=\"body\">" +
					"<table border=\"2\" bordercolor=\"#ff0000\">" +
					"<article><h3 align=\"center\">Display Customer Orders</h3>");
			pw.println("<fieldset>" +
					"<div style=\"width:400px; margin-right:auto; margin-left:auto;\">");

			pw.println("<form action=\"SalesmanServlet\" method=\"post\">");
			pw.println("<input type='hidden' name='queryType' value='deleteOrder'>");
			pw.println("<p>" +
					"<label>Customer User Name:</label><input name=\"customerEmailId\" id=\"email\" value=\"\" type=\"text\" /></p>");
			pw.println("<p>" +
					"<input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Display Customer Orders\" type=\"submit\" /></p>");

			pw.println("</form></div>" +
					"</fieldset></article</div></div></body></html>");
		}

		else if(category.equals("update"))
		{
			pw.println("<div id=\"body\">" +
					"<article><h3 align=\"center\">Update Order</h3>");
			pw.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");

			pw.println("<form action=\"SalesmanServlet\" method=\"post\">");
			pw.println("<input type='hidden' name='queryType' value='updateOrder'>");
			pw.println("<p><label>Customer Name</label>" +
					"<input name=\"customerOrder\" id=\"email\" value=\"\" type=\"text\" /></p>");
			pw.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Display\" type=\"submit\" /></p>");

			pw.println("</form></div></fieldset>" +
					"</article</div></div></body></html>");
		}


		else if(category.equals("addOrder"))
		{

			DateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			Date date1 = new Date();
			String dateOfOrder = format.format(date1).toString();
			Calendar instance = Calendar.getInstance();
			instance.add(Calendar.DAY_OF_MONTH, 14);
			Date date = instance.getTime();
			String dateFormat = "MM/dd/yyyy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
			String dateOfDelivery = simpleDateFormat.format(date);


			pw.println("<div id=\"body\">" +
					"<table border=\"2\" bordercolor=\"#ff0000\"><th>" +
					"<article><h3 align=\"center\">Add Order</h3>");
			pw.println("<fieldset>" +
					"<div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
			pw.println("<form action=\"SalesmanServlet\" method=\"post\">");
			pw.println("<input type='hidden' name='queryType' value='addOrder'>");
			pw.println("<input type='hidden' name='orderDate' value='"+dateOfOrder+"'>");
			pw.println("<input type='hidden' name='deliveryDate' value='"+dateOfDelivery+"'>");

			pw.println("<p><label>Order Date: </label>"+dateOfOrder+"</p>");
			pw.println("<p><label>Customer User Name:</label>" +
					"<input name=\"customerEmailId\" id=\"email\" value=\"\" type=\"text\" /></p>");
			pw.println("<p><label>Order Id:</label><input name='orderId' id='email' value='' type='text' /></p>");
			pw.println("<p><label>Item Name:</label>" +
					"<input name=\"itemName\" id=\"email\" value=\"\" type=\"text\" /></p>");
			pw.println("<p><label>Item Price:</label>" +
					"<input name=\"itemPrice\" id=\"email\" value=\"\" type=\"text\" /></p>");
			pw.println("<p><label>Item Qty:</label>" +
					"<input name=\"itemQty\" id=\"email\" value=\"\" type=\"text\" /></p>");
			pw.println("<p><label>Delivery Address:</label>" +
					"<input name=\"deliveryAddress\" id=\"email\" value=\"\" type=\"text\" /></p>");
			pw.println("<p><label>Delivery Date: </label>"+dateOfDelivery+"</p>");

			pw.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Add\" type=\"submit\" /></p>");
			pw.println("</form></div></fieldset></table></article</div></div></body></html>");
		}

		pw.close();

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		String type="";
		type = request.getParameter("queryType");

		if(type==null)
		{
			doGet(request, response);
		}

		if(type.equals("addOrder"))
		{
			pw.println("<!doctype html><html>" +
					"<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
			pw.println("<title>Smart Potables</title>" +
					"<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
			pw.println("<body><div id=\"container\">");
			pw.println("<header><h1><a href=\"/\">Smart<span>Portables</span></a></h1><h3>Salesman Portal</h3></header>");
			pw.println("<nav><ul>");
			pw.println("<li class=\"start selected\">" +
					"<a href=\"SalesmanServlet\">Customer List</a></li>");
			pw.println("<li><a href=\"SalesmanServlet?type=createCustomer\">Create Customer</a></li>");
			pw.println("<li><a href=\"SalesmanServlet?type=addOrder\">Add Order</a></li>");
			pw.println("<li><a href=\"SalesmanServlet?type=updateOrder\">Update Order</a></li>");
			pw.println("<li><a href=\"SalesmanServlet?type=orderList\">Order List</a></li>");
			pw.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");

			String emailId1 = request.getParameter("customerEmailId");
			String address = request.getParameter("deliveryAddress");
			String productName = request.getParameter("itemName");
			Float productPrice = Float.parseFloat(request.getParameter("itemPrice"));
			Integer quantity = Integer.parseInt(request.getParameter("itemQty"));
			String orderid = request.getParameter("orderId");
			String orderId = "SP " +orderid;
			String dateOfOrder = request.getParameter("orderDate");
			String DateOfDelivery = request.getParameter("deliveryDate");

			MySqlDataStoreUtilities.insertOrder(productName, orderId, productPrice,quantity, dateOfOrder, DateOfDelivery, emailId1, address);

			ArrayList<String> orderItems = new ArrayList<String>();
			orderItems.add(productName);

			order = new Order(orderId, orderId, emailId1, dateOfOrder, DateOfDelivery, address, productPrice, orderItems);
			stringOrderHashMap = orderDataStore.getOrderHashMap();
			stringOrderHashMap.put(order.getorderId(), order);
			orderDataStore.writeOrderHashMap(stringOrderHashMap);

			pw.println("<h3><br><br>Order No "+orderId+" for Customer "+emailId1+" placed Succesfully.</h3><br><br>");
		}

		if(type.equals("deleteOrder"))
		{
			pw.println("<!doctype html><html>" +
					"<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
			pw.println("<title>Smart portables</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
			pw.println("<body>" +
					"<div id=\"container\">");
			pw.println("<header><h1><a href=\"/\">Smart<span>Portables</span></a></h1><h3>Salesman Area</h3></header>");
			pw.println("<nav><ul>");
			pw.println("<li class=\"start selected\">" +
					"<a href=\"SalesmanServlet?type=createCustomer\">Create Customer</a></li>");
			pw.println("<li><a href=\"SalesmanServlet?type=addOrder\">Add Order</a></li>");
			pw.println("<li><a href=\"SalesmanServlet?type=orderList\">Delete Order</a></li>");
			pw.println("<li><a href=\"SalesmanServlet?type=updateOrder\">Update Order</a></li>");
			pw.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");

			String currentCustomer = request.getParameter("customerEmailId");
			HashMap<String,OrderItem> orderItems;
			orderItems = MySqlDataStoreUtilities.getOrderDetails();

			String s;
			String s1;
			String s2;
			float s3;
			String s4;
			String s5;
			String s6;


			pw.println("<div id=\"body\">");
			pw.println("<table border=\"2\" bordercolor=\"#ff0000\">");
			pw.println("<article>" +
					"<h3 align=\"center\">Cancel Order</h3></th>");
			pw.println("<fieldset>" +
					"<div style=\"width:800; margin-right:auto; margin-left:auto;\">");
			pw.println("<tr><th>User Name:</th><th>Order Id</th><th>Item Name</th><th>Item Price</th><th>Order Date</th><th>Delivery Date</th><th>Shipping Address</th></tr>");
			stringOrderHashMap = orderDataStore.getOrderHashMap();


			for(Map.Entry<String,OrderItem> mapping : orderItems.entrySet()){

				OrderItem value = mapping.getValue();

				s = value.getCustomerEmailId();
				s1 = value.getOrderId();
				s2 = value.getItemName().toString();
				s3 = value.getItemPrice();
				s4 = value.getOrderDate();
				s5 = value.getDeliveryDate();
				s6 = value.getDeliveryAddress();

				if(currentCustomer.equals(s))
				{
					pw.println("<form action='CancelOrderServlet' method = 'post'>");
					pw.println("<input type='hidden' name='userid' value='"+s+"'>");
					pw.println("<input type='hidden' name='ordernum' value='"+s1+"'>");
					pw.println("<input type='hidden' name='delivery' value='"+s5+"'>");
					pw.println("<input type='hidden' name='itemName' value='"+s2+"'>");

					pw.println("<th>"+s+"</th>");
					pw.println("<th>"+s1+"</th>");
					pw.println("<th>"+s2+"</th>");
					pw.println("<th>"+s3+"</th>");
					pw.println("<th>"+s4+"</th>");
					pw.println("<th>"+s5+"</th>");
					pw.println("<th>"+s6+"</th>");

					pw.println("<td><input type='submit'  value='Cancel'></t></tr>");
					pw.println("</form>");
				}

			}
			pw.println("</table>");

			pw.println("</div></fieldset></article></div></div></body></html>");
		}

		if(type.equals("updateOrder"))
		{
			pw.println("<!doctype html>" +
					"<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
			pw.println("<title>Smart Portables</title>" +
					"<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
			pw.println("<body><div id=\"container\">");
			pw.println("<header><h1><a href=\"/\">Smart<span>Portables</span></a></h1><h3>SALESMAN AREA</h3></header>");
			pw.println("<nav>" +
					"<ul>");
			pw.println("<li class=\"start selected\"><a href=\"SalesmanServlet?type=createCustomer\">Create Customer</a></li>");
			pw.println("<li><a href=\"SalesmanServlet?type=addOrder\">ADD ORDER</a></li>");
			pw.println("<li><a href=\"SalesmanServlet?type=orderList\">DELETE ORDER</a></li>");
			pw.println("<li><a href=\"SalesmanServlet?type=updateOrder\">UPDATE ORDER</a></li>");

			pw.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");

			String thisOrder = request.getParameter("customerOrder");

			//stringOrderHashMap = orderDataStore.getOrderHashMap();
			HashMap<String,OrderItem> orderItems;
			orderItems = MySqlDataStoreUtilities.getOrderDetails();

			String s;
			String s1;
			String s2;
			float s3;
			String s4;
			String s5;
			String s6;

			pw.println("<div id=\"body\"><article><h3 align=\"center\">Update Order</h3>");
			pw.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");

			for(Map.Entry<String,OrderItem> mapping :orderItems.entrySet()){

				OrderItem value = mapping.getValue();

				s = value.getCustomerEmailId();
				s1 = value.getOrderId();
				s2 = value.getItemName();
				s3 = value.getItemPrice();
				s4 = value.getOrderId();
				s5 = value.getDeliveryDate();
				s6 = value.getDeliveryAddress();

				if(thisOrder.equals(s))
				{

					pw.println("<form action=\"UpdateOrder\" method=\"post\">");
					pw.println("<input type='hidden' name='queryType' value='updateOrder'>");
					pw.println("<input type='hidden' name='orderId' value='"+s1+"'>");
					pw.println("<input type='hidden' name='orderDate' value='"+s4+"'>");

					pw.println("<table style=\"width:100%\" style=\"height:100%\" border=\"2\" bordercolor=\"#ff0000\" cellspacing=\"0\" cellpadding=\"0\">");
					pw.println("<tr><th><p><label>Order Id: </label>"+s1+"</p></th></tr>");
					pw.println("<tr><th><p><label>Order Date: </label>"+s4+"</p></th></tr>");
					pw.println("<tr><th><p><label>User Name: </label>" +
							"<input name=\"customerEmailId\" id=\"email\" value='"+s+"' type=\"text\" /></p></th></tr>");
					pw.println("<tr><th><p><label>Item Name: </label>" +
							"<input name=\"itemName\" id=\"email\" value='"+s2+"' type=\"text\" /></p></th></tr>");
					pw.println("<tr><th><p><label>Delivery Address: </label><input name=\"deliveryAddress\" id=\"email\" value='"+s6+"' type=\"text\" /></p></th></tr>");

					pw.println("<tr><th><p><label>Item Price: </label>" +
							"<input name=\"itemPrice\" id=\"email\" value='"+s3+"' type=\"text\" /></p></th></tr>");

					pw.println("<tr><th><p><label>Delivery Date: </label><input name=\"deliveryDate\" id=\"email\" value='"+s5+"' type=\"text\" /></p></th></tr>");

					pw.println("<tr><th><p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Update Order\" type=\"submit\" /></p></th></tr></form>");
					pw.println("</table>");
				}

			}

		}
		pw.println("</div></fieldset></article</div></div></body></html>");
		pw.println("<footer>"+
				"<div class='footer-bottom'>"+
				"<p></p>"+
				"</div>"+
				"</footer>");


	}


}


