

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import java.util.*;


public class ViewOrders extends HttpServlet {

	
	OrderDataStore orderDataStore;
	HashMap<String,Order> orders;

	public void init() throws ServletException{

		orderDataStore = new OrderDataStore();
		orders = new HashMap<String, Order>();
	}

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		PrintWriter pw = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();
		String firstName=(String)session.getAttribute("firstName");

		pw.println("<!doctype html>" +
				"<html><head>" +
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		pw.println("<title>Smart Portables</title>" +
				"<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");

		pw.println("<body onload='init()'><div id=\"container\">" +
				"<header><h1>" +
				"<a href=\"/\">Smart<span>Portables</span></a></h1>");


		pw.println("</header>");

		if(firstName != null && !firstName.isEmpty())
		{

			pw.println("<nav>" +
					"<ul>" +
					"<li class='start selected'><a href='HomeServlet'>Welcome "+firstName+"</a></li>");
		}
		else{
			pw.println("<nav><ul><li class=\"start selected\"><a href=\"HomeServlet\">Home</a></li>");
		}


		pw.println("<li class=\"\"><a href=\"DataServlet?productType=Smartphones\">SmartPhones</a></li>");
		pw.println("<li><a href=\"DataServlet?productType=Speakers\">Speakers</a></li>");
		pw.println("<li><a href=\"DataServlet?productType=Laptops\">Laptops</a></li>");
		pw.println("<li><a href=\"DataServlet?productType=Accessories\">Accessories</a></li>");

		if(firstName != null && !firstName.isEmpty())
		{
			pw.println("<li><a href=\"ViewCartDetailServlet\">Cart</a></li>");
			pw.println("<li><a href=\"ViewOrders\">Your Orders</a></li>");
			pw.println("<li><a href=\"LogoutServlet\">Logout</a></li>");
		}
		else
		{
			pw.println("<li><a href=\"LoginServlet\">Login</a></li>");
			pw.println("<li><a href=\"registration.html\">Signup</a></li>");
			pw.println("<li><a href=\"ViewCartDetailServlet\">Cart</a></li>");
		}

		pw.println("</ul></nav>");
		pw.println("<div id=\"body\">");
		pw.println("<section id=\"content\">");

		if(firstName==null)
		{
			pw.println("<h1>You are not logged in.Login and try again</h1>");
		}
		else
		{
			String usedkey=(String)session.getAttribute("userid");
			HashMap<String,OrderItem> orderItem;
			orderItem= MySqlDataStoreUtilities.getOrderDetails();
			HashMap<String,OrderItem> customerOrderItem;
			customerOrderItem= new HashMap<String, OrderItem>();
			for(Map.Entry<String,OrderItem> mapping :orderItem.entrySet())
			{
				String mappingKey = mapping.getKey();
				OrderItem value = mapping.getValue();
				if(value.getCustomerEmailId().equals(usedkey))
				{
					customerOrderItem.put(mappingKey, value);
				}

			}
			if(customerOrderItem.isEmpty()){
				pw.println("<h3>No Orders Present<h3>");
			}
			else
			{
				String email;
				String orderid;
				String nameOfItem;
				float priceOfItem;
				int itemQty;
				String dateOfOder;
				String dateOfDelivery;
				String Address;

				pw.println("<h3>Your Orders Details Is shown Below:</h3>");
				pw.println("<table border ='2' bordercolor ='#ff0000'>");
				pw.println("<tr><th>User Name:</th><th>Orderid</th><th>Name</th><th>Price</th><th>Item Qty</th><th>Date of Order</th><th>Expected date</th><th>Shipping Detail</th><th>Cancel</th></tr>");

				for(Map.Entry<String,OrderItem> mapping :customerOrderItem.entrySet()){
					OrderItem mappingValue = mapping.getValue();
					email = mappingValue.getCustomerEmailId();
					orderid = mappingValue.getOrderId();
					nameOfItem = mappingValue.getItemName();
					priceOfItem = mappingValue.getItemPrice();
					itemQty=mappingValue.getItemQty();
					dateOfOder = mappingValue.getOrderDate();
					dateOfDelivery = mappingValue.getDeliveryDate();
					Address = mappingValue.getDeliveryAddress();
					pw.println("<form action='CancelOrderServlet'>");
					pw.println("<input type='hidden' name='userid' value='"+email+"'>");
					pw.println("<input type='hidden' name='ordernum' value='"+orderid+"'>");
					pw.println("<input type='hidden' name='delivery' value='"+dateOfDelivery+"'>");
					pw.println("<input type='hidden' name='itemName' value='"+nameOfItem+"'>");
					pw.println("<th>"+email+"</th>");
					pw.println("<th>"+orderid+"</th>");
					pw.println("<th>"+nameOfItem+"</th>");
					pw.println("<th>"+priceOfItem+"</th>");
					pw.println("<th>"+itemQty+"</th>");
					pw.println("<th>"+dateOfOder+"</th>");
					pw.println("<th>"+dateOfDelivery+"</th>");
					pw.println("<th>"+Address+"</th>");
					pw.println("<td><input type='submit'  value='Cancel'></td></tr>");
					pw.println("</form>");

				}

				pw.println("</table>");
			}

		}
		pw.println("</article></section>");
		pw.println("<aside class=\"sidebar\">");
		pw.println("<ul>" +
				"<li>" +
				"<h4>Categories</h4>" +
				"<ul>");
		pw.println("<li>" +
				"<a href='DataServlet?productType=Smartphones'>SmartPhones</a></li>");
		pw.println("<li>" +
				"<a href=\"DataServlet?productType=Speakers\">Speakers</a></li>");
		pw.println("<li>" +
				"<a href=\"DataServlet?productType=Laptops\">Laptops</a></li>");
		pw.println("<li>" +
				"<a href=\"DataServlet?productType=Headphones\">HeadPhones</a></li>");
		pw.println("<li>" +
				"<a href=\"DataServlet?productType=Smartwatch\">SmartWatches</a></li>");
		pw.println("<li>" +
				"<a href=\"DataServlet?productType=Accessories\">External Storage</a></li>");
		pw.println("</ul>" +
				"</li></aside>" +
				"<div class=\"clear\">" +
				"</div></div>");
		pw.println("<footer>"+
				"<div class='footer-bottom'>"+
				"<p></p>"+
				"</div>"+
				"</footer>");
		pw.println("</body></html>");
		pw.close();
	}
}