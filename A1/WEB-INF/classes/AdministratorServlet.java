


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;



public class AdministratorServlet extends HttpServlet {

	ArrayList<Object> products;
	HashMap<String, Smartphone> phones;
	HashMap<String, Laptop> lappy;
	HashMap<String, Accessory> extraItems;
	HashMap<String, Smartwatch> smartwatches;
	HashMap<String, Speaker> speakers;
	HashMap<String,WarrantyServlet> warranty;
	HashMap<String,Headphone> headphone;

	ProductSaxParser saxParser = new ProductSaxParser();

	void loadXmlData()
	{
		try{
			products = saxParser.loadDataStore();

			phones = (HashMap<String,Smartphone>)products.get(0);
			lappy = (HashMap<String, Laptop>)products.get(1);
			extraItems = (HashMap<String, Accessory>)products.get(2);
			smartwatches= (HashMap<String, Smartwatch>)products.get(3);
			speakers= (HashMap<String, Speaker>)products.get(4);
			headphone = (HashMap<String, Headphone>)products.get(5);
			warranty = (HashMap<String, WarrantyServlet>)products.get(6);


		}catch(Exception E){
			System.out.println("Exception Occured");
		}
	}


	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {

		PrintWriter pw = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		loadXmlData();

		pw.println("<!doctype html>" +
				"<html><head>" +
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		pw.println("<title>Smart Portables</title>" +
				"<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		pw.println("<body><div id=\"container\">");
		pw.println("<header>" +
				"<h1><a href=\"/\">Smart<span>Portables</span></a></h1><h3>ADMIN AREA</h3></header>");
		pw.println("<nav><ul>");
		pw.println("<li class=\"\"><a href=\"AdministratorServlet\">Product List</a></li>");
		pw.println("<li><a href=\"AddProduct?type=addProduct\">Add Product</a></li>");
		pw.println("<li><a href=\"InventoryServlet\">Inventory Report</a></li>");
		pw.println("<li><a href=\"SalesReportServlet\">Sales Report</a></li>");
		pw.println("<li><a href=\"AnalyticsServlet\">Data Analytics</a></li>");
		pw.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");

		pw.println("<body>"+
				"<article><h3 align=\"center\">Product List</h3>");

		pw.println("<body>"+
				"<article><h3 align=\"center\">Smartphones</h3>"+
				"<fieldset>"+
				"<div>");
				pw.println("<table border='2' bordercolor=\"#ff0000\">"+
				"<tr><th>Product Id</th><th>Retailer</th><th>Name</th><th>Company</th><th>Condition</th><th>Color</th><th>Manufacturer Rebate</th><th>Retailer Discount</th><th>Delete</th><th>Update</th></tr>");
		for(Map.Entry<String,Smartphone> map : phones.entrySet())
		{
			Smartphone phone = map.getValue();

			String id=phone.getId();
			String retailerName=phone.getRetailer();
			String productImage=phone.getImage();
			String productName=phone.getName();
			String productCompany=phone.getCompany();
			String productCondition=phone.getCondition();
			String productColor=phone.getColor();
			float manfRebate=phone.getMafRebate();
			float retDiscount =phone.getReDiscount();
			float productPrice=phone.getPrice();


			pw.println("<form action='/A1/DeleteProduct' method='post'>"+
					"<input type='hidden' name='productName' value='"+productName+"'>"+
					"<input type='hidden' name='type' value='smartphone'>"+
					"<th>"+id+"</th>"+
					"<th>"+retailerName+"</th>"+
					"<th>"+productName+"</th>"+
					"<th>"+productCompany+"</th>"+
					"<th>"+productCondition+"</th>"+
					"<th>"+productColor+"</th>"+
					"<th>"+manfRebate+"</th>"+
					"<th>"+retDiscount+"</th>"+
					"<td><input class='formbutton' type='submit'  value='Delete'></td>"+
					"</form>"+

					"<form action='/A1/UpdateProduct' method='get'>"+
					"<input type='hidden' name='productName' value='"+productName+"'>"+
					"<input type='hidden' name='id' value='"+id+"'>"+
					"<input type='hidden' name='retailer' value='"+retailerName+"'>"+
					"<input type='hidden' name='image' value='"+productImage+"'>"+
					"<input type='hidden' name='company' value='"+productCompany+"'>"+
					"<input type='hidden' name='condition' value='"+productCondition+"'>"+
					"<input type='hidden' name='color' value='"+productColor+"'>"+
					"<input type='hidden' name='price' value='"+productPrice+"'>"+
					"<input type='hidden' name='type' value='Smartphone'>"+ "<td><input class='formbutton' type='submit'  value='Update'></td></tr>"+
					"</form>");

		}
		pw.println("</table>"+
				"</fieldset></article</div>"+
				"<body><article><h3 align=\"center\">Laptops</h3>"+
				"<fieldset>"+
				"<table border='2' bordercolor=\"#ff0000\">"+
				"<tr><th>Product Id</th><th>Retailer</th><th>Name</th><th>Company</th><th>Condition</th><th>Color</th><th>Manufacturer Rebate</th><th>Retailer Discount</th><th>Delete</th><th>Update</th></tr>");

		for(Map.Entry<String,Laptop> map : lappy.entrySet())
		{
			Laptop laptop = map.getValue();
			String laptopId=laptop.getId();
			String laptopRetailer=laptop.getRetailer();
			String laptopImage=laptop.getImage();
			String laptopName=laptop.getName();
			String laptopCompany=laptop.getCompany();
			String laptopCondition=laptop.getCondition();
			String laptopColor=laptop.getColor();
			float manfRebate=laptop.getMafRebate();
			float retDiscount =laptop.getReDiscount();
			float price=laptop.getPrice();

			pw.println("<form action='/A1/DeleteProduct' method='post'>"+
					"<input type='hidden' name='productName' value='"+laptopName+"'>"+
					"<input type='hidden' name='type' value='laptop'>"+
					"<th>"+laptopId+"</th>"+
					"<th>"+laptopRetailer+"</th>"+
					"<th>"+laptopName+"</th>"+
					"<th>"+laptopCompany+"</th>"+
					"<th>"+laptopCondition+"</th>"+
					"<th>"+laptopColor+"</th>"+
					"<th>"+manfRebate+"</th>"+
					"<th>"+retDiscount+"</th>"+
					"<th><input class='formbutton' type='submit'  value='Delete'></td>"+
					"</form>");

			pw.println("<form action='/A1/UpdateProduct' method='get'>"+
					"<input type='hidden' name='productName' value='"+laptopName+"'>"+
					"<input type='hidden' name='id' value='"+laptopId+"'>"+
					"<input type='hidden' name='retailer' value='"+laptopRetailer+"'>"+
					"<input type='hidden' name='image' value='"+laptopImage+"'>"+
					"<input type='hidden' name='company' value='"+laptopCompany+"'>"+
					"<input type='hidden' name='condition' value='"+laptopCondition+"'>"+
					"<input type='hidden' name='color' value='"+laptopColor+"'>"+
					"<input type='hidden' name='price' value='"+price+"'>"+
					"<input type='hidden' name='type' value='Laptop'>"+
					"<td><input class='formbutton' type='submit'  value='Update'></td></tr>"+
					"</form>");

		}

		pw.println("</table>"+
				"</fieldset></article</div>"+



				"<body><article><h3 align=\"center\">SmartWatches</h3>"+
				"<fieldset>"+
				"<table border='2' bordercolor=\"#ff0000\">"+
				"<tr><th>Product Id</th><th>Retailer</th><th>Name</th><th>Company</th><th>Condition</th><th>Color</th><th>Manufacturer Rebate</th><th>Retailer Discount</th><th>Delete</th><th>Update</th></tr>");

		for(Map.Entry<String,Smartwatch> map :smartwatches.entrySet())
		{
			Smartwatch watch = map.getValue();

			String watchId=watch.getId();
			String watchRetailer=watch.getRetailer();
			String watchImage=watch.getImage();
			String watchName=watch.getName();
			String watchCompany=watch.getCompany();
			String watchCondition=watch.getCondition();
			String watchColor=watch.getColor();
			float manfRebate=watch.getMafRebate();
			float retDiscount =watch.getReDiscount();
			float watchPrice=watch.getPrice();


			pw.println("<form action='/A1/DeleteProduct' method='post'>"+
					"<input type='hidden' name='productName' value='"+watchName+"'>"+
					"<input type='hidden' name='type' value='smartwatch'>"+
					"<th>"+watchId+"</th>"+
					"<th>"+watchRetailer+"</th>"+
					"<th>"+watchName+"</th>"+
					"<th>"+watchCompany+"</th>"+
					"<th>"+watchCondition+"</th>"+
					"<th>"+watchColor+"</th>"+
					"<th>"+manfRebate+"</th>"+
					"<th>"+retDiscount+"</th>"+
					"<th><input class='formbutton' type='submit'  value='Delete'></td>"+
					"</form>");

			pw.println("<form action='/A1/UpdateProduct' method='get'>"+
					"<input type='hidden' name='productName' value='"+watchName+"'>"+
					"<input type='hidden' name='id' value='"+watchId+"'>"+
					"<input type='hidden' name='retailer' value='"+watchRetailer+"'>"+
					"<input type='hidden' name='image' value='"+watchImage+"'>"+
					"<input type='hidden' name='company' value='"+watchCompany+"'>"+
					"<input type='hidden' name='condition' value='"+watchCondition+"'>"+
					"<input type='hidden' name='color' value='"+watchColor+"'>"+
					"<input type='hidden' name='price' value='"+watchPrice+"'>"+
					"<input type='hidden' name='type' value='Smartwatch'>"+

					"<td><input class='formbutton' type='submit'  value='Update'></td></tr>"+
					"</form>");

		}
		pw.println("</table>"+
				"</fieldset></article</div>"+

				//====================================HeadPhones=========================
				"<body><article><h3 align=\"center\">HeadPhones</h3>"+
				"<fieldset>"+
				"<table border='2' bordercolor=\"#ff0000\">"+
				"<tr><th>Product Id</th><th>Retailer</th><th>Name</th><th>Company</th><th>Condition</th><th>Color</th><th>Manufacturer Rebate</th><th>Retailer Discount</th><th>Delete</th><th>Update</th></tr>");
		for(Map.Entry<String,Headphone> map : headphone.entrySet())
		{
			Headphone watch = map.getValue();

			String watchId=watch.getId();
			String watchRetailer=watch.getRetailer();
			String watchImage=watch.getImage();
			String watchName=watch.getName();
			String watchCompany=watch.getCompany();
			String watchCondition=watch.getCondition();
			String watchColor=watch.getColor();
			float manfRebate=watch.getMafRebate();
			float retDiscount =watch.getReDiscount();
			float watchPrice=watch.getPrice();


			pw.println("<form action='/A1/DeleteProduct' method='post'>"+
					"<input type='hidden' name='productName' value='"+watchName+"'>"+
					"<input type='hidden' name='type' value='headphone'>"+
					"<th>"+watchId+"</th>"+
					"<th>"+watchRetailer+"</th>"+
					"<th>"+watchName+"</th>"+
					"<th>"+watchCompany+"</th>"+
					"<th>"+watchCondition+"</th>"+
					"<th>"+watchColor+"</th>"+
					"<th>"+manfRebate+"</th>"+
					"<th>"+retDiscount+"</th>"+
					"<th><input class='formbutton' type='submit'  value='Delete'></td>"+
					"</form>");

			pw.println("<form action='/A1/UpdateProduct' method='get'>"+
					"<input type='hidden' name='productName' value='"+watchName+"'>"+
					"<input type='hidden' name='id' value='"+watchId+"'>"+
					"<input type='hidden' name='retailer' value='"+watchRetailer+"'>"+
					"<input type='hidden' name='image' value='"+watchImage+"'>"+
					"<input type='hidden' name='company' value='"+watchCompany+"'>"+
					"<input type='hidden' name='condition' value='"+watchCondition+"'>"+
					"<input type='hidden' name='color' value='"+watchColor+"'>"+
					"<input type='hidden' name='price' value='"+watchPrice+"'>"+
					"<input type='hidden' name='type' value='Headphone'>"+

					"<td><input class='formbutton' type='submit'  value='Update'></td></tr>"+
					"</form>");

		}
		pw.println("</table>"+
				"</fieldset></article</div>"+




				//------------Speakers

				"<body><article><h3 align=\"center\">Speakers</h3>"+
				"<fieldset>"+
				"<table border='2' bordercolor=\"#ff0000\">"+
				"<tr><th>Product Id</th><th>Retailer</th><th>Name</th><th>Company</th><th>Condition</th><th>Color</th><th>Manufacturer Rebate</th><th>Retailer Discount</th><th>Delete</th><th>Update</th></tr>");

		for(Map.Entry<String,Speaker> map :speakers.entrySet())
		{
			Speaker speaker = map.getValue();

			String speakerId=speaker.getId();
			String speakerRetailer=speaker.getRetailer();
			String speakerImage=speaker.getImage();
			String speakerName=speaker.getName();
			String speakerCompany=speaker.getCompany();
			String speakerCondition=speaker.getCondition();
			String speakerColor=speaker.getColor();
			float manfRebate=speaker.getMafRebate();
			float retDiscount =speaker.getReDiscount();
			float speakerPrice=speaker.getPrice();


			pw.println("<form action='/A1/DeleteProduct' method='post'>"+
					"<input type='hidden' name='productName' value='"+speakerName+"'>"+
					"<input type='hidden' name='type' value='speaker'>"+
					"<th>"+speakerId+"</th>"+
					"<th>"+speakerRetailer+"</th>"+
					"<th>"+speakerName+"</th>"+
					"<th>"+speakerCompany+"</th>"+
					"<th>"+speakerCondition+"</th>"+
					"<th>"+speakerColor+"</th>"+
					"<th>"+manfRebate+"</th>"+
					"<th>"+retDiscount+"</th>"+
					"<th><input class='formbutton' type='submit'  value='Delete'></td>"+
					"</form>");

			pw.println("<form action='/A1/UpdateProduct' method='get'>"+
					"<input type='hidden' name='productName' value='"+speakerName+"'>"+
					"<input type='hidden' name='id' value='"+speakerId+"'>"+
					"<input type='hidden' name='retailer' value='"+speakerRetailer+"'>"+
					"<input type='hidden' name='image' value='"+speakerImage+"'>"+
					"<input type='hidden' name='company' value='"+speakerCompany+"'>"+
					"<input type='hidden' name='condition' value='"+speakerCondition+"'>"+
					"<input type='hidden' name='color' value='"+speakerColor+"'>"+
					"<input type='hidden' name='price' value='"+speakerPrice+"'>"+
					"<input type='hidden' name='type' value='Speaker'>"+

					"<td><input class='formbutton' type='submit'  value='Update'></td></tr>"+
					"</form>");

		}
		pw.println("</table>"+
				"</fieldset></article</div>"+

				//=======================Warranty===============================================/
				"<body><article><h3 align=\"center\">Warraty</h3>"+
				"<fieldset>"+
				"<table border='2' bordercolor=\"#ff0000\">"+
				"<tr><th>Product Id</th><th>Retailer</th><th>Name</th><th>Company</th><th>Condition</th><th>Color</th><th>Manufacturer Rebate</th><th>Retailer Discount</th><th>Delete</th><th>Update</th></tr>");

		for(Map.Entry<String,WarrantyServlet> map : warranty.entrySet())
		{
			WarrantyServlet watch = map.getValue();

			String watchId=watch.getId();
			String watchRetailer=watch.getRetailer();
			String watchImage=watch.getImage();
			String watchName=watch.getName();
			String watchCompany=watch.getCompany();
			String watchCondition=watch.getCondition();
			String watchColor=watch.getColor();
			float manfRebate=watch.getMafRebate();
			float retDiscount =watch.getReDiscount();
			float watchPrice=watch.getPrice();


			pw.println("<form action='/A1/DeleteProduct' method='post'>"+
					"<input type='hidden' name='productName' value='"+watchName+"'>"+
					"<input type='hidden' name='type' value='warranty'>"+
					"<th>"+watchId+"</th>"+
					"<th>"+watchRetailer+"</th>"+
					"<th>"+watchName+"</th>"+
					"<th>"+watchCompany+"</th>"+
					"<th>"+watchCondition+"</th>"+
					"<th>"+watchColor+"</th>"+
					"<th>"+manfRebate+"</th>"+
					"<th>"+retDiscount+"</th>"+
					"<th><input class='formbutton' type='submit'  value='Delete'></td>"+
					"</form>");

			pw.println("<form action='/A1/UpdateProduct' method='get'>"+
					"<input type='hidden' name='productName' value='"+watchName+"'>"+
					"<input type='hidden' name='id' value='"+watchId+"'>"+
					"<input type='hidden' name='retailer' value='"+watchRetailer+"'>"+
					"<input type='hidden' name='image' value='"+watchImage+"'>"+
					"<input type='hidden' name='company' value='"+watchCompany+"'>"+
					"<input type='hidden' name='condition' value='"+watchCondition+"'>"+
					"<input type='hidden' name='color' value='"+watchColor+"'>"+
					"<input type='hidden' name='price' value='"+watchPrice+"'>"+
					"<input type='hidden' name='type' value='warranty'>"+

					"<td><input class='formbutton' type='submit'  value='Update'></td></tr>"+
					"</form>");

		}
		pw.println("</table>"+
				"</fieldset></article</div>"+
				
				"<body><article><h3 align=\"center\">Accessories</h3>"+
				"<table border='2' bordercolor=\"#ff0000\">"+
				"<tr><th>Id</th><th>Retailer</th><th>Name</th><th>Company</th><th>Condition</th><th>Color</th><th>Manufacturer Rebate</th><th>Retailer Discount</th></tr>");

		for(Map.Entry<String,Accessory> map : extraItems.entrySet())
		{
			Accessory acc = map.getValue();

			String id=acc.getId();
			String retailer=acc.getRetailer();
			String image=acc.getImage();
			String name=acc.getName();
			String company=acc.getCompany();
			String condition=acc.getCondition();
			String color=acc.getColor();
			float manfRebate=acc.getMafRebate();
			float retDiscount =acc.getReDiscount();
			float price=acc.getPrice();

			pw.println("<form action='/A1/DeleteProduct' method='post'>"+
					"<input type='hidden' name='productName' value='"+name+"'>"+
					"<input type='hidden' name='type' value='accessory'>"+
					"<th>"+id+"</th>"+
					"<th>"+retailer+"</th>"+
					"<th>"+name+"</th>"+
					"<th>"+company+"</th>"+
					"<th>"+condition+"</th>"+
					"<th>"+color+"</th>"+
					"<th>"+manfRebate+"</th>"+
					"<th>"+retDiscount+"</th>"+
					"<th><input class='formbutton' type='submit'  value='Delete'></th>"+
					"</form>");

			pw.println("<form action='/A1/UpdateProduct' method='get'>"+
					"<input type='hidden' name='productName' value='"+name+"'>"+
					"<input type='hidden' name='id' value='"+id+"'>"+
					"<input type='hidden' name='retailer' value='"+retailer+"'>"+
					"<input type='hidden' name='image' value='"+image+"'>"+
					"<input type='hidden' name='company' value='"+company+"'>"+
					"<input type='hidden' name='condition' value='"+condition+"'>"+
					"<input type='hidden' name='color' value='"+color+"'>"+
					"<input type='hidden' name='price' value='"+price+"'>"+
					"<input type='hidden' name='type' value='Accessory'>"+

					"<td><input class='formbutton' type='submit'  value='Update'></td></tr>"+
					"</form>");

		}
		pw.println("</table></table>"+
				"</article</div>"+
				"</div>"+
				"</body>"+
				"</html>");


		pw.close();

	}

	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {

		doPost(request, response);
	}

}
