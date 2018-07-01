

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.*;
import java.util.Set;
import java.util.Date;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class ViewReviews extends HttpServlet {
	

	
	public void init() throws ServletException{

		
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

		String attribute=(String)httpSession.getAttribute("userid");
		String name = request.getParameter("productName");
		HashMap<String,Review> reviewHashMap;
		reviewHashMap = MongoDBDataStoreUtilities.fetchReviewsFromDB();
		HashMap<String,Review> thisProductReviews;
		thisProductReviews = new HashMap<String, Review>();

		for(Map.Entry<String,Review> mapping :reviewHashMap.entrySet())
		{
			String mappingKey = mapping.getKey();
			Review value = mapping.getValue();
			
			if(value.getProductName().equals(name))
			{
				thisProductReviews.put(mappingKey, value);
			}
			
		}

		if(thisProductReviews.isEmpty()){
			pw.println("<h3>No Reviews present at this time<h3>");
		}
		else	
		{
			String s;
			String emailId;
			int reviewRating;
			String company;
			String zip;
			Date datedate;
			String reviewText;
			String city;
			
			pw.println("<h3 align='center'>Product Reviews</h3>");
			pw.println("<table border='2' bordercolor=\"#ff0000\"><th>");
			pw.println("<tr><th>Sr.Number</th><th>Product Model Name</th><th>Email</th><th>ReviewRating</th><th>ReviewDate</th><th>RetailerName</th><th>RetailerZip</th><th>RetailerCity</th><th>ReviewText</th></tr>");
				
			int count=1;
			
				for(Map.Entry<String,Review> reviewEntry :thisProductReviews.entrySet()){
					
					Review value = reviewEntry.getValue();
					s = value.getProductName();
					zip = value.getRetailerZip();
					city = value.getRetailerCity();
					emailId = value.getEmailId();
					reviewRating = value.getReviewRating();
					company = value.getRetailer();
					DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
					datedate = value.getReviewDate();
					String reviewDate = format.format(datedate);
					reviewText = value.getReviewText();
					
					pw.println("<tr><th>"+count+"</th>");
					pw.println("<th>"+s+"</th>");
					pw.println("<th>"+emailId+"</th>");
					pw.println("<th>"+reviewRating+"</th>");
					pw.println("<th>"+reviewDate+"</th>");
					pw.println("<th>"+company+"</th>");
					pw.println("<th>"+zip+"</th>");
					pw.println("<th>"+city+"</th>");
					pw.println("<th>"+reviewText+"</th></tr>");
					
					count++;
					
				}
			
			pw.println("</table>");	
		}

}
}