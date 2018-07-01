
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class HomeServlet extends HttpServlet
{
	HashMap<String,CustomerDetails> customerDetailsHashMap;
	HashMap<String, Product> productsFound;
	ArrayList<String> twitterTweets;


	CustomerHashmap hashmap;

	public void init()
	{
		hashmap = new CustomerHashmap();
		customerDetailsHashMap = new HashMap<String, CustomerDetails>();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		MySqlDataStoreUtilities.deleteallProductsDatabase();
		MySqlDataStoreUtilities.LoadProductsToDatabase();

		DealMatches dealMatches = new DealMatches();
		productsFound = dealMatches.getSelectedProductsFromTweets();
		twitterTweets = dealMatches.getTwitterTweets();

		HttpSession httpSession = request.getSession(false);
		String firstName = (String)httpSession.getAttribute("firstName");
		viewPage(pw, firstName);
		pw.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


	void viewPage(PrintWriter pw, String firstName)
	{

		pw.println("<!doctype html>" +
				"<html>" +
				"<head>" +
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		pw.println("<title>Smart Portables</title>" +
				"<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
		pw.println("<script type=\"text/javascript\" src=\"autoComplete.js\"></script></head>");
		pw.println("<body onload=\"init()\">");
		pw.println("</head>");
		pw.println("<body'>" +
				"<div id=\"container\">");
		pw.println("<header>");
		pw.println("<h1><a href=\"/\">Smart<span>Portables</span></a></h1>");

		pw.println("<form  name='' action=''>");
		pw.println("<div name=''>");
		pw.println("<strong>SEARCH PRODUCTS HERE: </strong>");
		pw.println("<input type='text' name='searchId' size='40' id='searchId' onkeyup='doCompletion()' placeholder='Search Products Here...'><div id='auto-row'>");
		pw.println("<table border='0' id='complete-table' class='popupBox'></table>");
		pw.println("</div></div></form></header>");

		pw.println("<nav>" +
				"<ul>" +
				"<li class=\"start selected\"><a href=\"HomeServlet\">Home</a></li>");
		pw.println("<li>" +
				"<li class=\"start selected\"><a href=\"HomeServlet\">Welcome "+firstName+"</a></li>");
		pw.println("<li>" +
				"<a href=\"DataServlet?productType=Smartphones\">SmartPhones</a></li>");
		pw.println("<li>" +
				"<a href=\"DataServlet?productType=Speakers\">Speakers</a></li>");
		pw.println("<li>" +
				"<a href=\"DataServlet?productType=Laptops\">Laptops</a></li>");
		pw.println("<li>" +
				"<a href=\"DataServlet?productType=Warranties\">Warranty</a></li>");
		pw.println("<li>" +
				"<a href=\"ViewCartDetailServlet\">Cart</a></li>");
		pw.println("<li>" +
				"<a href=\"ViewOrders\">Your Orders</a></li>");
		pw.println("<li>" +
				"<a href=\"LogoutServlet\">Logout</a></li>");

		pw.println("</ul>" +
				"</nav>" +
				"<img class=\"header-image\" src=\"images/banner.jpg\" />");

		pw.println("<div id=\"body\">" +
				"<section id=\"content\">");
		pw.println("<br><br><br><article>" +
				"<h1>Welcome to Smart Portables</h1>");
		pw.println("<br><br>" +
				"<p>Americas Ultimate Online Shopping Site SmartPortables vision is to create Americas most reliable and frictionless commerce ecosystem that creates life-changing experiences for buyers and sellers.</p>	");

		if(twitterTweets.isEmpty())
		{
			pw.println("<p style='color:#0000FF'>"+"No Offers Found"+"</p>");
		}
		else
		{
			for(String twitter: twitterTweets)
			{
				pw.println("<p style='color:#0000FF'>"+twitter+"</p>");
			}
		}

		pw.println("</article><article><h2>Deal Matches</h2></article>");

		if(productsFound.isEmpty())
		{
			pw.println("<article>");
			pw.println("<p style='color:#0000FF'>"+"No Deals Found"+"</p>");
			pw.println("</article>");
		}
		else
		{
			for(Map.Entry<String,Product> mapping : productsFound.entrySet()){

				Product value = mapping.getValue();

				String productType = value.getType();

				pw.println("<article>");
				pw.println("<table style=\"width:100%\" style=\"height:100%\" border=\"2\" bordercolor=\"#ff0000\" cellspacing=\"0\" cellpadding=\"0\">");
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
				pw.println("</th></tr><tr><th><b>Condition:</b>");
				pw.println("New");
				pw.println("</th></tr></table></th>");
				pw.println("<th width=\"30%\"><table border=\"2\" bordercolor=\"#ff0000\"><tr><th><b>Price:");
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
					pw.println("</article>");
				}
				else
				{
					pw.println("</b></th></tr>");
					pw.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
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
					pw.println("</table></th></tr></table>");
					pw.println("</article>");
				}

			}

		}
		pw.println("</section>");

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

	}


}