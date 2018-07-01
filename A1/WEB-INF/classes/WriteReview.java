

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class WriteReview extends HttpServlet {


	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		HttpSession session = request.getSession();
		String firstName = (String)session.getAttribute("firstName");
		String userId = (String)session.getAttribute("userid");

		pw.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		pw.println("<title>Smart Portables</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
		pw.println("<body onload='init()'>" +
				"<div id=\"container\">" +
				"<header><h1><a href=\"/\">Smart<span>Portables</span></a></h1>");

		pw.println("</header>");

		if(firstName != null && !firstName.isEmpty() && session != null)
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

		if(firstName != null && !firstName.isEmpty() && session != null)
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

		String sale = "Yes";
		String rebate = "No";
		String type = request.getParameter("productType");
		String name = request.getParameter("productName");
		double priceTotal = Double.parseDouble(request.getParameter("price"));
		String companyName = request.getParameter("company");
		String retailerName = request.getParameter("retailer");
		String colorName = request.getParameter("color");
		String condition = request.getParameter("condition");
		String imagePath = request.getParameter("image");
		String id = userId;
		DateFormat date = new SimpleDateFormat("MM/dd/yyyy");
		Date date1 = new Date();
		String dateOfReview = date.format(date1);


		pw.println("<div id=\"body\">" +
				"<table border=\"2\" bordercolor=\"#ff0000\"><th>" +
				"<article class=\"expanded\"><h3 align=\"center\">Write a Review</h3>");
		pw.println("<fieldset>" +
				"<div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
		pw.println("<form action=\"WriteReview\" method=\"POST\">");

		pw.println("<input type='hidden' name='type' value='"+type+"'>");
		pw.println("<input type='hidden' name='name' value='"+name+"'>");
		pw.println("<input type='hidden' name='price' value='"+priceTotal+"'>");
		pw.println("<input type='hidden' name='company' value='"+companyName+"'>");
		pw.println("<input type='hidden' name='sale' value='"+sale+"'>");
		pw.println("<input type='hidden' name='manufacturerRebate' value='"+rebate+"'>");
		pw.println("<input type='hidden' name='emailId' value='"+id+"'>");
		pw.println("<input type='hidden' name='reviewDate' value='"+dateOfReview+"'>");
		pw.println("<p><label><b>ProductCategory:</b></label>"+type+"</p>");
		pw.println("<p><label><b>ProductModelName:</b></label>"+name+"</p>");
		pw.println("<p><label><b>ProductPrice:</b></label>"+priceTotal+"</p>");
		pw.println("<p><label><b>RetailerName:</b></label><input name=\"retailer\" type=\"text\"></p>");
		pw.println("<p><label><b>RetailerZip:</b></label><input name=\"zip\" type=\"text\"></p>");
		pw.println("<p><label><b>RetailerCity:</b></label><input name=\"city\"type=\"text\" /></p>");
		pw.println("<p><label><b>RetailerState:</b></label><input name=\"state\"type=\"text\" /></p>");
		pw.println("<p><label><b>ProductOnSale:</b></label>"+sale+"</p>");
		pw.println("<p><label><b>ManufacturerName:</b></label>"+companyName+"</p>");
		pw.println("<p><label>ManufacturerRebate:</label>"+rebate+"</p>");
		pw.println("<p><label><b>UserID:</b></label>"+id+"</p>");
		pw.println("<p><label><b>UserAge:</b></label><input name=\"age\" type=\"text\"></p>");
		pw.println("<p><label><b>UserGender:</b></label><input name=\"gender\"type=\"text\" /></p>");
		pw.println("<p><label><b>UserOccupation:</b></label><input name=\"occupation\"type=\"text\" /></p>");
		pw.println("<p><label><b>ReviewRating:</b></label><select name='rating'><option name='rating' value='1' selected>1</option><option name='rating' value='2'>2</option>");
		pw.println("<option name='rating' value='3'>3</option><option name='rating' value='4'>4 </option><option name='rating' value='5'>5</option></select></p>");
		pw.println("<p><label><b>ReviewDate:</b></label>"+dateOfReview+"</p>");
		pw.println("<p><label><b>ReviewText:</b></label><textarea rows=\"4\" cols=\"50\" name=\"reviewText\"></textarea>");
		pw.println("<p><input name=\"send\" style=\"margin-left: 150px;\"  class=\"formbutton\" value=\"Submit Review\" type=\"submit\" /></p>");

		pw.println("</form></div></fieldset>" +
				"</th></table>"+
				"</article></div></div></body></html>");

		pw.close();

	}

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		HttpSession session = request.getSession();
		String fname = (String)session.getAttribute("firstName");
		String userId = (String)session.getAttribute("userid");

		pw.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		pw.println("<title>Smart Portables</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
		pw.println("</head>");
		pw.println("<body onload='init()'>" +
				"<div id=\"container\">" +
				"<header><h1><a href=\"/\">Smart<span>Portables</span></a></h1>");

		pw.println("</header>");

		if(fname != null && !fname.isEmpty() && session != null)
		{
			pw.println("<nav>" +
					"<ul>" +
					"<li class=\"start selected\"><a href=\"HomeServlet\">Welcome "+fname+"</a></li>");
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

		if(fname != null && !fname.isEmpty() && session != null)
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

		String type = request.getParameter("type");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		System.out.println(age);
		String gender = request.getParameter("gender");
		String occupation = request.getParameter("occupation");
		double price = Double.parseDouble(request.getParameter("price"));
		String retailer = request.getParameter("retailer");
		String zip = request.getParameter("zip");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String sale = request.getParameter("sale");
		System.out.println(sale);
		String company = request.getParameter("company");
		System.out.println(company);
		String reviewText = request.getParameter("reviewText");
		String manufacturerRebate = request.getParameter("manufacturerRebate");
		String emailId = request.getParameter("emailId");
		int reviewRating = Integer.parseInt(request.getParameter("rating"));
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date o = null;
		try{
			o = format.parse(request.getParameter("reviewDate"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		if(age != 0 && gender != null && gender.length() != 0 && occupation != null && occupation.length() != 0
				&& zip != null && zip.length() != 0
				&& reviewText != null && reviewText.length() != 0
				 && city != null && city.length() != 0
				&& state != null && state.length() != 0)
		{
			MongoDBDataStoreUtilities.getConnection();
			System.out.println("Connection created success!");
			MongoDBDataStoreUtilities.insertIntoReviewCollection(name, type, price, retailer, zip, city, state,
					sale, company, manufacturerRebate, emailId, age, gender,
					occupation, reviewRating, o, reviewText);

			pw.println("<p>Your Review has been saved.Please goto review page to see your Review");

		}
		else
		{
			pw.println("<p>Please fill all fields");
		}

	}

}