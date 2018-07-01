

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class UpdateOrder extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		String queryType="";
		queryType = request.getParameter("queryType");

		pw.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		pw.println("<title>Smart Portables</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		pw.println("<body><div id=\"container\">");
		pw.println("<header><h1><a href=\"/\">Smart<span>Portables</span></a></h1>" +
				"<h3>Salesman Area</h3></header>");
		pw.println("<nav><ul>");
		pw.println("<li class=\"start selected\"><a href=\"SalesmanServlet?type=createCustomer\">Create Customer</a></li>");
		pw.println("<li><a href=\"SalesmanServlet?type=addOrder\">ADD ORDER</a></li>");
		pw.println("<li><a href=\"SalesmanServlet?type=updateOrder\">UPDATE ORDER</a></li>");
		pw.println("<li><a href=\"SalesmanServlet?type=orderList\">DELETE ORDER</a></li>");
		pw.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");

		if(queryType.equals("updateOrder"))
		{
			String customerEmailId = request.getParameter("customerEmailId");
			String address = request.getParameter("deliveryAddress");
			String name = request.getParameter("itemName");
			Float price = Float.parseFloat(request.getParameter("itemPrice"));
			String orderId = request.getParameter("orderId");
			String orderDate = request.getParameter("orderDate");
			String dateOfdelivery = request.getParameter("deliveryDate");

			MySqlDataStoreUtilities.updateOrder(name, orderId, price, dateOfdelivery,address);

			pw.println("<h3><br><br>Order No "+orderId+" for Customer "+customerEmailId+" updated succesfully.</h3><br><br>");
		}


	}


}


