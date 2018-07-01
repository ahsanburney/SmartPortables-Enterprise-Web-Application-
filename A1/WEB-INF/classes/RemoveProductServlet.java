

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;



public class RemoveProductServlet extends HttpServlet {
	
	
  public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
      
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
        HttpSession httpSession = request.getSession();
		String firstName = (String)httpSession.getAttribute("firstName");
        
		pw.println("<!doctype html>" +
				"<html><head>" +
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		pw.println("<title>Smart Portables</title><" +
				"link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		pw.println("<body>" +
				"<div id=\"container\">" +
				"<header><h1><a href=\"/\">Smart<span>Portables</span></a></h1><h2>");
		pw.println("</header>");
		
		if(firstName != null && !firstName.isEmpty())
		{


			pw.println("<nav>" +
					"<ul>" +
					"<li class=\"start selected\"><a href=\"HomeServlet\">Welcome "+firstName+"</a></li>");
		}
		else{
			pw.println("<nav><ul><li class=\"start selected\"><a href=\"HomeServlet\">Home</a></li>");
		}

	  pw.println("<li>" +
			  "<a href=\"DataServlet?productType=Smartphones\">SmartPhones</a></li>");
	  pw.println("<li>" +
			  "<a href=\"DataServlet?productType=Speakers\">Speakers</a></li>");
	  pw.println("<li>" +
			  "<a href=\"DataServlet?productType=Laptops\">Laptops</a></li>");
	  pw.println("<li>" +
			  "<a href=\"DataServlet?productType=Smartwatch\">SmartWatches</a></li>");
	  pw.println("<li>" +
			  "<a href=\"DataServlet?productType=Accessories\">Accessories</a></li>");
		
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
			pw.println("<li><a href=\"ViewCartDetailServlet\">Cart(0)</a></li>");
		}
		
		pw.println("</ul></nav>");
		
//===================================Display Code Start Here================================
		String value = request.getParameter("value");

		if(value.equals("Remove"))
		{
			String name = request.getParameter("name");
			
			Cart cartFunctionality;
			cartFunctionality = (Cart) httpSession.getAttribute("cart");
			cartFunctionality.deleteFromCart(name);
			httpSession.setAttribute("cart", cartFunctionality);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("ViewCartDetailServlet");
			requestDispatcher.forward(request,response);
		}
		
		if(value.equals("Checkout"))
		{	
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("CheckoutServlet");
			requestDispatcher.forward(request,response);
		}
		
		pw.close();
	
}

}