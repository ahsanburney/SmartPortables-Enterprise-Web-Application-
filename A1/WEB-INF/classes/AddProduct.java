

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;



public class AddProduct extends HttpServlet {

	ArrayList<Object> products;
	HashMap<String, Smartphone> phones;
	HashMap<String, Laptop> lappy;
	HashMap<String, Accessory> accessory;
	HashMap<String, Smartwatch> watches;
	HashMap<String, Speaker> speakers;
	HashMap<String,Headphone> headphones;
	HashMap<String,WarrantyServlet> warranty;

	ProductSaxParser productSaxParser = new ProductSaxParser();

	void dataXMLFile()
	{
		try{
			products = productSaxParser.loadDataStore();

			phones = (HashMap<String,Smartphone>)products.get(0);
			lappy = (HashMap<String, Laptop>)products.get(1);
			accessory = (HashMap<String, Accessory>)products.get(2);
			watches = (HashMap<String, Smartwatch>)products.get(3);
			speakers= (HashMap<String, Speaker>)products.get(4);
			headphones= (HashMap<String, Headphone>)products.get(5);
			warranty = (HashMap<String, WarrantyServlet>)products.get(6);

		}catch(Exception E){
			System.out.println("Exception occured");
		}
	}


	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {

		dataXMLFile();

		PrintWriter pw = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		
		pw.println("<!doctype html>" +
				"<html><head>" +
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		pw.println("<title>Smart Portables</title>" +
				"<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		pw.println("<body>" +
				"<div id=\"container\">");
		pw.println("<header>" +
				"<h1><a href=\"/\">Smart<span>Portables</span></a></h1><h3>ADMIN AREA</h3></header>");
		pw.println("<nav><ul>");
		pw.println("<li class=\"\"><a href=\"AdministratorServlet\">Product List</a></li>");
		pw.println("<li><a href=\"AdministratorServlet?type=addProduct\">Add Product</a></li>");
		pw.println("<li><a href=\"InventoryServlet\">Inventory Report</a></li>");
		pw.println("<li><a href=\"SalesReportServlet\">Sales Report</a></li>");
		pw.println("<li><a href=\"AnalyticsServlet\">Data Analytics</a></li>");
		pw.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");

		pw.println("<div id=\"body\">" +
				"<table border=\\\"2\\\" bordercolor=\"#ff0000\"><tr><th width=\\\"\\40\">"+
				"<article><h3 align=\"center\">Add Product</h3>");
		pw.println("<fieldset>" +
				"<div style=\"width:400px; margin-right:auto; margin-left:auto;\">");

		pw.println("<form action='/A1/AddingProduct' method=\"post\">");

		pw.println("<p><label>Product Type:</label>");
		pw.println("<select name='productType'>" +
				"<option name='productType' value='Smartphone' selected>Smartphones</option><option name='productType' value='Smartwatch'>SmartWatches</option>");
		pw.println("<option name='productType' value='Laptop'>Laptops</option><option name='productType' value='Headphone'>HeadPhone </option><option name='productType' value='Speaker'>Speaker</option><option name='productType' value='Accessory'>Accessories </option><option name='productType' value='Warranty'>Warranty</option></select></p>");

		pw.println("<p><label>Product Id:</label>" +
				"<input name=\"productId\" type=\"text\" /></p>");
		pw.println("<p><label>Retailer Name:</label>" +
				"<input name=\"retailer\" type=\"text\" /></p>");
		pw.println("<p><label>Company:</label>" +
				"<input name=\"company\" type=\"text\" /></p>");
		pw.println("<p><label>Image Path:</label>" +
				"<input name=\"imagePath\" type=\"text\" /></p>");
		pw.println("<p><label>Product Name:</label>" +
				"<input name=\"productName\" type=\"text\" /></p>");
		pw.println("<p><label>Condition:</label>" +
				"<input name=\"condition\" type=\"text\" /></p>");
		pw.println("<p><label>Price:</label>" +
				"<input name=\"price\" type=\"text\" /></p>");
		pw.println("<p><label>Manf Rebate:</label>" +
				"<input name=\"manfRebate\" type=\"text\" /></p>");
		pw.println("<p><label>Ret Discount:</label>" +
				"<input name=\"retdiscount\" type=\"text\" /></p>");
		pw.println("<p><label>Color:</label>" +
				"<input name=\"color\" type=\"text\" /></p>");
		pw.println("<p><input name=\"send\" float: right; value=\"Submit\" type=\"submit\" /></p>");
		pw.println("</form>");

		pw.println("</div>" +
				"</fieldset>" +
				"</article</div></div></body>" +
				"</html>");

		pw.close();

	}

	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {

		doPost(request, response);
	}

}


