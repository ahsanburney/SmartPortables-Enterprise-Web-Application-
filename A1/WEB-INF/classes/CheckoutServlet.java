

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;



public class CheckoutServlet extends HttpServlet {
	
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
        HttpSession session = request.getSession();
		String firstName = (String)session.getAttribute("firstName");
        
		pw.println("<!doctype html>" +
				"<html><head>" +
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		pw.println("<title>Smart Portables</title>" +
				"<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");

		pw.println("<body>" +
				"<div id=\"container\">" +
				"<header><h1>" +
				"<a href=\"/\">Smart<span>Portables</span></a></h1>");
		
		pw.println("</header>");
		
		if(firstName != null && !firstName.isEmpty())
		{

			pw.println("<nav><ul>" +
					"<li class=\"start selected\"><a href=\"HomeServlet\">Welcome "+firstName+"</a></li>");
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
		//==================================Display code starts=====================================
		
		String action = request.getParameter("value");
			
		if(action.equals("Checkout"))
		{
			Cart cartFunctionality;
			cartFunctionality = (Cart) session.getAttribute("cart");
			HashMap<String, List<Object>> cartItems = cartFunctionality.getCartItems();
			
			for(Map.Entry<String, List<Object>> value : cartItems.entrySet()){
				String key = value.getKey();
				List<Object> values = value.getValue();
				int quantity = 1;
				cartFunctionality.addToCart(key,(String)values.get(0),(float)values.get(1),quantity,(String)values.get(3));
			}
					
			int noOfProducts=0;
			float priceOfProduct=0;
			float totalAmount=0;
			
			for(Map.Entry<String, List<Object>> entry : cartItems.entrySet()){
				List<Object> values = entry.getValue();
				priceOfProduct=(float)values.get(1);
				noOfProducts=(int)values.get(2);
				totalAmount=totalAmount + priceOfProduct*noOfProducts;
			}
			
			cartFunctionality.setTotalAmount(totalAmount);

				
			pw.println("<div id=\"body\">" +

					"<table border=\\\"2\\\"  bordercolor=\"#ff0000\"><tr><td width=\\\"\\40\">"+
					"<h3 align='center'>Personal Information</h3>");
			pw.println("<form action=\"OrderConfirmationServlet\" method=\"POST\">");
			pw.println("<tr>" +
					"<th width=\"\"><b align='center'>First Name::</b>");
			pw.println("<input name=\'firstName\' type=\'text\'>");
			pw.println("</th>" +
					"<th width=\"\"><b align='center'>Last Name:");
			pw.println("<input name=\"lastName\"type=\"text\" />");
			pw.println("</th>" +
					"<th width=\"\"><b align='center'>Phone Number:");
			pw.println("<input name=\"phoneNumber\"type=\"text\" />");
			pw.println("</th>" +
					"</tr><tr><th width=\"\"><b align='center'>User Name</b>");
			pw.println("<input name=\"emailId\"type=\"text\"></p>");
			pw.println("</th>" +
					"<th width=\"\"><b align='center'>Shipping Address:</b>");
			pw.println("<textarea rows=\"4\" cols=\"20\" name=\"shippingAddress\"></textarea>");
			pw.println("</th>" +
					"</tr></table>");

			pw.println("<table border=\\\"2\\\"  bordercolor=\"#ff0000\"><tr><td width=\\\"40\\\"><b>"+
			"<h3 align=\"center\">Payment Information</h3>");
			pw.println("<tr>" +
					"<th width=\"40\"><b align='center'>Card Holder name:</b>");
			pw.println("<input name=\"cardHolderName\" type=\"text\"></p>");
			pw.println("</th>" +
					"<th width=\"40\"><b align='center'>Credit Card Number:</b>");
			pw.println("<input name=\"ccNumber\"type=\"text\" /></p>");
			pw.println("</th>" +
					"<th width=\"40\"><b align='center'>Expriy Date:</b>");
			pw.println("<input name=\"expDate\"type=\"text\" /></p>");
			pw.println("</th>" +
					"</tr><th width=\"40\"><b align='center'>CVV:</b>");
			pw.println("<input name=\"cvv\"type=\"password\" /></p>");
			pw.println("</th>" +
					"<tr><td width=\"40\"><b align='center'>Amount to Pay:</b>");
			pw.println("<p><label name='finalAmount' value='amount'>Total Amount: <b align='center'>"+totalAmount+"</b></label>");
			pw.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Confirm Payment\" type=\"submit\" /></p>");
			pw.println("</form>" +
					"</table>"+
					"</div>" +
					"<div class=\"clear\">" +
					"</div></div>");
		}
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
				"</div>");

		pw.println("<footer>"+
				"<div class='footer-bottom'>"+
				"<p></p>"+
				"</div>"+
				"</footer>");
		pw.println("</body></html>");
		
		pw.close();
	
}

}

