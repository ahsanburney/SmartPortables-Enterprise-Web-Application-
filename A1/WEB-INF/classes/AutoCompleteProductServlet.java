

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class AutoCompleteProductServlet extends HttpServlet
{

	ArrayList<Object> products;
	HashMap<String, Smartphone> phones;
	HashMap<String, Laptop> lappy;
	HashMap<String, Accessory> extraItems;
	HashMap<String, Smartwatch> smartwatches;
	HashMap<String, Speaker> speakers;
	HashMap<String,WarrantyServlet> warranty;
	HashMap<String,Headphone> headphone;
	
	HashMap<String, Product> productHashMap = new HashMap<String, Product>();
	
	public void init()
	{
	}
	
	void fetchData()
	{
		try{
		products = AjaxUtility.fetchMysqlProducts();

			phones = (HashMap<String,Smartphone>)products.get(0);
			lappy = (HashMap<String, Laptop>)products.get(1);
			extraItems = (HashMap<String, Accessory>)products.get(2);
			smartwatches= (HashMap<String, Smartwatch>)products.get(3);
			speakers= (HashMap<String, Speaker>)products.get(4);
			headphone = (HashMap<String, Headphone>)products.get(5);
			warranty = (HashMap<String, WarrantyServlet>)products.get(6);

			productHashMap = (HashMap<String, Product>)products.get(7);
		
		}catch(Exception E){
		System.out.println("Exception Occured");
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		fetchData();
		HttpSession httpSession = request.getSession(false);
		String firstName = null;
		
		if(httpSession != null)
		{
			firstName=(String)httpSession.getAttribute("firstName");
		}
		
		
		String productName = (String)request.getAttribute("productName");
		String productType = "Products";

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		pw.println("<!doctype html>" +
				"<html><head>" +
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		pw.println("<title>Smart Portables" +
				"</title>" +
				"<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
		pw.println("<script type=\"text/javascript\" src=\"autoComplete.js\"></script>" +
				"</head>");
		pw.println("<body onload='init()'>" +
				"<div id=\"container\">" +
				"<header><h1>" +
				"<a href=\"/\">Smart<span>Portables</span></a></h1>");
		pw.println("<form  name='' action='autocomplete'>");
		pw.println("<div name=''>");
		pw.println("<strong>SEARCH PRODUCTS HERE: </strong>");
		pw.println("<input type='text' name='searchId' size='40' id='searchId' onkeyup='doCompletion()' placeholder='Search Here...'>");
		pw.println("<div id='auto-row'>");
		pw.println("<table border='0' id='complete-table' class='popupBox'>" +
				"</table>");
		pw.println("</div>");
		pw.println("</div>");
		pw.println("</form>");
		
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
	
		if(productType.equals("Products"))
		{
			pw.println("<article><h3>Searched Product</h3></article>");
			pw.println("<article class=\"expanded\">");


			for(Map.Entry<String,Product> entry : productHashMap.entrySet()){
				
				Product value = entry.getValue();
				String valueName = value.getName();

				if (valueName != null) {
					valueName = valueName.trim().toLowerCase();
				}
				if(productName.equals(valueName))
				{
					pw.println("<table style=\"width:100%\" style=\"height:100%\" border=\"2\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
					pw.println("<tr><th width=\"30%\">");
					pw.println("<a href=\"\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
					pw.println(value.getImage());
					pw.println("\" /></a>");
					pw.println("</th>");
					pw.println("<th width=\"40%\"><table border=\"2\" bordercolor=\"#ff0000\"><tr><th width=\"40\"><b>");
					pw.println(value.getName());
					pw.println("</b></th></tr><tr><th width=\"40\"><b>Company:</b>");
					pw.println(value.getCompany());
					pw.println("</b></th></tr><tr><th width=\"40\"><b>Color:</b>");
					pw.println(value.getColor());
					pw.println("</th></tr><tr><td><b>Condition:</b>");
					pw.println("New");
					pw.println("</th></tr></table></td>");
					pw.println("<th width=\"30%\"><table bordercolor=\"#ff0000\" border=\"2\"><tr><th><b>Price:");
					pw.println(value.getPrice());
					
					if(firstName != null && !firstName.isEmpty())
					{
						pw.println("<tr><th><form method = 'get' action = 'WriteReview'>");
						pw.println("<input class = 'formbutton' type = 'submit' name = '"+ value.getName() +"' value = 'Write Review'>");
						pw.println("<input type='hidden' name='productType' value='"+productType+"'>");
						pw.println("<input type='hidden' name='productName' value='"+value.getName()+"'>");
						pw.println("<input type='hidden' name='image' value='"+value.getImage()+"'>");
						pw.println("<input type='hidden' name='price' value='"+value.getPrice()+"'>");
						pw.println("<input type='hidden' name='color' value='"+value.getColor()+"'>");
						pw.println("<input type='hidden' name='condition' value='"+value.getCondition()+"'>");
						pw.println("<input type='hidden' name='company' value='"+value.getCompany()+"'>");
						pw.println("<input type='hidden' name='retailer' value='"+value.getRetailer()+"'>");
						pw.println("<input type='hidden' name='quantity' value='"+1+"'>");
						pw.println("</form></th></tr>");
						
						pw.println("<tr><th><form method = 'get' action = 'ViewReviews'>");
						pw.println("<input class = 'formbutton' type = 'submit' name = '"+ value.getName() +"' value = 'View Reviews'>");
						pw.println("<input type='hidden' name='productType' value='"+productType+"'>");
						pw.println("<input type='hidden' name='productName' value='"+value.getName()+"'>");
						pw.println("<input type='hidden' name='image' value='"+value.getImage()+"'>");
						pw.println("<input type='hidden' name='price' value='"+value.getPrice()+"'>");
						pw.println("<input type='hidden' name='color' value='"+value.getColor()+"'>");
						pw.println("<input type='hidden' name='condition' value='"+value.getCondition()+"'>");
						pw.println("<input type='hidden' name='company' value='"+value.getCompany()+"'>");
						pw.println("<input type='hidden' name='retailer' value='"+value.getRetailer()+"'>");
						pw.println("<input type='hidden' name='quantity' value='"+1+"'>");
						pw.println("</form></th></tr>");
						
						pw.println("<tr><th><form method = 'get' action = 'AddingProductToCart'>");
						pw.println("<input class = 'formbutton' type = 'submit' name = '"+ value.getName() +"' value = 'Add to Cart'>");
						pw.println("<input type='hidden' name='productName' value='"+value.getName()+"'>");
						pw.println("<input type='hidden' name='image' value='"+value.getImage()+"'>");
						pw.println("<input type='hidden' name='price' value='"+value.getPrice()+"'>");
						pw.println("<input type='hidden' name='quantity' value='"+1+"'>");
						pw.println("</form></th></tr>");
						
						pw.println("</table></th></tr></table>");
					}
			
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