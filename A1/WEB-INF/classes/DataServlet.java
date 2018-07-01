

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class DataServlet extends HttpServlet
{
	ArrayList<Object> products;
	HashMap<String, Smartphone> phones;
	HashMap<String, Laptop> lappy;
	HashMap<String, Speaker> speakers;
	HashMap<String, Smartwatch> watches;
	HashMap<String, Accessory> accessories;
	HashMap<String, Headphone> head;
	HashMap<String, WarrantyServlet> warranty;

	ProductSaxParser productSaxParser = new ProductSaxParser();

	void loadXMLFile()
	{
		try{
			products = productSaxParser.loadDataStore();

			phones = (HashMap<String,Smartphone>)products.get(0);
			lappy = (HashMap<String, Laptop>)products.get(1);
			accessories = (HashMap<String, Accessory>)products.get(2);
			watches = (HashMap<String, Smartwatch>)products.get(3);
			speakers= (HashMap<String, Speaker>)products.get(4);
			head = (HashMap<String, Headphone>)products.get(5);
			warranty = (HashMap<String, WarrantyServlet>)products.get(6);

		}catch(Exception E){
			System.out.println("Exception Occured");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		loadXMLFile();

		HttpSession httpSession = request.getSession(false);
		String firstName = null;

		if(httpSession != null)
		{
			firstName=(String)httpSession.getAttribute("firstName");
		}

		String typeOfProduct = request.getParameter("productType");

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		pw.println("<!doctype html>" +
				"<html>" +
				"<head>" +
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		pw.println("<title>Smart Portables</title>" +
				"<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
		pw.println("<script type=\"text/javascript\" src=\"autoComplete.js\"></script></head>");
		pw.println("<body onload=\"init()\">");
		pw.println("<div id=\"container\">"+
				"<header>" +
				"<h1><a href=\"/\">Smart<span>Portables</span></a></h1>");
		pw.println("<form  name='autofillform1' action=''>");
		pw.println("<div name='autofillform'>");
		pw.println("<strong>SEARCH PRODUCTS HERE: </strong>");
		pw.println("<input type='text' name='searchId' size='40' id='searchId' onkeyup='doCompletion()' placeholder='Search Here...'><div id='auto-row'>");
		pw.println("<table border='0' id='complete-table' class='popupBox'></table>");
		pw.println("</div></div></form></header>");
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

		pw.println("<div id=\"body\">");
		pw.println("<section id=\"content\">");

//==================================================================Display Area Starts=======================================
		if(typeOfProduct.equals("Smartphones"))
		{
			pw.println("<article><h3>Smartphones</h3></article>");
			pw.println("<article>");

			for(Map.Entry<String,Smartphone> mapping : phones.entrySet()){
				Smartphone smartphone = mapping.getValue();

				float manfRebate = smartphone.getMafRebate();
				float retDiscount = smartphone.getReDiscount();
				float originalPrice = smartphone.getPrice();

				float newPrice = (((100 - manfRebate)/100) * originalPrice) * ((100 - retDiscount)/100);
				newPrice = Math.round(newPrice*100)/100;


				pw.println("<table style=\"width:100%\" style=\"height:100%\" border=\"2\" bordercolor=\"#ff0000\" cellspacing=\"0\" cellpadding=\"0\">");
				pw.println("<tr><th width=\"30%\">");
				pw.println("<a href=\"ViewProduct\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
				pw.println(smartphone.getImage());
				pw.println("\" /></a>");
				pw.println("</th>");
				pw.println("<th width=\"40%\"><table border=\"2\" bordercolor=\"#ff0000\"><tr><th width=\"40\"><b>");
				pw.println(smartphone.getName());
				pw.println("</b></th></tr><tr><th width=\"40\"><b>Company:</b>");
				pw.println(smartphone.getCompany());
				pw.println("</b></th></tr><tr><th width=\"40\"><b>Color:</b>");
				pw.println(smartphone.getColor());
				pw.println("</th></tr><tr><td><b>Condition:</b>");
				pw.println(smartphone.getCondition());
				pw.println("</th></tr><tr><th><b>Manufacturer Rebates:</b>");
				pw.println(smartphone.getMafRebate()+"%");
				pw.println("</th></tr><tr><th><b>Retailer Discounts:</b>");
				pw.println(smartphone.getReDiscount()+"%");
				pw.println("</th></tr></table></th>");
				pw.println("<th width=\"30%\"><table bordercolor=\"#ff0000\" border=\"2\"><tr><th><b>Price:");
				pw.println(smartphone.getPrice()+"$");
				pw.println("<tr><th><b>Price after Discount:");
				pw.println(newPrice+"$");

				if(firstName != null && !firstName.isEmpty())
				{


					pw.println("<tr><th><form method = 'get' action = 'ViewProduct'>");
					pw.println("<input class = 'formbutton' type = 'submit' name = '"+ smartphone.getName() +"' value = 'View Product Details'>");
					pw.println("<input type='hidden' name='productType' value='"+typeOfProduct+"'>");
					pw.println("<input type='hidden' name='productName' value='"+smartphone.getName()+"'>");
					pw.println("<input type='hidden' name='image' value='"+smartphone.getImage()+"'>");
					pw.println("<input type='hidden' name='price' value='"+smartphone.getPrice()+"'>");
					pw.println("<input type='hidden' name='color' value='"+smartphone.getColor()+"'>");
					pw.println("<input type='hidden' name='condition' value='"+smartphone.getCondition()+"'>");
					pw.println("<input type='hidden' name='company' value='"+smartphone.getCompany()+"'>");
					pw.println("<input type='hidden' name='retailer' value='"+smartphone.getRetailer()+"'>");
					pw.println("<input type='hidden' name='manf' value='"+manfRebate+"'>");
					pw.println("<input type='hidden' name='ret' value='"+retDiscount+"'>");
					pw.println("<input type='hidden' name='newPrice' value='"+newPrice+"'>");
					pw.println("<input type='hidden' name='quantity' value='"+1+"'>");
					pw.println("</form></td></tr>");

					pw.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
					pw.println("<input class = 'formbutton' type = 'submit' name = '"+ smartphone.getName() +"' value = 'View Reviews'>");
					pw.println("<input type='hidden' name='productType' value='"+typeOfProduct+"'>");
					pw.println("<input type='hidden' name='productName' value='"+smartphone.getName()+"'>");
					pw.println("<input type='hidden' name='image' value='"+smartphone.getImage()+"'>");
					pw.println("<input type='hidden' name='price' value='"+smartphone.getPrice()+"'>");
					pw.println("<input type='hidden' name='color' value='"+smartphone.getColor()+"'>");
					pw.println("<input type='hidden' name='condition' value='"+smartphone.getCondition()+"'>");
					pw.println("<input type='hidden' name='company' value='"+smartphone.getCompany()+"'>");
					pw.println("<input type='hidden' name='retailer' value='"+smartphone.getRetailer()+"'>");
					pw.println("<input type='hidden' name='quantity' value='"+1+"'>");
					pw.println("</form></td></tr>");

					pw.println("<tr><td><form method = 'get' action = 'WriteReview'>");
					pw.println("<input class = 'formbutton' type = 'submit' name = '"+ smartphone.getName() +"' value = 'Write Review'>");
					pw.println("<input type='hidden' name='productType' value='"+typeOfProduct+"'>");
					pw.println("<input type='hidden' name='productName' value='"+smartphone.getName()+"'>");
					pw.println("<input type='hidden' name='image' value='"+smartphone.getImage()+"'>");
					pw.println("<input type='hidden' name='price' value='"+smartphone.getPrice()+"'>");
					pw.println("<input type='hidden' name='color' value='"+smartphone.getColor()+"'>");
					pw.println("<input type='hidden' name='condition' value='"+smartphone.getCondition()+"'>");
					pw.println("<input type='hidden' name='company' value='"+smartphone.getCompany()+"'>");
					pw.println("<input type='hidden' name='retailer' value='"+smartphone.getRetailer()+"'>");
					pw.println("<input type='hidden' name='quantity' value='"+1+"'>");
					pw.println("</form></td></tr>");

					pw.println("<tr><th><form method = 'get' action = 'AddingProductToCart'>");
					pw.println("<input class = 'formbutton' type = 'submit' name = '"+ smartphone.getName() +"' value = 'Add to Cart'>");
					pw.println("<input type='hidden' name='productName' value='"+smartphone.getName()+"'>");
					pw.println("<input type='hidden' name='productType' value='"+typeOfProduct+"'>");
					pw.println("<input type='hidden' name='company' value='"+smartphone.getCompany()+"'>");
					pw.println("<input type='hidden' name='image' value='"+smartphone.getImage()+"'>");
					pw.println("<input type='hidden' name='price' value='"+newPrice+"'>");
					pw.println("<input type='hidden' name='quantity' value='"+1+"'>");
					pw.println("</form></td></tr>");

					pw.println("</table></th></tr></table>");
				}
			}
		}

		if(typeOfProduct.equals("Speakers"))
		{
			pw.println("<article><h3>Speakers</h3></article>");
			pw.println("<article class=\"expanded\">");

			for(Map.Entry<String,Speaker> mapping :speakers.entrySet()){
				Speaker value = mapping.getValue();

				float manfRebate = value.getMafRebate();
				float retDiscount = value.getReDiscount();
				float originalPrice = value.getPrice();

				float newPrice = (((100 - manfRebate)/100) * originalPrice) * ((100 - retDiscount)/100);
				newPrice = Math.round(newPrice*100)/100;

				pw.println("<table style=\"width:100%\" style=\"height:100%\" border=\"2\" bordercolor=\"#ff0000\" cellspacing=\"0\" cellpadding=\"0\">");
				pw.println("<tr><th width=\"30%\">");
				pw.println("<a href=\"ViewProduct\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
				pw.println(value.getImage());
				pw.println("\" /></a>");
				pw.println("</th>");
				pw.println("<th width=\"40%\"><table border=\"2\"  bordercolor=\"#ff0000\"><tr><th width=\"40\"><b>");
				pw.println(value.getName());
				pw.println("</b></th></tr><tr><th width=\"40\"><b>Company:</b>");
				pw.println(value.getCompany());
				pw.println("</b></th></tr><tr><th width=\"40\"><b>Color:</b>");
				pw.println(value.getColor());
				pw.println("</th></tr><tr><th><b>Condition:</b>");
				pw.println(value.getCondition());
				pw.println("</th></tr><tr><td><b>Manufacturer Rebates:</b>");
				pw.println(value.getMafRebate()+"%");
				pw.println("</th></tr><tr><th><b>Retailer Discounts:</b>");
				pw.println(value.getReDiscount()+"%");
				pw.println("</th></tr></table></th>");
				pw.println("<th width=\"30%\"><table  bordercolor=\"#ff0000\" border=\"2\"><tr><th><b>Price:");
				pw.println(value.getPrice()+"$");
				pw.println("<tr><th><b>Price after Discount:");
				pw.println(newPrice+"$");

				if(firstName != null && !firstName.isEmpty())
				{


					pw.println("<tr><th><form method = 'get' action = 'ViewProduct'>");
					pw.println("<input class = 'formbutton' type = 'submit' name = '"+ value.getName() +"' value = 'View Product Details'>");
					pw.println("<input type='hidden' name='productType' value='"+typeOfProduct+"'>");
					pw.println("<input type='hidden' name='productName' value='"+value.getName()+"'>");
					pw.println("<input type='hidden' name='image' value='"+value.getImage()+"'>");
					pw.println("<input type='hidden' name='price' value='"+value.getPrice()+"'>");
					pw.println("<input type='hidden' name='color' value='"+value.getColor()+"'>");
					pw.println("<input type='hidden' name='condition' value='"+value.getCondition()+"'>");
					pw.println("<input type='hidden' name='company' value='"+value.getCompany()+"'>");
					pw.println("<input type='hidden' name='retailer' value='"+value.getRetailer()+"'>");
					pw.println("<input type='hidden' name='manf' value='"+manfRebate+"'>");
					pw.println("<input type='hidden' name='ret' value='"+retDiscount+"'>");
					pw.println("<input type='hidden' name='newPrice' value='"+newPrice+"'>");
					pw.println("<input type='hidden' name='quantity' value='"+1+"'>");
					pw.println("</form></th></tr>");

					pw.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
					pw.println("<input class = 'formbutton' type = 'submit' name = '"+ value.getName() +"' value = 'View Reviews'>");
					pw.println("<input type='hidden' name='productType' value='"+typeOfProduct+"'>");
					pw.println("<input type='hidden' name='productName' value='"+value.getName()+"'>");
					pw.println("<input type='hidden' name='image' value='"+value.getImage()+"'>");
					pw.println("<input type='hidden' name='price' value='"+value.getPrice()+"'>");
					pw.println("<input type='hidden' name='color' value='"+value.getColor()+"'>");
					pw.println("<input type='hidden' name='condition' value='"+value.getCondition()+"'>");
					pw.println("<input type='hidden' name='company' value='"+value.getCompany()+"'>");
					pw.println("<input type='hidden' name='retailer' value='"+value.getRetailer()+"'>");
					pw.println("<input type='hidden' name='quantity' value='"+1+"'>");
					pw.println("</form></td></tr>");

					pw.println("<tr><td><form method = 'get' action = 'WriteReview'>");
					pw.println("<input class = 'formbutton' type = 'submit' name = '"+ value.getName() +"' value = 'Write Review'>");
					pw.println("<input type='hidden' name='productType' value='"+typeOfProduct+"'>");
					pw.println("<input type='hidden' name='productName' value='"+value.getName()+"'>");
					pw.println("<input type='hidden' name='image' value='"+value.getImage()+"'>");
					pw.println("<input type='hidden' name='price' value='"+value.getPrice()+"'>");
					pw.println("<input type='hidden' name='color' value='"+value.getColor()+"'>");
					pw.println("<input type='hidden' name='condition' value='"+value.getCondition()+"'>");
					pw.println("<input type='hidden' name='company' value='"+value.getCompany()+"'>");
					pw.println("<input type='hidden' name='retailer' value='"+value.getRetailer()+"'>");
					pw.println("<input type='hidden' name='quantity' value='"+1+"'>");
					pw.println("</form></td></tr>");

					pw.println("<tr><th><form method = 'get' action = 'AddingProductToCart'>");
					pw.println("<input class = 'formbutton' type = 'submit' name = '"+ value.getName() +"' value = 'Add to Cart'>");
					pw.println("<input type='hidden' name='productName' value='"+value.getName()+"'>");
					pw.println("<input type='hidden' name='productType' value='"+typeOfProduct+"'>");
					pw.println("<input type='hidden' name='company' value='"+value.getCompany()+"'>");
					pw.println("<input type='hidden' name='image' value='"+value.getImage()+"'>");
					pw.println("<input type='hidden' name='price' value='"+newPrice+"'>");
					pw.println("<input type='hidden' name='quantity' value='"+1+"'>");
					pw.println("</form></th></tr>");

					pw.println("</table></th></tr></table>");
				}
			}
		}

		if(typeOfProduct.equals("Warranties"))
		{
			pw.println("<article><h3>Warranty Cards</h3></article>");
			pw.println("<article class=\"expanded\">");

			for(Map.Entry<String,WarrantyServlet> mapping : warranty.entrySet()){
				WarrantyServlet value = mapping.getValue();

				float manfRebate = value.getMafRebate();
				float retDiscount = value.getReDiscount();
				float originalPrice = value.getPrice();

				float newPrice = (((100 - manfRebate)/100) * originalPrice) * ((100 - retDiscount)/100);
				newPrice = Math.round(newPrice*100)/100;

				pw.println("<table style=\"width:100%\" style=\"height:100%\" border=\"2\" bordercolor=\"#ff0000\" cellspacing=\"0\" cellpadding=\"0\">");
				pw.println("<tr><th width=\"30%\">");
				pw.println("<a href=\"ProductDetails.html\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
				pw.println(value.getImage());
				pw.println("\" /></a>");
				pw.println("</th>");
				pw.println("<th width=\"40%\"><table border=\"2\"  bordercolor=\"#ff0000\"><tr><td width=\"40\"><b>");
				pw.println(value.getName());
				pw.println("</b></th></tr><tr><th width=\"40\"><b>Company:</b>");
				pw.println(value.getCompany());
				pw.println("</b></th></tr><tr><th width=\"40\"><b>Color:</b>");
				pw.println(value.getColor());
				pw.println("</th></tr><tr><th><b>Condition:</b>");
				pw.println(value.getCondition());
				pw.println("</th></tr><tr><th><b>Manufacturer Rebates:</b>");
				pw.println(value.getMafRebate()+"%");
				pw.println("</th></tr><tr><th><b>Retailer Discounts:</b>");
				pw.println(value.getReDiscount()+"%");
				pw.println("</th></tr></table></th>");
				pw.println("<th width=\"30%\"><table border=\"2\"  bordercolor=\"#ff0000\"><tr><th><b>Price:");
				pw.println(value.getPrice()+"$");
				pw.println("<tr><th><b>Price after Discount:");
				pw.println(newPrice+"$");

				if(firstName != null && !firstName.isEmpty())
				{


					pw.println("<tr><th><form method = 'get' action = 'ViewProduct'>");
					pw.println("<input class = 'formbutton' type = 'submit' name = '"+ value.getName() +"' value = 'View Product Details'>");
					pw.println("<input type='hidden' name='productType' value='"+typeOfProduct+"'>");
					pw.println("<input type='hidden' name='productName' value='"+value.getName()+"'>");
					pw.println("<input type='hidden' name='image' value='"+value.getImage()+"'>");
					pw.println("<input type='hidden' name='price' value='"+value.getPrice()+"'>");
					pw.println("<input type='hidden' name='color' value='"+value.getColor()+"'>");
					pw.println("<input type='hidden' name='condition' value='"+value.getCondition()+"'>");
					pw.println("<input type='hidden' name='company' value='"+value.getCompany()+"'>");
					pw.println("<input type='hidden' name='retailer' value='"+value.getRetailer()+"'>");
					pw.println("<input type='hidden' name='manf' value='"+manfRebate+"'>");
					pw.println("<input type='hidden' name='ret' value='"+retDiscount+"'>");
					pw.println("<input type='hidden' name='newPrice' value='"+newPrice+"'>");
					pw.println("<input type='hidden' name='quantity' value='"+1+"'>");
					pw.println("</form></th></tr>");

					pw.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
					pw.println("<input class = 'formbutton' type = 'submit' name = '"+ value.getName() +"' value = 'View Reviews'>");
					pw.println("<input type='hidden' name='productType' value='"+typeOfProduct+"'>");
					pw.println("<input type='hidden' name='productName' value='"+value.getName()+"'>");
					pw.println("<input type='hidden' name='image' value='"+value.getImage()+"'>");
					pw.println("<input type='hidden' name='price' value='"+value.getPrice()+"'>");
					pw.println("<input type='hidden' name='color' value='"+value.getColor()+"'>");
					pw.println("<input type='hidden' name='condition' value='"+value.getCondition()+"'>");
					pw.println("<input type='hidden' name='company' value='"+value.getCompany()+"'>");
					pw.println("<input type='hidden' name='retailer' value='"+value.getRetailer()+"'>");
					pw.println("<input type='hidden' name='quantity' value='"+1+"'>");
					pw.println("</form></td></tr>");

					pw.println("<tr><td><form method = 'get' action = 'WriteReview'>");
					pw.println("<input class = 'formbutton' type = 'submit' name = '"+ value.getName() +"' value = 'Write Review'>");
					pw.println("<input type='hidden' name='productType' value='"+typeOfProduct+"'>");
					pw.println("<input type='hidden' name='productName' value='"+value.getName()+"'>");
					pw.println("<input type='hidden' name='image' value='"+value.getImage()+"'>");
					pw.println("<input type='hidden' name='price' value='"+value.getPrice()+"'>");
					pw.println("<input type='hidden' name='color' value='"+value.getColor()+"'>");
					pw.println("<input type='hidden' name='condition' value='"+value.getCondition()+"'>");
					pw.println("<input type='hidden' name='company' value='"+value.getCompany()+"'>");
					pw.println("<input type='hidden' name='retailer' value='"+value.getRetailer()+"'>");
					pw.println("<input type='hidden' name='quantity' value='"+1+"'>");
					pw.println("</form></td></tr>");

					pw.println("<tr><th><form method = 'get' action = 'AddingProductToCart'>");
					pw.println("<input class = 'formbutton' type = 'submit' name = '"+ value.getName() +"' value = 'Add to Cart'>");
					pw.println("<input type='hidden' name='productName' value='"+value.getName()+"'>");
					pw.println("<input type='hidden' name='productType' value='"+typeOfProduct+"'>");
					pw.println("<input type='hidden' name='company' value='"+value.getCompany()+"'>");
					pw.println("<input type='hidden' name='image' value='"+value.getImage()+"'>");
					pw.println("<input type='hidden' name='price' value='"+newPrice+"'>");
					pw.println("<input type='hidden' name='quantity' value='"+1+"'>");
					pw.println("</form></th></tr>");

					pw.println("</table></th></tr></table>");
				}
			}
		}

		if(typeOfProduct.equals("Headphones"))
		{
			pw.println("<article><h3>HeadPhones</h3></article>");
			pw.println("<article class=\"expanded\">");

			for(Map.Entry<String,Headphone> mapping : head.entrySet()){
				Headphone value = mapping.getValue();

				float manfRebate = value.getMafRebate();
				float retDiscount = value.getReDiscount();
				float originalPrice = value.getPrice();

				float newPrice = (((100 - manfRebate)/100) * originalPrice) * ((100 - retDiscount)/100);
				newPrice = Math.round(newPrice*100)/100;

				pw.println("<table style=\"width:100%\" style=\"height:100%\" border=\"2\" bordercolor=\"#ff0000\" cellspacing=\"0\" cellpadding=\"0\">");
				pw.println("<tr><th width=\"30%\">");
				pw.println("<a href=\"ViewProduct\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
				pw.println(value.getImage());
				pw.println("\" /></a>");
				pw.println("</th>");
				pw.println("<th width=\"40%\"><table border=\"2\"  bordercolor=\"#ff0000\"><tr><th width=\"40\"><b>");
				pw.println(value.getName());
				pw.println("</b></th></tr><tr><th width=\"40\"><b>Company:</b>");
				pw.println(value.getCompany());
				pw.println("</b></th></tr><tr><th width=\"40\"><b>Color:</b>");
				pw.println(value.getColor());
				pw.println("</th></tr><tr><th><b>Condition:</b>");
				pw.println(value.getCondition());
				pw.println("</th></tr><tr><th><b>Manufacturer Rebates:</b>");
				pw.println(value.getMafRebate()+"%");
				pw.println("</th></tr><tr><th><b>Retailer Discounts:</b>");
				pw.println(value.getReDiscount()+"%");
				pw.println("</th></tr></table></th>");
				pw.println("<th width=\"30%\"><table border=\"2\"  bordercolor=\"#ff0000\"><tr><th><b>Price:");
				pw.println(value.getPrice()+"$");
				pw.println("<tr><th><b>Price after Discount:");
				pw.println(newPrice+"$");

				if(firstName != null && !firstName.isEmpty())
				{


					pw.println("<tr><th><form method = 'get' action = 'ViewProduct'>");
					pw.println("<input class = 'formbutton' type = 'submit' name = '"+ value.getName() +"' value = 'View Product Details'>");
					pw.println("<input type='hidden' name='productType' value='"+typeOfProduct+"'>");
					pw.println("<input type='hidden' name='productName' value='"+value.getName()+"'>");
					pw.println("<input type='hidden' name='image' value='"+value.getImage()+"'>");
					pw.println("<input type='hidden' name='price' value='"+value.getPrice()+"'>");
					pw.println("<input type='hidden' name='color' value='"+value.getColor()+"'>");
					pw.println("<input type='hidden' name='condition' value='"+value.getCondition()+"'>");
					pw.println("<input type='hidden' name='company' value='"+value.getCompany()+"'>");
					pw.println("<input type='hidden' name='retailer' value='"+value.getRetailer()+"'>");
					pw.println("<input type='hidden' name='newPrice' value='"+newPrice+"'>");
					pw.println("<input type='hidden' name='quantity' value='"+1+"'>");
					pw.println("</form></th></tr>");

					pw.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
					pw.println("<input class = 'formbutton' type = 'submit' name = '"+ value.getName() +"' value = 'View Reviews'>");
					pw.println("<input type='hidden' name='productType' value='"+typeOfProduct+"'>");
					pw.println("<input type='hidden' name='productName' value='"+value.getName()+"'>");
					pw.println("<input type='hidden' name='image' value='"+value.getImage()+"'>");
					pw.println("<input type='hidden' name='price' value='"+value.getPrice()+"'>");
					pw.println("<input type='hidden' name='color' value='"+value.getColor()+"'>");
					pw.println("<input type='hidden' name='condition' value='"+value.getCondition()+"'>");
					pw.println("<input type='hidden' name='company' value='"+value.getCompany()+"'>");
					pw.println("<input type='hidden' name='retailer' value='"+value.getRetailer()+"'>");
					pw.println("<input type='hidden' name='quantity' value='"+1+"'>");
					pw.println("</form></td></tr>");

					pw.println("<tr><td><form method = 'get' action = 'WriteReview'>");
					pw.println("<input class = 'formbutton' type = 'submit' name = '"+ value.getName() +"' value = 'Write Review'>");
					pw.println("<input type='hidden' name='productType' value='"+typeOfProduct+"'>");
					pw.println("<input type='hidden' name='productName' value='"+value.getName()+"'>");
					pw.println("<input type='hidden' name='image' value='"+value.getImage()+"'>");
					pw.println("<input type='hidden' name='price' value='"+value.getPrice()+"'>");
					pw.println("<input type='hidden' name='color' value='"+value.getColor()+"'>");
					pw.println("<input type='hidden' name='condition' value='"+value.getCondition()+"'>");
					pw.println("<input type='hidden' name='company' value='"+value.getCompany()+"'>");
					pw.println("<input type='hidden' name='retailer' value='"+value.getRetailer()+"'>");
					pw.println("<input type='hidden' name='quantity' value='"+1+"'>");
					pw.println("</form></td></tr>");

					pw.println("<tr><th><form method = 'get' action = 'AddingProductToCart'>");
					pw.println("<input class = 'formbutton' type = 'submit' name = '"+ value.getName() +"' value = 'Add to Cart'>");
					pw.println("<input type='hidden' name='productName' value='"+value.getName()+"'>");
					pw.println("<input type='hidden' name='image' value='"+value.getImage()+"'>");
					pw.println("<input type='hidden' name='productType' value='"+typeOfProduct+"'>");
					pw.println("<input type='hidden' name='company' value='"+value.getCompany()+"'>");
					pw.println("<input type='hidden' name='price' value='"+newPrice+"'>");
					pw.println("<input type='hidden' name='quantity' value='"+1+"'>");
					pw.println("</form></th></tr>");

					pw.println("</table></th></tr></table>");
				}
			}
		}
		//==================================
		if(typeOfProduct.equals("Laptops"))
		{
			pw.println("<article><h3>Laptops</h3></article>");
			pw.println("<article class=\"expanded\">");

			for(Map.Entry<String,Laptop> mapping : lappy.entrySet()){
				Laptop value = mapping.getValue();

				float manfRebate = value.getMafRebate();
				float retDiscount = value.getReDiscount();
				float originalPrice = value.getPrice();

				float newPrice = (((100 - manfRebate)/100) * originalPrice) * ((100 - retDiscount)/100);
				newPrice = Math.round(newPrice*100)/100;

				pw.println("<table style=\"width:100%\" style=\"height:100%\" border=\"2\" bordercolor=\"#ff0000\" cellspacing=\"0\" cellpadding=\"0\">");
				pw.println("<tr><th width=\"30%\">");
				pw.println("<a href=\"ViewProduct\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
				pw.println(value.getImage());
				pw.println("\" /></a>");
				pw.println("</th>");
				pw.println("<th width=\"40%\"><table border=\"2\"  bordercolor=\"#ff0000\"><tr><th width=\"40\"><b>");
				pw.println(value.getName());
				pw.println("</b></th></tr><tr><th width=\"40\"><b>Company:</b>");
				pw.println(value.getCompany());
				pw.println("</b></th></tr><tr><th width=\"40\"><b>Color:</b>");
				pw.println(value.getColor());
				pw.println("</th></tr><tr><th><b>Condition:</b>");
				pw.println(value.getCondition());
				pw.println("</th></tr><tr><th><b>Manufacturer Rebates:</b>");
				pw.println(value.getMafRebate()+"%");
				pw.println("</th></tr><tr><th><b>Retailer Discounts:</b>");
				pw.println(value.getReDiscount()+"%");
				pw.println("</th></tr></table></th>");
				pw.println("<th width=\"30%\"><table border=\"2\"  bordercolor=\"#ff0000\"><tr><th><b>Price:");
				pw.println(value.getPrice()+"$");
				pw.println("<tr><th><b>Price after Discount:");
				pw.println(newPrice+"$");

				if(firstName != null && !firstName.isEmpty())
				{


					pw.println("<tr><th><form method = 'get' action = 'ViewProduct'>");
					pw.println("<input class = 'formbutton' type = 'submit' name = '"+ value.getName() +"' value = 'View Product Details'>");
					pw.println("<input type='hidden' name='productType' value='"+typeOfProduct+"'>");
					pw.println("<input type='hidden' name='productName' value='"+value.getName()+"'>");
					pw.println("<input type='hidden' name='image' value='"+value.getImage()+"'>");
					pw.println("<input type='hidden' name='price' value='"+value.getPrice()+"'>");
					pw.println("<input type='hidden' name='color' value='"+value.getColor()+"'>");
					pw.println("<input type='hidden' name='condition' value='"+value.getCondition()+"'>");
					pw.println("<input type='hidden' name='company' value='"+value.getCompany()+"'>");
					pw.println("<input type='hidden' name='retailer' value='"+value.getRetailer()+"'>");
					pw.println("<input type='hidden' name='manf' value='"+manfRebate+"'>");
					pw.println("<input type='hidden' name='ret' value='"+retDiscount+"'>");
					pw.println("<input type='hidden' name='newPrice' value='"+newPrice+"'>");
					pw.println("<input type='hidden' name='quantity' value='"+1+"'>");
					pw.println("</form></th></tr>");

					pw.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
					pw.println("<input class = 'formbutton' type = 'submit' name = '"+ value.getName() +"' value = 'View Reviews'>");
					pw.println("<input type='hidden' name='productType' value='"+typeOfProduct+"'>");
					pw.println("<input type='hidden' name='productName' value='"+value.getName()+"'>");
					pw.println("<input type='hidden' name='image' value='"+value.getImage()+"'>");
					pw.println("<input type='hidden' name='price' value='"+value.getPrice()+"'>");
					pw.println("<input type='hidden' name='color' value='"+value.getColor()+"'>");
					pw.println("<input type='hidden' name='condition' value='"+value.getCondition()+"'>");
					pw.println("<input type='hidden' name='company' value='"+value.getCompany()+"'>");
					pw.println("<input type='hidden' name='retailer' value='"+value.getRetailer()+"'>");
					pw.println("<input type='hidden' name='quantity' value='"+1+"'>");
					pw.println("</form></td></tr>");

					pw.println("<tr><td><form method = 'get' action = 'WriteReview'>");
					pw.println("<input class = 'formbutton' type = 'submit' name = '"+ value.getName() +"' value = 'Write Review'>");
					pw.println("<input type='hidden' name='productType' value='"+typeOfProduct+"'>");
					pw.println("<input type='hidden' name='productName' value='"+value.getName()+"'>");
					pw.println("<input type='hidden' name='image' value='"+value.getImage()+"'>");
					pw.println("<input type='hidden' name='price' value='"+value.getPrice()+"'>");
					pw.println("<input type='hidden' name='color' value='"+value.getColor()+"'>");
					pw.println("<input type='hidden' name='condition' value='"+value.getCondition()+"'>");
					pw.println("<input type='hidden' name='company' value='"+value.getCompany()+"'>");
					pw.println("<input type='hidden' name='retailer' value='"+value.getRetailer()+"'>");
					pw.println("<input type='hidden' name='quantity' value='"+1+"'>");
					pw.println("</form></td></tr>");

					pw.println("<tr><th><form method = 'get' action = 'AddingProductToCart'>");
					pw.println("<input class = 'formbutton' type = 'submit' name = '"+ value.getName() +"' value = 'Add to Cart'>");
					pw.println("<input type='hidden' name='productName' value='"+value.getName()+"'>");
					pw.println("<input type='hidden' name='image' value='"+value.getImage()+"'>");
					pw.println("<input type='hidden' name='productType' value='"+typeOfProduct+"'>");
					pw.println("<input type='hidden' name='company' value='"+value.getCompany()+"'>");
					pw.println("<input type='hidden' name='price' value='"+newPrice+"'>");
					pw.println("<input type='hidden' name='quantity' value='"+1+"'>");
					pw.println("</form></th></tr>");

					pw.println("</table></th></tr></table>");
				}
			}
		}

		if(typeOfProduct.equals("Smartwatch"))
		{
			pw.println("<article><h3>SmartWatches</h3></article>");
			pw.println("<article class=\"expanded\">");

			for(Map.Entry<String,Smartwatch> mapping : watches.entrySet()){
				Smartwatch value = mapping.getValue();
				float manfRebate = value.getMafRebate();
				float retDiscount = value.getReDiscount();
				float originalPrice = value.getPrice();

				float newPrice = (((100 - manfRebate)/100) * originalPrice) * ((100 - retDiscount)/100);
				newPrice = Math.round(newPrice*100)/100;

				pw.println("<table style=\"width:100%\" style=\"height:100%\" border=\"2\" bordercolor=\"#ff0000\" cellspacing=\"0\" cellpadding=\"0\">");
				pw.println("<tr><th width=\"30%\">");
				pw.println("<a href=\"ViewProduct\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
				pw.println(value.getImage());
				pw.println("\" /></a>");
				pw.println("</th>");
				pw.println("<th width=\"40%\"><table border=\"2\"  bordercolor=\"#ff0000\"><tr><th width=\"40\"><b>");
				pw.println(value.getName());
				pw.println("</b></th></tr><tr><th width=\"40\"><b>Company:</b>");
				pw.println(value.getCompany());
				pw.println("</b></th></tr><tr><th width=\"40\"><b>Color:</b>");
				pw.println(value.getColor());
				pw.println("</th></tr><tr><th><b>Condition:</b>");
				pw.println(value.getCondition());
				pw.println("</th></tr><tr><th><b>Manufacturer Rebates:</b>");
				pw.println(value.getMafRebate()+"%");
				pw.println("</th></tr><tr><th><b>Retailer Discounts:</b>");
				pw.println(value.getReDiscount()+"%");
				pw.println("</th></tr></table></th>");
				pw.println("<th width=\"30%\"><table  bordercolor=\"#ff0000\" border=\"2\"><tr><th><b>Price:");
				pw.println(value.getPrice()+"$");
				pw.println("<tr><th><b>Price after Discount:");
				pw.println(newPrice+"$");

				if(firstName != null && !firstName.isEmpty())
				{


					pw.println("<tr><th><form method = 'get' action = 'ViewProduct'>");
					pw.println("<input class = 'formbutton' type = 'submit' name = '"+ value.getName() +"' value = 'View Product Details'>");
					pw.println("<input type='hidden' name='productType' value='"+typeOfProduct+"'>");
					pw.println("<input type='hidden' name='productName' value='"+value.getName()+"'>");
					pw.println("<input type='hidden' name='image' value='"+value.getImage()+"'>");
					pw.println("<input type='hidden' name='price' value='"+value.getPrice()+"'>");
					pw.println("<input type='hidden' name='color' value='"+value.getColor()+"'>");
					pw.println("<input type='hidden' name='condition' value='"+value.getCondition()+"'>");
					pw.println("<input type='hidden' name='company' value='"+value.getCompany()+"'>");
					pw.println("<input type='hidden' name='retailer' value='"+value.getRetailer()+"'>");
					pw.println("<input type='hidden' name='manf' value='"+manfRebate+"'>");
					pw.println("<input type='hidden' name='ret' value='"+retDiscount+"'>");
					pw.println("<input type='hidden' name='newPrice' value='"+newPrice+"'>");
					pw.println("<input type='hidden' name='quantity' value='"+1+"'>");
					pw.println("</form></th></tr>");

					pw.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
					pw.println("<input class = 'formbutton' type = 'submit' name = '"+ value.getName() +"' value = 'View Reviews'>");
					pw.println("<input type='hidden' name='productType' value='"+typeOfProduct+"'>");
					pw.println("<input type='hidden' name='productName' value='"+value.getName()+"'>");
					pw.println("<input type='hidden' name='image' value='"+value.getImage()+"'>");
					pw.println("<input type='hidden' name='price' value='"+value.getPrice()+"'>");
					pw.println("<input type='hidden' name='color' value='"+value.getColor()+"'>");
					pw.println("<input type='hidden' name='condition' value='"+value.getCondition()+"'>");
					pw.println("<input type='hidden' name='company' value='"+value.getCompany()+"'>");
					pw.println("<input type='hidden' name='retailer' value='"+value.getRetailer()+"'>");
					pw.println("<input type='hidden' name='quantity' value='"+1+"'>");
					pw.println("</form></td></tr>");

					pw.println("<tr><td><form method = 'get' action = 'WriteReview'>");
					pw.println("<input class = 'formbutton' type = 'submit' name = '"+ value.getName() +"' value = 'Write Review'>");
					pw.println("<input type='hidden' name='productType' value='"+typeOfProduct+"'>");
					pw.println("<input type='hidden' name='productName' value='"+value.getName()+"'>");
					pw.println("<input type='hidden' name='image' value='"+value.getImage()+"'>");
					pw.println("<input type='hidden' name='price' value='"+value.getPrice()+"'>");
					pw.println("<input type='hidden' name='color' value='"+value.getColor()+"'>");
					pw.println("<input type='hidden' name='condition' value='"+value.getCondition()+"'>");
					pw.println("<input type='hidden' name='company' value='"+value.getCompany()+"'>");
					pw.println("<input type='hidden' name='retailer' value='"+value.getRetailer()+"'>");
					pw.println("<input type='hidden' name='quantity' value='"+1+"'>");
					pw.println("</form></td></tr>");

					pw.println("<tr><th><form method = 'get' action = 'AddingProductToCart'>");
					pw.println("<input class = 'formbutton' type = 'submit' name = '"+ value.getName() +"' value = 'Add to Cart'>");
					pw.println("<input type='hidden' name='productName' value='"+value.getName()+"'>");
					pw.println("<input type='hidden' name='image' value='"+value.getImage()+"'>");
					pw.println("<input type='hidden' name='price' value='"+newPrice+"'>");
					pw.println("<input type='hidden' name='productType' value='"+typeOfProduct+"'>");
					pw.println("<input type='hidden' name='company' value='"+value.getCompany()+"'>");
					pw.println("<input type='hidden' name='quantity' value='"+1+"'>");
					pw.println("</form></td></tr>");

					pw.println("</table></th></tr></table>");
				}
			}
		}

		if(typeOfProduct.equals("Accessories"))
		{
			pw.println("<article><h3>Accessories</h3></article>");
			pw.println("<article class=\"expanded\">");

			for(Map.Entry<String,Accessory> mapping :accessories.entrySet()){
				Accessory value = mapping.getValue();

				float manfRebate = value.getMafRebate();
				float retDiscount = value.getReDiscount();
				float originalPrice = value.getPrice();

				float newPrice = (((100 - manfRebate)/100) * originalPrice) * ((100 - retDiscount)/100);
				newPrice = Math.round(newPrice*100)/100;

				pw.println("<table style=\"width:100%\" style=\"height:100%\" border=\"2\" bordercolor=\"#ff0000\" cellspacing=\"0\" cellpadding=\"0\">");
				pw.println("<tr><th width=\"30%\">");
				pw.println("<a href=\"ViewProduct\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
				pw.println(value.getImage());
				pw.println("\" /></a>");
				pw.println("</th>");
				pw.println("<th width=\"40%\"><table border=\"2\"  bordercolor=\"#ff0000\"><tr><th width=\"40\"><b>");
				pw.println(value.getName());
				pw.println("</b></th></tr><tr><th width=\"40\"><b>Company:</b>");
				pw.println(value.getCompany());
				pw.println("</b></th></tr><tr><th width=\"40\"><b>Color:</b>");
				pw.println(value.getColor());
				pw.println("</th></tr><tr><th><b>Condition:</b>");
				pw.println(value.getCondition());
				pw.println("</th></tr><tr><th><b>Manufacturer Rebates:</b>");
				pw.println(value.getMafRebate()+"%");
				pw.println("</th></tr><tr><th><b>Retailer Discounts:</b>");
				pw.println(value.getReDiscount()+"%");
				pw.println("</th></tr></table></th>");
				pw.println("<th width=\"30%\"><table  bordercolor=\"#ff0000\" border=\"2\"><tr><th><b>Price:");
				pw.println(value.getPrice()+"$");
				pw.println("<tr><th><b>Price after Discount:");
				pw.println(newPrice+"$");

				if(firstName != null && !firstName.isEmpty())
				{


					pw.println("<tr><th><form method = 'get' action = 'ViewProduct'>");
					pw.println("<input class = 'formbutton' type = 'submit' name = '"+ value.getName() +"' value = 'ViewProduct'>");
					pw.println("<input type='hidden' name='productType' value='"+typeOfProduct+"'>");
					pw.println("<input type='hidden' name='productName' value='"+value.getName()+"'>");
					pw.println("<input type='hidden' name='image' value='"+value.getImage()+"'>");
					pw.println("<input type='hidden' name='price' value='"+value.getPrice()+"'>");
					pw.println("<input type='hidden' name='color' value='"+value.getColor()+"'>");
					pw.println("<input type='hidden' name='condition' value='"+value.getCondition()+"'>");
					pw.println("<input type='hidden' name='company' value='"+value.getCompany()+"'>");
					pw.println("<input type='hidden' name='retailer' value='"+value.getRetailer()+"'>");
					pw.println("<input type='hidden' name='manf' value='"+manfRebate+"'>");
					pw.println("<input type='hidden' name='ret' value='"+retDiscount+"'>");
					pw.println("<input type='hidden' name='newPrice' value='"+newPrice+"'>");
					pw.println("<input type='hidden' name='quantity' value='"+1+"'>");
					pw.println("</form></th></tr>");

					pw.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
					pw.println("<input class = 'formbutton' type = 'submit' name = '"+ value.getName() +"' value = 'View Reviews'>");
					pw.println("<input type='hidden' name='productType' value='"+typeOfProduct+"'>");
					pw.println("<input type='hidden' name='productName' value='"+value.getName()+"'>");
					pw.println("<input type='hidden' name='image' value='"+value.getImage()+"'>");
					pw.println("<input type='hidden' name='price' value='"+value.getPrice()+"'>");
					pw.println("<input type='hidden' name='color' value='"+value.getColor()+"'>");
					pw.println("<input type='hidden' name='condition' value='"+value.getCondition()+"'>");
					pw.println("<input type='hidden' name='company' value='"+value.getCompany()+"'>");
					pw.println("<input type='hidden' name='retailer' value='"+value.getRetailer()+"'>");
					pw.println("<input type='hidden' name='quantity' value='"+1+"'>");
					pw.println("</form></td></tr>");

					pw.println("<tr><td><form method = 'get' action = 'WriteReview'>");
					pw.println("<input class = 'formbutton' type = 'submit' name = '"+ value.getName() +"' value = 'Write Review'>");
					pw.println("<input type='hidden' name='productType' value='"+typeOfProduct+"'>");
					pw.println("<input type='hidden' name='productName' value='"+value.getName()+"'>");
					pw.println("<input type='hidden' name='image' value='"+value.getImage()+"'>");
					pw.println("<input type='hidden' name='price' value='"+value.getPrice()+"'>");
					pw.println("<input type='hidden' name='color' value='"+value.getColor()+"'>");
					pw.println("<input type='hidden' name='condition' value='"+value.getCondition()+"'>");
					pw.println("<input type='hidden' name='company' value='"+value.getCompany()+"'>");
					pw.println("<input type='hidden' name='retailer' value='"+value.getRetailer()+"'>");
					pw.println("<input type='hidden' name='quantity' value='"+1+"'>");
					pw.println("</form></td></tr>");

					pw.println("<tr><th><form method = 'get' action = 'AddingProductToCart'>");
					pw.println("<input class = 'formbutton' type = 'submit' name = '"+ value.getName() +"' value = 'Add to Cart'>");
					pw.println("<input type='hidden' name='productName' value='"+value.getName()+"'>");
					pw.println("<input type='hidden' name='image' value='"+value.getImage()+"'>");
					pw.println("<input type='hidden' name='productType' value='"+typeOfProduct+"'>");
					pw.println("<input type='hidden' name='company' value='"+value.getCompany()+"'>");
					pw.println("<input type='hidden' name='price' value='"+newPrice+"'>");
					pw.println("<input type='hidden' name='quantity' value='"+1+"'>");
					pw.println("</form></th></tr>");

					pw.println("</table></th></tr></table>");
				}
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

}