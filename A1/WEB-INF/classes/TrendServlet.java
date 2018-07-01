

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class TrendServlet extends HttpServlet
{

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		HttpSession httpSession = request.getSession(false);
		String firstName = null;
		if(httpSession != null)
		{
			firstName=(String)httpSession.getAttribute("firstName");
		}
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
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
		pw.println("<div id=\"body\">" +
				"<section id=\"content\">");
		
		String productCategory = request.getParameter("type");
		if(productCategory.equals("FiveMostLiked"))
		{
			LinkedHashMap<String, Double> fiveMostLiked;
			fiveMostLiked = MongoDBDataStoreUtilities.fiveMostLikedProducts();
			pw.println("<h3 align='center'>Top five most liked products</h3>");
			pw.println("<table border='2' bordercolor=\"#ff0000\">");
			pw.println("<tr><th><b>Product Name</b></th><th><b>Rating</b></th></tr>");
			for(Map.Entry<String, Double> mapping :fiveMostLiked.entrySet())
			{
				String key = mapping.getKey();
				Double value = mapping.getValue();
				pw.println("<tr><th>"+key+"</th>");
				pw.println("<th>"+value+"</th></tr>");
			}
			pw.println("</table>");
		}
		if(productCategory.equals("FiveZipCodes"))
		{
			LinkedHashMap<String, Integer> fiveZipCodes;
			fiveZipCodes = MongoDBDataStoreUtilities.fiveZipcodesOfMostSold();
			pw.println("<h3 align='center'>Top five zip-codes where maximum number of products sold</h3>");
			pw.println("<table border='2' bordercolor=\"#ff0000\">");
			pw.println("<tr><th><b>Zip Codes</b></th><th><b>Products Sold</b></th></tr>");
			for(Map.Entry<String, Integer> mapping :fiveZipCodes.entrySet())
			{
				String key = mapping.getKey();
				Integer value = mapping.getValue();
				pw.println("<tr><th>"+key+"</th>");
				pw.println("<th>"+value+"</th></tr>");
			}
			
			pw.println("</table>");
		}
		
	if(productCategory.equals("FiveMostSold"))
		{
			String name;
			float price;
			int quantity;
			LinkedHashMap<String, ArrayList<Object>> topFiveMostSoldProducts;
			topFiveMostSoldProducts = MySqlDataStoreUtilities.TopFiveMostSoldProducts();
			pw.println("<h3 align='center'>Top five most sold products regardless of the rating</h3>");
			pw.println("<table border='2' bordercolor=\"#ff0000\">");
			pw.println("<tr><th><b>Product Model Name</b></th><th><b>Product Price</b></th><th><b>Quantity Sold</b></th></tr>");
			for(Map.Entry<String, ArrayList<Object>> m :topFiveMostSoldProducts.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				name = (String)values.get(0);
				price = (Float)values.get(1);
				quantity = (Integer)values.get(2);
				pw.println("<tr><th>"+name+"</th>");
				pw.println("<th>"+price+"</th>");
				pw.println("<th>"+quantity+"</th></tr>");
			}
			pw.println("</table>");
		}
		pw.println("</section>");

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
		pw.println("</ul>");

		pw.println("<ul><li><h4>Trending</h4>" +
				"<ul>");
		pw.println("<li>" +
				"<a href=\"TrendServlet?type=FiveMostLiked\">Top five most liked products</a>" +
				"</li>");
		pw.println("<li>" +
				"<a href=\"TrendServlet?type=FiveZipCodes\">Top Five Zip Codes where maximum number of products sold</a>" +
				"</li>");
		pw.println("<li>" +
				"<a href=\"TrendServlet?type=FiveMostSold\">Top five most sold products</a>" +
				"</li>" +
				"</ul>"+
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
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}