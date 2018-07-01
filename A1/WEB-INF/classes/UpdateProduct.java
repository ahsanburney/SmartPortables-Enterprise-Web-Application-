

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;


public class UpdateProduct extends HttpServlet {

	ArrayList<Object> products;
	HashMap<String, Smartphone> phones;
	HashMap<String, Laptop> lappy;
	HashMap<String, Accessory> accessory;
	HashMap<String, Smartwatch> watches;
	HashMap<String, Speaker> speakers;
	HashMap<String,Headphone> headphones;
	HashMap<String,WarrantyServlet> warranty;

	ProductSaxParser productSaxParser = new ProductSaxParser();

	String productXmlFileLocation = "C:/apache-tomcat-7.0.34/webapps/A1/WEB-INF/classes/ProductCatalog.xml";


	void DataXMLFile()
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


	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {

		DataXMLFile();

		PrintWriter pw = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");

		pw.println("<!doctype html>" +
				"<html><head>" +
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		pw.println("<title>Smart Portables</title>" +
				"<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		pw.println("<body>" +
				"<div id=\"container\">");
		pw.println("<header><h1>" +
				"<a href=\"/\">Smart<span>Portables</span></a></h1><h3>Store Manager Admin Area</h3></header>");
		pw.println("<nav><ul>");
		pw.println("<li class=\"\"><a href=\"AdministratorServlet\">Product List</a></li>");
		pw.println("<li><a href=\"AdministratorServlet?type=addProduct\">Add Product</a></li>");
		pw.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");


		String type = request.getParameter("type");
		String productId = request.getParameter("id");
		String retailerName = request.getParameter("retailer");
		String path = request.getParameter("image");
		String productName = request.getParameter("productName");
		String companyName = request.getParameter("company");
		String conditionName = request.getParameter("condition");
		String priceName = request.getParameter("price");
		String colorName = request.getParameter("color");

		pw.println("<div id=\"body\">" +
				"<table border=\\\"1\\\" bordercolor=\"#ff0000\"><tr><td width=\\\"\\40\">"+
				"<article><h3 align=\"center\">Update Product Details" +
				"</h3>");
		pw.println("<div style=\"width:400px; margin-right:auto; margin-left:auto;\">");


		if(type.equals("Smartphone"))
		{
			pw.println("<form action='/A1/UpdateProduct' method=\"post\">");
			pw.println("<table style=\"width:100%\" style=\"height:100%\" border=\"2\" bordercolor=\"#ff0000\" cellspacing=\"0\" cellpadding=\"0\">");
			pw.println("" +
					"<th><label>Product Category:</label>"+"Smartphone"+"</th>");
			pw.println("<input type='hidden' name='type' value='Smartphone'>");
			pw.println("" +
					"<tr><th><label>Product Id:</label>"+productId+"</th></tr>");
			pw.println("<input type='hidden' name='id' value='"+productId+"'>");
			pw.println("<tr><th>" +
					"<label>Retailer Name:</label>" +
					"</th><th><input name=\"retailer\" type=\"text\" value='"+retailerName+"'/></th></tr>");
			pw.println("<tr><th>" +
					"<label>Image Path:</label></th>" +
					"<th><input name=\"imagePath\" type=\"text\" value='"+path+"'/></th></tr>");
			pw.println("<tr><th>" +
					"<label>Product Name:</label></th>" +
					"<th><input name=\"productName\" type=\"text\" value='"+productName+"' /></th></tr>");
			pw.println("<tr><th>" +
					"<label>Company Name:</label></th>" +
					"<th><input name=\"company\" type=\"text\" value='"+companyName+"' /></th></tr>");
			pw.println("<tr><th>" +
					"<label>Condition:</label></th><th>" +
					"<input name=\"condition\" type=\"text\" value='"+conditionName+"' /></p></td></tr>");
			pw.println("<tr><th>" +
					"<label>Price:</label></th><th>" +
					"<input name=\"price\" type=\"text\" value='"+priceName+"' /></th></tr>");
			pw.println("<tr><th>" +
					"<label>Color:</label></th><th><input name=\"color\" type=\"text\" value='"+colorName+"' /></th></tr>");
			pw.println("<tr><th>" +
					"<input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Update Product\" type=\"submit\" /></th></tr>");
			pw.println("</form>");
			pw.println("</table>");
		}

		if(type.equals("Smartwatch"))
		{
			pw.println("<form action='/A1/UpdateProduct' method=\"post\">");
			pw.println("<p>" +
					"<label>Product Category:</label>"+"SmartWatch"+"</p>");
			pw.println("<input type='hidden' name='type' value='Smartwatch'>");
			pw.println("<p>" +
					"<label>Product Id:</label>"+productId+"</p>");
			pw.println("<input type='hidden' name='id' value='"+productId+"'>");
			pw.println("<p>" +
					"<label>Retailer Name:</label><input name=\"retailer\" type=\"text\" value='"+retailerName+"'/></p>");
			pw.println("<p>" +
					"<label>Image Path:</label><input name=\"imagePath\" type=\"text\" value='"+path+"'/></p>");
			pw.println("<p>" +
					"<label>Product Name:</label><input name=\"productName\" type=\"text\" value='"+productName+"' /></p>");
			pw.println("<p>" +
					"<label>Company Name:</label><input name=\"company\" type=\"text\" value='"+companyName+"' /></p>");
			pw.println("<p>" +
					"<label>Condition:</label><input name=\"condition\" type=\"text\" value='"+conditionName+"' /></p>");
			pw.println("<p>" +
					"<label>Price:</label><input name=\"price\" type=\"text\" value='"+priceName+"' /></p>");
			pw.println("<p>" +
					"<label>Color:</label><input name=\"color\" type=\"text\" value='"+colorName+"' /></p>");
			pw.println("<p>" +
					"<input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Update Product\" type=\"submit\" /></p>");
			pw.println("</form>");
		}

		if(type.equals("warranty"))
		{
			pw.println("<form action='/A1/UpdateProduct' method=\"post\">");
			pw.println("<p>" +
					"<label>Product Category:</label>"+"Warranty"+"</p>");
			pw.println("<input type='hidden' name='type' value='warranty'>");
			pw.println("<p>" +
					"<label>Product Id:</label>"+productId+"</p>");
			pw.println("<input type='hidden' name='id' value='"+productId+"'>");
			pw.println("<p>" +
					"<label>Retailer Name:</label>" +
					"<input name=\"retailer\" type=\"text\" value='"+retailerName+"'/></p>");
			pw.println("<p>" +
					"<label>Image Path:</label>" +
					"<input name=\"imagePath\" type=\"text\" value='"+path+"'/></p>");
			pw.println("<p>" +
					"<label>Product Name:</label>" +
					"<input name=\"productName\" type=\"text\" value='"+productName+"' /></p>");
			pw.println("<p>" +
					"<label>Company Name:</label>" +
					"<input name=\"company\" type=\"text\" value='"+companyName+"' /></p>");
			pw.println("<p>" +
					"<label>Condition:</label>" +
					"<input name=\"condition\" type=\"text\" value='"+conditionName+"' /></p>");
			pw.println("<p>" +
					"<label>Price:</label>" +
					"<input name=\"price\" type=\"text\" value='"+priceName+"' /></p>");
			pw.println("<p>" +
					"<label>Color:</label>" +
					"<input name=\"color\" type=\"text\" value='"+colorName+"' /></p>");
			pw.println("<p>" +
					"<input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Update Product\" type=\"submit\" /></p>");
			pw.println("</form>");
		}

		if(type.equals("Speaker"))
		{
			pw.println("<form action='/A1/UpdateProduct' method=\"post\">");
			pw.println("<p>" +
					"<label>Product Category:</label>"+"Speaker"+"</p>");
			pw.println("<input type='hidden' name='type' value='Speaker'>");
			pw.println("<p>" +
					"<label>Product Id:</label>"+productId+"</p>");
			pw.println("<input type='hidden' name='id' value='"+productId+"'>");
			pw.println("<p>" +
					"<label>Retailer:</label>" +
					"<input name=\"retailer\" type=\"text\" value='"+retailerName+"'/></p>");
			pw.println("<p>" +
					"<label>Image Path:</label>" +
					"<input name=\"imagePath\" type=\"text\" value='"+path+"'/></p>");
			pw.println("<p>" +
					"<label>Product Name:</label>" +
					"<input name=\"productName\" type=\"text\" value='"+productName+"' /></p>");
			pw.println("<p>" +
					"<label>Company Name:</label>" +
					"<input name=\"company\" type=\"text\" value='"+companyName+"' /></p>");
			pw.println("<p>" +
					"<label>Condition:</label>" +
					"<input name=\"condition\" type=\"text\" value='"+conditionName+"' /></p>");
			pw.println("<p>" +
					"<label>Price:</label>" +
					"<input name=\"price\" type=\"text\" value='"+priceName+"' /></p>");
			pw.println("<p>" +
					"<label>Color:</label>" +
					"<input name=\"color\" type=\"text\" value='"+colorName+"' /></p>");
			pw.println("<p>" +
					"<input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Update Product\" type=\"submit\" /></p>");
			pw.println("</form>");
		}
		if(type.equals("Headphone"))
		{
			pw.println("<form action='/A1/UpdateProduct' method=\"post\">");
			pw.println("<p>" +
					"<label>Product Category:</label>"+"Laptop"+"</p>");
			pw.println("<input type='hidden' name='type' value='Headphone'>");
			pw.println("<p>" +
					"<label>Product Id:</label>"+productId+"</p>");
			pw.println("<input type='hidden' name='id' value='"+productId+"'>");
			pw.println("<p>" +
					"<label>Retailer Name:</label>" +
					"<input name=\"retailer\" type=\"text\" value='"+retailerName+"'/></p>");
			pw.println("<p>" +
					"<label>Image Path:</label>" +
					"<input name=\"imagePath\" type=\"text\" value='"+path+"'/></p>");
			pw.println("<p>" +
					"<label>Product Name:</label>" +
					"<input name=\"productName\" type=\"text\" value='"+productName+"' /></p>");
			pw.println("<p>" +
					"<label>Company Name:</label>" +
					"<input name=\"company\" type=\"text\" value='"+companyName+"' /></p>");
			pw.println("<p>" +
					"<label>Condition:</label>" +
					"<input name=\"condition\" type=\"text\" value='"+conditionName+"' /></p>");
			pw.println("<p>" +
					"<label>Price:</label>" +
					"<input name=\"price\" type=\"text\" value='"+priceName+"' /></p>");
			pw.println("<p>" +
					"<label>Color:</label>" +
					"<input name=\"color\" type=\"text\" value='"+colorName+"' /></p>");
			pw.println("<p>" +
					"<input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Update Product\" type=\"submit\" /></p>");
			pw.println("</form>");
		}


		if(type.equals("Laptop"))
		{
			pw.println("<form action='/A1/UpdateProduct' method=\"post\">");
			pw.println("<p>" +
					"<label>Product Category:</label>"+"Laptop"+"</p>");
			pw.println("<input type='hidden' name='type' value='Laptop'>");
			pw.println("<p>" +
					"<label>Product Id:</label>"+productId+"</p>");
			pw.println("<input type='hidden' name='id' value='"+productId+"'>");
			pw.println("<p>" +
					"<label>Retailer name:</label>" +
					"<input name=\"retailer\" type=\"text\" value='"+retailerName+"'/></p>");
			pw.println("<p>" +
					"<label>Image Path:</label>" +
					"<input name=\"imagePath\" type=\"text\" value='"+path+"'/></p>");
			pw.println("<p>" +
					"<label>Product Name:</label>" +
					"<input name=\"productName\" type=\"text\" value='"+productName+"' /></p>");
			pw.println("<p>" +
					"<label>Company Name:</label>" +
					"<input name=\"company\" type=\"text\" value='"+companyName+"' /></p>");
			pw.println("<p>" +
					"<label>Condition:</label>" +
					"<input name=\"condition\" type=\"text\" value='"+conditionName+"' /></p>");
			pw.println("<p>" +
					"<label>Price:</label>" +
					"<input name=\"price\" type=\"text\" value='"+priceName+"' /></p>");
			pw.println("<p>" +
					"<label>Color:</label>" +
					"<input name=\"color\" type=\"text\" value='"+colorName+"' /></p>");
			pw.println("<p>" +
					"<input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Update Product\" type=\"submit\" /></p>");
			pw.println("</form>");
		}


		if(type.equals("Accessory"))
		{
			pw.println("<form action='/A1/UpdateProduct' method=\"post\">");
			pw.println("<p>" +
					"<label>Product Category:</label>"+"Accessory"+"</p>");
			pw.println("<input type='hidden' name='type' value='Accessory'>");
			pw.println("<p>" +
					"<label>Product Id:</label>"+productId+"</p>");
			pw.println("<input type='hidden' name='id' value='"+productId+"'>");
			pw.println("<p>" +
					"<label>Retailer Name:</label>" +
					"<input name=\"retailer\" type=\"text\" value='"+retailerName+"'/></p>");
			pw.println("<p>" +
					"<label>Image Path:</label>" +
					"<input name=\"imagePath\" type=\"text\" value='"+path+"'/></p>");
			pw.println("<p>" +
					"<label>Product Name:</label>" +
					"<input name=\"productName\" type=\"text\" value='"+productName+"' /></p>");
			pw.println("<p>" +
					"<label>Company Name:</label><input name=\"company\" type=\"text\" value='"+companyName+"' /></p>");
			pw.println("<p>" +
					"<label>Condition:</label>" +
					"<input name=\"condition\" type=\"text\" value='"+conditionName+"' /></p>");
			pw.println("<p>" +
					"<label>Price:</label>" +
					"<input name=\"price\" type=\"text\" value='"+priceName+"' /></p>");
			pw.println("<p>" +
					"<label>Color:</label>" +
					"<input name=\"color\" type=\"text\" value='"+colorName+"' /></p>");
			pw.println("<p>" +
					"<input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Update Product\" type=\"submit\" /></p>");
			pw.println("</form>");
		}

		pw.println("</div></fieldset></article</div></div></body></html>");

		pw.close();

	}

	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {

		DataXMLFile();

		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");


		out.println("<!doctype html>" +
				"<html><head>" +
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>Smart Portables</title>" +
				"<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		out.println("<body>" +
				"<div id=\"container\">");
		out.println("<header>" +
				"<h1><a href=\"/\">Smart<span>Portables</span></a></h1><h3>Store Manager Admin Area</h3></header>");
		out.println("<nav><ul>");
		out.println("<li>" +
				"<a href=\"AdministratorServlet\">Product List</a></li>");
		out.println("<li>" +
				"<a href=\"AdministratorServlet?type=addProduct\">Add Product</a></li>");
		out.println("<li>" +
				"<a href=\"LogoutServlet\">Logout</a></li></ul></nav>");
		

		String category = request.getParameter("type");
		String productId = request.getParameter("id");
		System.out.println("Hello" +productId);
		String retailerName = request.getParameter("retailer");
		String path = request.getParameter("imagePath");
		String productName = request.getParameter("productName");
		String companyName = request.getParameter("company");
		String condition = request.getParameter("condition");
		String price = request.getParameter("price");
		String color = request.getParameter("color");

		MySqlDataStoreUtilities.updateProduct(productId, category, retailerName, path, productName, companyName, condition, price, color);
		
		if(category.equals("Smartphone"))
		{
			try
			{
				DocumentBuilderFactory instance = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = instance.newDocumentBuilder();
				Document parse = builder.parse(productXmlFileLocation);
				
				NodeList tagName = parse.getElementsByTagName("smartphone");
				if (tagName != null && tagName.getLength() > 0)
				{
					

					for (int i = 0; i < tagName.getLength(); i++)
					{
						if (tagName.item(i).getNodeType() == Node.ELEMENT_NODE)
						{
							Element item = (Element) tagName.item(i);
							if(productId.equals(item.getAttributes().getNamedItem("id").getNodeValue()))
							{
								NodeList childNodes = item.getChildNodes();

								for (int j = 0; j < childNodes.getLength(); j++)
								{
									Node item1 = childNodes.item(j);

									if ("smartPhoneImage".equals(item1.getNodeName())) {
										item1.setTextContent(path);
									}
									if ("smartPhoneName".equals(item1.getNodeName())) {
										item1.setTextContent(productName);
									}
									if ("smartPhoneCompany".equals(item1.getNodeName())) {
										item1.setTextContent(companyName);
									}
									if ("smartPhoneCondition".equals(item1.getNodeName())) {
										item1.setTextContent(condition);
									}
									if ("smartPhonePrice".equals(item1.getNodeName())) {
										item1.setTextContent(price);
									}
									if ("smartPhoneColor".equals(item1.getNodeName())) {
										item1.setTextContent(color);
									}

								}

							}

						}
					}
				}

				TransformerFactory instance1 = TransformerFactory.newInstance();
				Transformer transformer = instance1.newTransformer();
				DOMSource domSource = new DOMSource(parse);
				StreamResult streamResult = new StreamResult(new File(productXmlFileLocation));
				transformer.transform(domSource, streamResult);

			}
			catch(Exception e)
			{
				out.println("<p>Updating product failed<p>");
				e.printStackTrace();
			}

			out.println("<h3>Smartphone id: " +productId+" Successfully updated<h3>");
		}







		
	}

}
