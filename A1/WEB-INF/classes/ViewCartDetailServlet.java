

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;



public class ViewCartDetailServlet extends HttpServlet {
	ArrayList<Object> products;
	HashMap<String, Smartphone> phones;
	HashMap<String, Laptop> lappy;
	HashMap<String, Speaker> speakers;
	HashMap<String, Smartwatch> watches;
	HashMap<String, Accessory> accessory;

	ProductSaxParser productSaxParser = new ProductSaxParser();

	void DataXMLFile() {
		try {
			products = productSaxParser.loadDataStore();

			phones = (HashMap<String, Smartphone>) products.get(0);
			lappy = (HashMap<String, Laptop>) products.get(1);
			accessory = (HashMap<String, Accessory>) products.get(2);
			watches = (HashMap<String, Smartwatch>) products.get(3);
			speakers = (HashMap<String, Speaker>) products.get(4);

		} catch (Exception E) {
			System.out.println("Exception");
		}
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataXMLFile();

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		HttpSession session = request.getSession();
		String firstName = (String) session.getAttribute("firstName");

		pw.println("<!doctype html>" +
				"<html>" +
				"<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		pw.println("<html lang='en'>" + "<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
		pw.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		pw.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>");
		pw.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js'></script>");
		pw.println("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>");
		pw.println();

		pw.println("<title>Smart Portables</title>" +
				"<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");

		pw.println("<body>" +
				"<div id=\"container\">" +
				"<header><h1><a href=\"/\">Smart<span>Portables</span></a></h1>");

		pw.println("</header>");

		if (firstName != null && !firstName.isEmpty()) {
			pw.println("<nav>" +
					"<ul>" +
					"<li class=\"start selected\"><a href=\"HomeServlet\">Welcome " + firstName + "</a></li>");
		} else {
			pw.println("<nav><ul><li class=\"start selected\"><a href=\"HomeServlet\">Home</a></li>");
		}

		pw.println("<li><a href=\"DataServlet?productType=Smartphones\">SmartPhones</a></li>");
		pw.println("<li><a href=\"DataServlet?productType=Speakers\">Speakers</a></li>");
		pw.println("<li><a href=\"DataServlet?productType=Laptops\">Laptops</a></li>");
		pw.println("<li><a href=\"DataServlet?productType=Accessories\">Accessories</a></li>");

		if (firstName != null && !firstName.isEmpty()) {
			pw.println("<li><a href=\"ViewCartDetailServlet\">Cart</a></li>");
			pw.println("<li><a href=\"ViewOrders\">Your Orders</a></li>");
			pw.println("<li><a href=\"LogoutServlet\">Logout</a></li>");
		} else {
			pw.println("<li><a href=\"LoginServlet\">Login</a></li>");
			pw.println("<li><a href=\"registration.html\">Register</a></li>");
			pw.println("<li><a href=\"ViewCartDetailServlet\">Cart(0)</a></li>");
		}

		pw.println("</ul></nav>");

		pw.println("<div id=\"body\">");
		pw.println("<section id=\"content\">");

		String typeOfProduct = request.getParameter("productType");
		String productCompany = request.getParameter("company");

		Cart cart;
		cart = (Cart) session.getAttribute("cart");

		if (firstName != null && !firstName.isEmpty()) {
			if (cart == null) {
				pw.println("<h1>Cart is Empty</h1>");
			} else {
				HashMap<String, List<Object>> items = cart.getCartItems();

				if (items.isEmpty()) {

					pw.println("<h1>Cart is Empty </h1>");
					pw.println("<tr>");
					pw.println("<td>");
					pw.println("</td>");
					pw.println("</tr>");

				} else {
					pw.println("<h1>List of current items in Cart</h1>");
					pw.println("<hr>");
					pw.println("<table border='2' bordercolor=\"#ff0000\">");
					pw.println("<tr><th>Product image</th><th>Product Detail</th><th>Cost&nbsp&nbsp&nbsp&nbsp</th><th>Quantity</th><th>Remove</th>");

					String key = "";
					for (Map.Entry<String, List<Object>> entry : items.entrySet()) {
						key = entry.getKey();
						List<Object> values = entry.getValue();
						pw.println("<form action='RemoveProductServlet'><input type='hidden' name='name' value='" + key + "'>");

						pw.println("<tr><th><img src ='" + values.get(3) + "' width = '100' height = '100'></th><th>" + values.get(0) + "  </th><th>" + "$" + values.get(1) + "</th>");
						pw.println("<td><select name='" + key + "'><option value='1' selected>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option></select></td>");
						pw.println("<td><input  class = 'formbutton' type='submit' name='value' value='Remove'></td></tr></form>");

					}
					pw.println("<form action='RemoveProductServlet'>");
					pw.println("<tr><td align='center' colspan='5'><input class = 'formbutton' type='submit' name='value' value='Checkout'></td></form>");
					pw.println("</tr></table>");
				}
			}
		} else {
			pw.println("<p>Please login to add items in your cart !");
		}

		pw.println("<br><br><br><br><br><br>");

		//=======================================================================
		//======================================================courousel==================================
		pw.println("<div class='bs-example'>");
		pw.println("<div id='myCarousel' class='carousel slide' data-ride='carousel'>");
		pw.println("<ol class='carousel-indicators'>");
		pw.print("<li data-target='#myCarousel' data-slide-to='1'></li>");
		pw.println("</ol>");




		pw.println("<div class='carousel-inner'>" +
				"<div class='item active'>");


		pw.println("</div>");

		//====================================second=============
		if(typeOfProduct != null){
		if(typeOfProduct.equals("Smartphones")) {

			for (Map.Entry<String, Accessory> mapping : accessory.entrySet()) {
				Accessory accessory = mapping.getValue();

				if (productCompany.equals(accessory.getCompany())) {

					float manfRebate = accessory.getMafRebate();
					float retDiscount = accessory.getReDiscount();
					float originalPrice = accessory.getPrice();

					float changePrice = (((100 - manfRebate) / 100) * originalPrice) * ((100 - retDiscount) / 100);
					changePrice = Math.round(changePrice * 100) / 100;

					pw.println("<div class='item'>");

					pw.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#ff0000\" cellspacing=\"0\" cellpadding=\"0\">");
					pw.println("<tr><td width=\"30%\">");
					pw.println("<a href=\"\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
					pw.println(accessory.getImage());
					pw.println("\" /></a>");
					pw.println("</td>");
					pw.println("<td width=\"40%\"><table border=\"1\"><tr><td width=\"40\"><b>");
					pw.println(accessory.getName());
					pw.println("</b></td></tr><tr><td width=\"40\"><b>Company:</b>");
					pw.println(accessory.getCompany());
					pw.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
					pw.println(accessory.getColor());
					pw.println("</td></tr><tr><td><b>Condition:</b>");
					pw.println(accessory.getCondition());
					pw.println("</td></tr><tr><td><b>Manufacturer Rebates:</b>");
					pw.println(manfRebate + "%");
					pw.println("</td></tr><tr><td><b>Retailer Discounts:</b>");
					pw.println(retDiscount + "%");
					pw.println("</td></tr></table border=\"1\"></td>");
					pw.println("<td width=\"30%\"><table><tr><td><b>Price:");
					pw.println(originalPrice+"$");
					pw.println("<tr><td><b>Price after Discount:");
					pw.println(changePrice+"$");

					pw.println("<tr><td><form method = 'get' action = '/A1/AddingProductToCart'>");
					pw.println("<input class = 'formbutton' type = 'submit' name = '" + accessory.getName() + "' value = 'Add to Cart'>");
					pw.println("<input type='hidden' name='productName' value='" + accessory.getName() + "'>");
					pw.println("<input type='hidden' name='image' value='" + accessory.getImage() + "'>");
					pw.println("<input type='hidden' name='price' value='" + changePrice + "'>");
					pw.println("<input type='hidden' name='quantity' value='" + 1 + "'>");
					pw.println("</form></td></tr>");
					pw.println("</table></td></tr></table>");
					pw.println("</div>");
				}
			}
			//=========================================================



		}else
		if(typeOfProduct.equals("Speakers")) {

			for (Map.Entry<String, Speaker> mapping : speakers.entrySet()) {
				Speaker value = mapping.getValue();

				if (productCompany.equals(value.getCompany())) {

					float manfRebate = value.getMafRebate();
					float retDiscount = value.getReDiscount();
					float originalPrice = value.getPrice();

					float changePrice = (((100 - manfRebate) / 100) * originalPrice) * ((100 - retDiscount) / 100);
					changePrice = Math.round(changePrice * 100) / 100;

					pw.println("<div class='item'>");

					pw.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#ff0000\" cellspacing=\"0\" cellpadding=\"0\">");
					pw.println("<tr><td width=\"30%\">");
					pw.println("<a href=\"\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
					pw.println(value.getImage());
					pw.println("\" /></a>");
					pw.println("</td>");
					pw.println("<td width=\"40%\"><table border=\"1\"><tr><td width=\"40\"><b>");
					pw.println(value.getName());
					pw.println("</b></td></tr><tr><td width=\"40\"><b>Company:</b>");
					pw.println(value.getCompany());
					pw.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
					pw.println(value.getColor());
					pw.println("</td></tr><tr><td><b>Condition:</b>");
					pw.println(value.getCondition());
					pw.println("</td></tr><tr><td><b>Manufacturer Rebates:</b>");
					pw.println(manfRebate + "%");
					pw.println("</td></tr><tr><td><b>Retailer Discounts:</b>");
					pw.println(retDiscount + "%");
					pw.println("</td></tr></table></td>");
					pw.println("<td width=\"30%\"><table border=\"1\"><tr><td><b>Price:");
					pw.println(originalPrice+"$");
					pw.println("<tr><td><b>Price after Discount:");
					pw.println(changePrice+"$");

					pw.println("<tr><td><form method = 'get' action = '/A1/AddingProductToCart'>");
					pw.println("<input class = 'formbutton' type = 'submit' name = '" + value.getName() + "' value = '/A1/AddingProductToCart'>");
					pw.println("<input type='hidden' name='productName' value='" + value.getName() + "'>");
					pw.println("<input type='hidden' name='image' value='" + value.getImage() + "'>");
					pw.println("<input type='hidden' name='price' value='" + changePrice + "'>");
					pw.println("<input type='hidden' name='quantity' value='" + 1 + "'>");
					pw.println("</form></td></tr>");
					pw.println("</table></td></tr></table>");
					pw.println("</div>");
				}
			}
		}
		//====================================================================================

		else if(typeOfProduct.equals("Laptops"))
		{
			for(Map.Entry<String,Laptop> mapping : lappy.entrySet()){
				Laptop value = mapping.getValue();

				if (productCompany.equals(value.getCompany())) {

					float manfRebate = value.getMafRebate();
					float retDiscount = value.getReDiscount();
					float originalPrice = value.getPrice();

					float changePrice = (((100 - manfRebate) / 100) * originalPrice) * ((100 - retDiscount) / 100);
					changePrice = Math.round(changePrice * 100) / 100;
					pw.println("<div class='item'>");

					pw.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#ff0000\" cellspacing=\"0\" cellpadding=\"0\">");
					pw.println("<tr><td width=\"30%\">");
					pw.println("<a href=\"\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
					pw.println(value.getImage());
					pw.println("\" /></a>");
					pw.println("</td>");
					pw.println("<td width=\"40%\"><table border=\"1\"><tr><td width=\"40\"><b>");
					pw.println(value.getName());
					pw.println("</b></td></tr><tr><td width=\"40\"><b>Company:</b>");
					pw.println(value.getCompany());
					pw.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
					pw.println(value.getColor());
					pw.println("</td></tr><tr><td><b>Condition:</b>");
					pw.println(value.getCondition());
					pw.println("</td></tr><tr><td><b>Manufacturer Rebates:</b>");
					pw.println(manfRebate + "%");
					pw.println("</td></tr><tr><td><b>Retailer Discounts:</b>");
					pw.println(retDiscount + "%");
					pw.println("</td></tr></table border=\"1\"></td>");
					pw.println("<td width=\"30%\"><table><tr><td><b>Price:");
					pw.println(originalPrice+"$");
					pw.println("<tr><td><b>Price after Discount:");
					pw.println(changePrice+"$");

					pw.println("<tr><td><form method = 'get' action = '/A1/AddingProductToCart'>");
					pw.println("<input class = 'formbutton' type = 'submit' name = '" + value.getName() + "' value = 'Add to Cart'>");
					pw.println("<input type='hidden' name='productName' value='" + value.getName() + "'>");
					pw.println("<input type='hidden' name='image' value='" + value.getImage() + "'>");
					pw.println("<input type='hidden' name='price' value='" + changePrice + "'>");
					pw.println("<input type='hidden' name='quantity' value='" + 1 + "'>");
					pw.println("</form></td></tr>");
					pw.println("</table></td></tr></table>");
					pw.println("</div>");
				}
			}
		}

		else if(typeOfProduct.equals("Smartwatch"))
		{
			for(Map.Entry<String,Smartwatch> mapping : watches.entrySet()){
				Smartwatch value = mapping.getValue();
				if (productCompany.equals(value.getCompany())) {

					float manfRebate = value.getMafRebate();
					float retDiscount = value.getReDiscount();
					float originalPrice = value.getPrice();

					float changePrice = (((100 - manfRebate) / 100) * originalPrice) * ((100 - retDiscount) / 100);
					changePrice = Math.round(changePrice * 100) / 100;
					pw.println("<div class='item'>");

					pw.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#ff0000\" cellspacing=\"0\" cellpadding=\"0\">");
					pw.println("<tr><td width=\"30%\">");
					pw.println("<a href=\"\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
					pw.println(value.getImage());
					pw.println("\" /></a>");
					pw.println("</td>");
					pw.println("<td width=\"40%\"><table border=\"1\"><tr><td width=\"40\"><b>");
					pw.println(value.getName());
					pw.println("</b></td></tr><tr><td width=\"40\"><b>Company:</b>");
					pw.println(value.getCompany());
					pw.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
					pw.println(value.getColor());
					pw.println("</td></tr><tr><td><b>Condition:</b>");
					pw.println(value.getCondition());
					pw.println("</td></tr><tr><td><b>Manufacturer Rebates:</b>");
					pw.println(manfRebate + "%");
					pw.println("</td></tr><tr><td><b>Retailer Discounts:</b>");
					pw.println(retDiscount + "%");
					pw.println("</td></tr></table border=\"1\"></td>");
					pw.println("<td width=\"30%\"><table><tr><td><b>Price:");
					pw.println(originalPrice+"$");
					pw.println("<tr><td><b>Price after Discount:");
					pw.println(changePrice+"$");

					pw.println("<tr><td><form method = 'get' action = '/A1/AddingProductToCart'>");
					pw.println("<input class = 'formbutton' type = 'submit' name = '" + value.getName() + "' value = 'Add to Cart'>");
					pw.println("<input type='hidden' name='productName' value='" + value.getName() + "'>");
					pw.println("<input type='hidden' name='image' value='" + value.getImage() + "'>");
					pw.println("<input type='hidden' name='price' value='" + changePrice + "'>");
					pw.println("<input type='hidden' name='quantity' value='" + 1 + "'>");
					pw.println("</form></td></tr>");
					pw.println("</table></td></tr></table>");
					pw.println("</div>");
				}
			}
		}

		//=====================================================================

		else if(typeOfProduct.equals("Accessories"))
		{
			for(Map.Entry<String,Accessory> mapping : accessory.entrySet()) {
				Accessory value = mapping.getValue();
				if (productCompany.equals(value.getCompany())) {

					float manfRebate = value.getMafRebate();
					float retDiscount = value.getReDiscount();
					float originalPrice = value.getPrice();

					float changePrice = (((100 - manfRebate) / 100) * originalPrice) * ((100 - retDiscount) / 100);
					changePrice = Math.round(changePrice * 100) / 100;
					pw.println("<div class='item'>");

					pw.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#ff0000\" cellspacing=\"0\" cellpadding=\"0\">");
					pw.println("<tr><td width=\"30%\">");
					pw.println("<a href=\"\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
					pw.println(value.getImage());
					pw.println("\" /></a>");
					pw.println("</td>");
					pw.println("<td width=\"40%\"><table border=\"1\"><tr><td width=\"40\"><b>");
					pw.println(value.getName());
					pw.println("</b></td></tr><tr><td width=\"40\"><b>Company:</b>");
					pw.println(value.getCompany());
					pw.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
					pw.println(value.getColor());
					pw.println("</td></tr><tr><td><b>Condition:</b>");
					pw.println(value.getCondition());
					pw.println("</td></tr><tr><td><b>Manufacturer Rebates:</b>");
					pw.println(manfRebate + "%");
					pw.println("</td></tr><tr><td><b>Retailer Discounts:</b>");
					pw.println(retDiscount + "%");
					pw.println("</td></tr></table></td>");
					pw.println("<td width=\"30%\"><table border=\"1\"><tr><td><b>Price:");
					pw.println(originalPrice + "$");
					pw.println("<tr><td><b>Price after Discount:");
					pw.println(changePrice + "$");

					pw.println("<tr><td><form method = 'get' action = '/A1/AddingProductToCart'>");
					pw.println("<input class = 'formbutton' type = 'submit' name = '" + value.getName() + "' value = 'Add to Cart'>");
					pw.println("<input type='hidden' name='productName' value='" + value.getName() + "'>");
					pw.println("<input type='hidden' name='image' value='" + value.getImage() + "'>");
					pw.println("<input type='hidden' name='price' value='" + changePrice + "'>");
					pw.println("<input type='hidden' name='quantity' value='" + 1 + "'>");
					pw.println("</form></td></tr>");
					pw.println("</table></td></tr></table>");
					pw.println("</div>");
				}
			}
			}
		}





		pw.println("</div>" +
				"<a class='carousel-control left' href='#myCarousel' data-slide='prev'>" +
				"<span class='glyphicon glyphicon-chevron-left'></span>" +
				"</a>" +
				"<a class='carousel-control right' href='#myCarousel' data-slide='next'>" +
				"<span class='glyphicon glyphicon-chevron-right'></span>" +
				"</a>" +
				"</div>" +
				"</div>");
		pw.println("</article></section>");

		pw.println("<aside class=\"sidebar\">");


		pw.println("<ul>" +
				"<li><h4>SmartPhones</h4><ul>");
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
		pw.println("<footer>" +
				"<div class='footer-bottom'>" +
				"<p></p>" +
				"</div>" +
				"</footer>");

		pw.close();

	}


}
