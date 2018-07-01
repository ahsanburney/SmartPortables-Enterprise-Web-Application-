

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.concurrent.*;


public class CancelOrderServlet extends HttpServlet {


	OrderDataStore orderDataStore;
	HashMap<String,Order> orders;

	public void init() throws ServletException{

		orderDataStore = new OrderDataStore();
		orders = new HashMap<String,Order>();
	}

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		PrintWriter pw = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");

		HttpSession httpSession = request.getSession();
		String firstName=(String)httpSession.getAttribute("firstName");

		pw.println("<!doctype html>" +
				"<html>" +
				"<head>" +
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		pw.println("<title>Smart Portables</title>" +
				"<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
		pw.println("<body onload='init()'>" +
				"<div id=\"container\">" +
				"<header>" +
				"<h1><a href=\"/\">Smart<span>Portables</span></a></h1>");
		pw.println("</header>");

		if(firstName != null && !firstName.isEmpty() && httpSession != null)
		{
			pw.println("<nav>" +
					"<ul>" +
					"<li class=\"start selected\"><a href=\"HomeServlet\">Welcome "+firstName+"</a></li>");
		}
		else
		{
			pw.println("<nav>" +
					"<ul>" +
					"<li class=\"start selected\"><a href=\"HomeServlet\">Home</a></li>");
		}

		pw.println("<li >" +
				"<a href=\"DataServlet?productType=Smartphones\">SmartPhones</a></li>");
		pw.println("<li>" +
				"<a href=\"DataServlet?productType=Speakers\">Speakers</a></li>");
		pw.println("<li>" +
				"<a href=\"DataServlet?productType=Laptops\">Laptops</a></li>");
		pw.println("<li>" +
				"<a href=\"DataServlet?productType=Accessories\">Accessories</a></li>");

		if(firstName != null && !firstName.isEmpty() && httpSession != null)
		{
			pw.println("<li>" +
					"<a href=\"ViewCartDetailServlet\">Cart</a></li>");
			pw.println("<li>" +
					"<a href=\"ViewOrders\">Your Orders</a></li>");
			pw.println("<li>" +
					"<a href=\"LogoutServlet\">Logout</a></li>");
		}
		else
		{
			pw.println("<li><a href=\"LoginServlet\">Login</a></li>");
			pw.println("<li><a href=\"registration.html\">Register</a></li>");
			pw.println("<li><a href=\"ViewCartDetailServlet\">Cart</a></li>");
		}

		pw.println("</ul>" +
				"</nav>");

		String email = request.getParameter("userid");
		String deliveryAddress= request.getParameter("delivery");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 5);
		Date date = new Date();
		String productName= request.getParameter("itemName");
		String number= request.getParameter("ordernum");
		String dateFormat = "MM/dd/yyyy";
		SimpleDateFormat format1 = new SimpleDateFormat(dateFormat);
		String format = format1.format(date);

		try {
			Date d1 = format1.parse(deliveryAddress);
			Date d2 = format1.parse(format);
			long diff = d1.getTime() - d2.getTime();
			long convert = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

			if(convert>5)
			{
				HashMap<String,OrderItem> orderItems;
				orderItems = MySqlDataStoreUtilities.getOrderDetails();
				for(Map.Entry<String,OrderItem> mapping :orderItems.entrySet())
				{
					String mappingKey = mapping.getKey();
					OrderItem value = mapping.getValue();
					String getorderId = value.getOrderId();
					String getItem = value.getItemName().toString();
					String getEmail = value.getCustomerEmailId();
					if(getorderId.equals(number) && getItem.equals(productName) && getEmail.equals(email))
					{

						MySqlDataStoreUtilities.cancelOrder(getorderId, getItem, getEmail);
						//orders.remove(getorderId);
						//orderDataStore.writeOrderHashMap(orders);
						//pw.println("<h3><br><br>Order cancelled Successfully<br><br><h3>");
						//RequestDispatcher view = request.getRequestDispatcher("ViewOrders");
						//view.forward(request, response);
					}
				}

			}
			else{
				pw.println("<h3><br><br>The order cannot be cancelled.It has already been shipped for delivery. <br><br><h3>");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");

		HttpSession httpSession = request.getSession();
		String firstName=(String)httpSession.getAttribute("firstName");

		pw.println("<!doctype html>" +
				"<html>" +
				"<head>" +
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		pw.println("<title>Smart Portables</title>" +
				"<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
		pw.println("<body onload='init()'>" +
				"<div id=\"container\">" +
				"<header>" +
				"<h1><a href=\"/\">Smart<span>Portables</span></a></h1>");
		pw.println("</header>");

		if(firstName != null && !firstName.isEmpty() && httpSession != null)
		{
			pw.println("<nav>" +
					"<ul>" +
					"<li class=\"start selected\"><a href=\"HomeServlet\">Welcome "+firstName+"</a></li>");
		}
		else
		{
			pw.println("<nav>" +
					"<ul>" +
					"<li class=\"start selected\"><a href=\"HomeServlet\">Home</a></li>");
		}

		pw.println("<li >" +
				"<a href=\"DataServlet?productType=Smartphones\">SmartPhones</a></li>");
		pw.println("<li>" +
				"<a href=\"DataServlet?productType=Speakers\">Speakers</a></li>");
		pw.println("<li>" +
				"<a href=\"DataServlet?productType=Laptops\">Laptops</a></li>");
		pw.println("<li>" +
				"<a href=\"DataServlet?productType=Accessories\">Accessories</a></li>");

		if(firstName != null && !firstName.isEmpty() && httpSession != null)
		{
			pw.println("<li>" +
					"<a href=\"ViewCartDetailServlet\">Cart</a></li>");
			pw.println("<li>" +
					"<a href=\"ViewOrders\">Your Orders</a></li>");
			pw.println("<li>" +
					"<a href=\"LogoutServlet\">Logout</a></li>");
		}
		else
		{
			pw.println("<li><a href=\"LoginServlet\">Login</a></li>");
			pw.println("<li><a href=\"registration.html\">Register</a></li>");
			pw.println("<li><a href=\"ViewCartDetailServlet\">Cart</a></li>");
		}

		pw.println("</ul>" +
				"</nav>");

		String email = request.getParameter("userid");
		String deliveryAddress= request.getParameter("delivery");

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 5);
		Date date = new Date();
		String dateFormat = "MM/dd/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		String s = simpleDateFormat.format(date);
		String productName= request.getParameter("itemName");
		String number= request.getParameter("ordernum");

		try {
			Date parse = simpleDateFormat.parse(deliveryAddress);
			Date parse1 = simpleDateFormat.parse(s);
			long difference = parse.getTime() - parse1.getTime();
			long diffOfdays = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);

			if(diffOfdays>5)
			{
				HashMap<String,OrderItem> orderItems;
				orderItems = MySqlDataStoreUtilities.getOrderDetails();

				for(Map.Entry<String,OrderItem> m :orderItems.entrySet())
				{
					String key = m.getKey();
					OrderItem c = m.getValue();

					String orderId = c.getOrderId();
					String item = c.getItemName();
					String emailid = c.getCustomerEmailId();

					if(orderId.equals(number) && item.equals(productName) && emailid.equals(email))
					{

						MySqlDataStoreUtilities.cancelOrder(orderId, item, emailid);
						//orderDataStore.writeOrderHashMap(orders);
						pw.println("<h3><br><br>The order has been cancelled from the order list<br><br><h3>");

					}
				}
				

			}
			else{
				pw.println("<h3><br><br>The order cannot be cancelled.It has already been shipped for delivery.<br><br><h3>");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		pw.close();
	}
}