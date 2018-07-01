

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import Datavisualization.*;


public class AnalyticsServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String category = "";
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		pw.println("<!doctype html>" +
				"<html><head>" +
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		pw.println("<title>Smart Portables</title>" +
				"<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		pw.println("<body><div id=\"container\">");
		pw.println("<header>" +
				"<script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>"+
				"<h1><a href=\"/\">Smart<span>Portables</span></a></h1><h3>ADMIN AREA</h3></header>");
		pw.println("<nav><ul>");
		pw.println("<li class=\"\"><a href=\"AdministratorServlet\">Product List</a></li>");
		pw.println("<li><a href=\"AddProduct?type=addProduct\">Add Product</a></li>");
		pw.println("<li><a href=\"InventoryServlet\">Inventory Report</a></li>");
		pw.println("<li><a href=\"SalesReportServlet\">Sales Report</a></li>");
		pw.println("<li><a href=\"AnalyticsServlet\">Data Analytics</a></li>");
		pw.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");
		pw.println("<div align='center' id=\"body\">" +
				"<article><h3 align=\"center\">Select any query from the list and click submit</h3>");
		pw.println("<fieldset>");
		pw.println("<table border='2' bordercolor=\"#ff0000\"><th>");
		pw.println("<form action='AnalyticsServlet' method='post'>");
		pw.println("<p><select name='request'>");
		pw.println("<option name='request' value='1' selected>1.Print the list of all products and their ratings</option>");
		pw.println("<option name='request' value='2'>2.Print a list of reviews where rating greater than 3</option>");
		pw.println("<option name='request' value='3'>3.Get a list of products that got review rating 5 and price more than thousand</option>");
		pw.println("<option name='request' value='4'>4.Print a list of how many reviews for every product </option>");
		pw.println("<option name='request' value='5'>5.Get the list of reviews for shoppers in Chicago</option>");
		pw.println("<option name='request' value='6'>6.Find highest price product reviewed/sold in every city</option>");
		pw.println("<option name='request' value='7'>7.Find highest price product reviewed/sold in every zip-code</option>");
		pw.println("<option name='request' value='8'>8.Get the top 5 list of liked products for every city</option>");
		pw.println("<option name='request' value='9'>9.Print a list of reviews grouped by City</option>");
		pw.println("<option name='request' value='10'>10.Print a list of reviews grouped by zip-code</option>");
		pw.println("<option name='request' value='11'>11.Get the total number of products reviewed and got Rating 5 in Every City</option>");
		pw.println("<option name='request' value='12'>12.Top 3 Most liked product in every city</option>");
		pw.println("<option name='request' value='13'>13.Print the median product prices per city</option>");
		pw.println("<option name='request' value='14'>14.Get top 5 list of most liked and expensive products sorted by retailer name for every city</option>");
		pw.println("<option name='request' value='15'>15.Get the top 5 list of most Disliked products sorted by retailer name for every city</option>");
		pw.println("<option name='request' value='16'>16.Get the top 5 list of most Disliked products sorted by retailer name for every zip-code</option>");
		pw.println("<option name='request' value='17'>17.Get the top 2 list of zip-codes where highest number of products got review rating 5</option>");
		pw.println("<option name='request' value='18'>18.Get a list of reviews where reviewer age greater than 50 and the list is sorted by age in every city</option>");
		pw.println("<option name='request' value='19'>19.Get the top 5 list of most liked products sorted by manufacturer name for every city</option>");
		pw.println("<option name='request' value='20'>20.Search reviews text for keywords (pattern-matching) and print the list of reviews that have the matched keywords</option>");
		pw.println("</select></p>");
		pw.println("<p><input name=\"send\"   class=\"formbutton\" value=\"Submit\" type=\"submit\" /></p>");
		pw.println("</form>");
		pw.println("</th></table>");
		pw.println("</fieldset>" +
				"</article></div>" +
				"</div></body>" +
				"</html>");

		pw.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter pw = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");

		String s = "";
		s = request.getParameter("request");
		System.out.println(s);
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
		pw.println("<li><a href=\"AnalyticsServlet\">Data Analytics</a></li>");
		pw.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");
//========================================================================================
		if (s.equals("1")) {
			int s6=1;
			LinkedHashMap<String, Double> ListofAllProductsAndTheirRatings;
			ListofAllProductsAndTheirRatings = MongoDBDataStoreUtilities.ListofAllProductsAndTheirRatings();
			pw.println("<h3 align='center'>List of All Products And Their Ratings</h3>");
			pw.println("<table border='2' bordercolor=\"#ff0000\">");
			pw.println("<tr><th>Sr No.</th><th><b>ProductModelName</b></th><th><b>Average Rating</b></th></tr>");
			for (Map.Entry<String, Double> mapping : ListofAllProductsAndTheirRatings.entrySet()) {

				String s1 = mapping.getKey();
				Double mappingValue = mapping.getValue();
				pw.println("<tr><th>" + s6 + "</th>");
				pw.println("<th>" + s1 + "</th>");
				pw.println("<th>" + mappingValue + "</th></tr>");
				s6++;
			}

			pw.println("</table>");
		}

		//==============================================================================================//

		if(s.equals("2"))
		{

			HashMap<String,Review> reviewHashMap;
			reviewHashMap = MongoDBDataStoreUtilities.Printalistofreviewswhereratinggreaterthan3();
			String s1;
			String s2;
			int s3;
			Date s4;
			String s5;
			int s6=1;
			pw.println("<h3 align='center'>List of Reviews Where Rating Greater Than 3</h3>");
			pw.println("<table border='2' bordercolor=\"#ff0000\">");
			pw.println("<tr><th>Sr No.</th><th>ProductModelName</th><th>Email</th><th>ReviewRating</th><th>ReviewDate</th><th>Review text</th></tr>");
			for(Map.Entry<String,Review> mapping :reviewHashMap.entrySet()){
				Review value = mapping.getValue();
				s1 = value.getProductName();
				s2 = value.getEmailId();
				s3 = value.getReviewRating();
				DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
				s4 = value.getReviewDate();
				String format1 = format.format(s4);
				s5 = value.getReviewText();
				pw.println("<tr><th>"+s6+"</th>");
				pw.println("<th>"+s1+"</th>");
				pw.println("<th>"+s2+"</th>");
				pw.println("<th>"+s3+"</th>");
				pw.println("<th>"+format1+"</th>");
				pw.println("<th>"+s5+"</th></tr>");
				s6++;
			}
			pw.println("</table>");
		}

		//=================================================================================================//

		if(s.equals("3"))
		{

			HashMap<String,Review> reviews;
			reviews = MongoDBDataStoreUtilities.fetchReviewListwhereRating5andprice100();
			String productname;
			String userEmail;
			int rating;
			Date date;
			String desc;
			int i=1;
			pw.println("<h3 align='center'>List of Products that got review rating 5 And Price more Than Thousand</h3>");
			pw.println("<table border='2' bordercolor=\"#ff0000\">");
			pw.println("<tr><th>Sr No.</th><th>Product Name</th><th>Email Id</th><th>Review Rating</th><th>Review Date</th><th>Review text</th></tr>");
			for(Map.Entry<String,Review> mapping :reviews.entrySet()){
				Review value = mapping.getValue();
				productname = value.getProductName();
				userEmail = value.getEmailId();
				rating = value.getReviewRating();
				DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
				date = value.getReviewDate();
				String s1 = format.format(date);
				desc = value.getReviewText();
				pw.println("<tr><th>"+i+"</th>");
				pw.println("<th>"+productname+"</th>");
				pw.println("<th>"+userEmail+"</th>");
				pw.println("<th>"+rating+"</th>");
				pw.println("<th>"+s1+"</th>");
				pw.println("<th>"+desc+"</th></tr>");
				i++;
			}
			pw.println("</table>");
		}

		//====================================================================================================//

		if(s.equals("4"))
		{

			LinkedHashMap<String, Integer> stringIntegerLinkedHashMap;
			stringIntegerLinkedHashMap = MongoDBDataStoreUtilities.fetchReviewsCountForProducts();
			int i=1;
			pw.println("<h3 align='center'>List of How many reviews for every Product</h3>");
			pw.println("<table border='2' bordercolor=\"#ff0000\">");
			pw.println("<tr><th>Sr No.</th><th><b>ProductModelName</b></th><th><b>Number of Reviews for this product</b></th></tr>");
			for(Map.Entry<String, Integer> mapping :stringIntegerLinkedHashMap.entrySet())
			{
				String mappingKey = mapping.getKey();
				Integer mappingValue = mapping.getValue();
				pw.println("<tr><th>"+i+"</th>");
				pw.println("<th>"+mappingKey+"</th>");
				pw.println("<th>"+mappingValue+"</th></tr>");
				i++;
			}
			pw.println("</table>");
		}

//=========================================================================================================//

		if(s.equals("5"))
		{
			HashMap<String,Review> reviews;
			reviews = MongoDBDataStoreUtilities.fetchReviewsOfChicagoShoppers();
			String productName;
			String userEmail;
			int rating;
			Date date;
			String desc;
			int b=1;
			pw.println("<h3 align='center'>List of Reviews for shoppers in Chicago</h3>");
			pw.println("<table border='2' bordercolor=\"#ff0000\">");
			pw.println("<tr><th>Sr No.</th><th>ProductModelName</th><th>Email</th><th>ReviewRating</th><th>ReviewDate</th><th>Reviewtext</th></tr>");
			for(Map.Entry<String,Review> mapping :reviews.entrySet()){
				Review value = mapping.getValue();
				productName = value.getProductName();
				userEmail = value.getEmailId();
				rating = value.getReviewRating();
				DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
				date = value.getReviewDate();
				String dateOfReview = format.format(date);
				desc = value.getReviewText();
				pw.println("<tr><th>"+b+"</th>");
				pw.println("<th>"+productName+"</th>");
				pw.println("<th>"+userEmail+"</th>");
				pw.println("<th>"+rating+"</th>");
				pw.println("<th>"+dateOfReview+"</th>");
				pw.println("<th>"+desc+"</th></tr>");
				b++;
			}
			pw.println("</table>");
		}
//==============================================================================================================//
		if(s.equals("6"))
		{

			LinkedHashMap<String, ArrayList<Object>> linkedHashMap;
			linkedHashMap = MongoDBDataStoreUtilities.Findhighestpriceproductreviewedsoldineverycity();
			int i=1;
			pw.println("<h3 align='center'>List of Highest price product reviewed/sold in every city</h3>");
			pw.println("<table border='2' bordercolor=\"#ff0000\">");
			pw.println("<tr><th>Sr No.</th><th><b>RetailerCity</b></th><th><b>ProductModelName</b></th><th><b>ProductPrice</b></th></tr>");
			for(Map.Entry<String, ArrayList<Object>> mapping :linkedHashMap.entrySet())
			{
				String key = mapping.getKey();
				ArrayList<Object> values = mapping.getValue();
				pw.println("<tr><th>"+i+"</td>");
				pw.println("<th>"+values.get(0)+"</th>");
				pw.println("<th>"+values.get(1)+"</th>");
				pw.println("<th>"+values.get(2)+"</th></tr>");
				i++;
			}

			pw.println("</table>");

		}
//===================================================================================================================//
		if(s.equals("7"))
		{

			LinkedHashMap<String, ArrayList<Object>> linkedHashMap;
			linkedHashMap = MongoDBDataStoreUtilities.Findhighestpriceproductreviewedsoldineveryzipcode();
			int i=1;
			pw.println("<h3 align='center'>Highest Price Product Reviewed In Every Zip Code</h3>");
			pw.println("<table border='2' bordercolor=\"#ff0000\">");
			pw.println("<tr><th>Sr No.</th><th><b>Zip Code</b></th><th><b>Product Name</b></th><th><b>Product Price</b></th></tr>");
			for(Map.Entry<String, ArrayList<Object>> mapping :linkedHashMap.entrySet())
			{
				String key = mapping.getKey();
				ArrayList<Object> mappingValue = mapping.getValue();
				pw.println("<tr><th>"+i+"</th>");
				pw.println("<th>"+mappingValue.get(0)+"</th>");
				pw.println("<th>"+mappingValue.get(1)+"</th>");
				pw.println("<th>"+mappingValue.get(2)+"</th></tr>");
				i++;
			}
			pw.println("</table>");

		}
//=====================================================================================================================//
		if(s.equals("8"))
		{

			LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>> linkedHashMap = new LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>>();
			linkedHashMap = MongoDBDataStoreUtilities.getthetop5listoflikedproductsforeverycity();
			pw.println("<h3 align='center'>List of top 5 liked products for every city</h3>");
			for(Map.Entry<String, LinkedHashMap<String, ArrayList<Object>>> hashMapEntry :linkedHashMap.entrySet())
			{
				String key1 = hashMapEntry.getKey();
				LinkedHashMap<String, ArrayList<Object>> productsListHashMap = hashMapEntry.getValue();
				pw.println("<h3 align=\"center\">"+key1+"</h3>");
				int i=1;
				pw.println("<table border='2' bordercolor=\"#ff0000\">");
				pw.println("<tr><th>Sr No.</th><th><b>ProductModelName</b></th><th><b>Rating</b></th></tr>");
				for(Map.Entry<String, ArrayList<Object>> m :productsListHashMap.entrySet())
				{
					ArrayList<Object> objectArrayList = m.getValue();
					pw.println("<tr><th>"+i+"</th>");
					pw.println("<th>"+objectArrayList.get(1)+"</th>");
					pw.println("<th>"+objectArrayList.get(2)+"</th></tr>");
					i++;
				}
				pw.println("</table>");
			}
		}
//================================================================================================================================//
		if(s.equals("9"))
		{


			LinkedHashMap<String, LinkedHashMap<String, Review>> linkedHashMap = new LinkedHashMap<String, LinkedHashMap<String, Review>>();
			linkedHashMap = MongoDBDataStoreUtilities.printalistofreviewsgroupedbyCity();

			pw.println("<h3 align='center'>List of reviews grouped by City</h3>");

			for(Map.Entry<String, LinkedHashMap<String, Review>> mapEntry :linkedHashMap.entrySet())
			{
				String key1 = mapEntry.getKey();
				LinkedHashMap<String, Review> productsListHashMap = mapEntry.getValue();
				pw.println("<h3 align=\"center\">"+key1+"</h3>");
				String s1;
				String s2;
				int s3;
				Date s4;
				String s5;
				int i=1;

				pw.println("<table border='2' bordercolor=\"#ff0000\">");
				pw.println("<tr><th>Sr No.</th><th>Product Name</th><th>Email Id</th><th>Review Rating</th><th>Review Date</th><th>Review text</th></tr>");

				for(Map.Entry<String, Review> mapping :productsListHashMap.entrySet())
				{
					Review value = mapping.getValue();
					s1 = value.getProductName();
					s2 = value.getEmailId();
					s3 = value.getReviewRating();
					DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
					s4 = value.getReviewDate();
					String reviewDate = format.format(s4);
					s5 = value.getReviewText();
					pw.println("<tr><th>"+i+"</th>");
					pw.println("<th>"+s1+"</th>");
					pw.println("<th>"+s2+"</th>");
					pw.println("<th>"+s3+"</th>");
					pw.println("<th>"+reviewDate+"</th>");
					pw.println("<th>"+s5+"</th></tr>");
					i++;
				}
				pw.println("</table>");
			}
		}
//===================================================================================
		if(s.equals("10"))
		{
			LinkedHashMap<String, LinkedHashMap<String, Review>> linkedHashMap = new LinkedHashMap<String, LinkedHashMap<String, Review>>();
			linkedHashMap = MongoDBDataStoreUtilities.printalistofreviewsgroupedbyzipcode();
			pw.println("<h3 align='center'>List of reviews grouped by zip-code</h3>");
			for(Map.Entry<String, LinkedHashMap<String, Review>> mapEntry :linkedHashMap.entrySet())
			{
				String mapEntryKey = mapEntry.getKey();
				LinkedHashMap<String, Review> hashMap = mapEntry.getValue();
				pw.println("<h3 align=\"center\">"+mapEntryKey+"</h3>");
				String s1;
				String s2;
				int s3;
				Date s4;
				String s5;
				int i=1;
				pw.println("<table border='2' bordercolor=\"#ff0000\">");
				pw.println("<tr><th>Sr No.</th><th>ProductModelName</th><th>Email</th><th>ReviewRating</th><th>ReviewDate</th><th>Reviewtext</th></tr>");
				for(Map.Entry<String, Review> reviewEntry :hashMap.entrySet())
				{
					Review value = reviewEntry.getValue();
					s1 = value.getProductName();
					s2 = value.getEmailId();
					s3 = value.getReviewRating();
					DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
					s4 = value.getReviewDate();
					String s6 = format.format(s4);
					s5 = value.getReviewText();
					pw.println("<tr><th>"+i+"</th>");
					pw.println("<th>"+s1+"</th>");
					pw.println("<th>"+s2+"</th>");
					pw.println("<th>"+s3+"</th>");
					pw.println("<th>"+s6+"</th>");
					pw.println("<th>"+s5+"</th></tr>");
					i++;
				}
				pw.println("</table>");
			}
		}
//==============================================================================================
		if(s.equals("11"))
		{
			LinkedHashMap<String, LinkedHashMap<String, Double>> linkedHashMap = new LinkedHashMap<String, LinkedHashMap<String, Double>>();
			linkedHashMap = MongoDBDataStoreUtilities.getthetotalnumberofproductsreviewedandgotRating5inEveryCity();
			pw.println("<h3 align='center'>List of total number of products reviewed and got Rating 5 in Every City</h3>");
			for(Map.Entry<String, LinkedHashMap<String, Double>> p :linkedHashMap.entrySet())
			{
				String s1 = p.getKey();
				LinkedHashMap<String, Double> map = p.getValue();
				pw.println("<h3 align=\"center\">"+s1+"</h3>");
				int i=1;
				pw.println("<table border='2' bordercolor=\"#ff0000\">");
				pw.println("<tr><th>Sr No.</th><th>ProductModelName</th><th>Rating</th></tr>");
				for(Map.Entry<String, Double> entry :map.entrySet())
				{
					String entryKey = entry.getKey();
					Double entryValue = entry.getValue();
					pw.println("<tr><th>"+i+"</th>");
					pw.println("<th>"+entryKey+"</th>");
					pw.println("<th>"+entryValue+"</th></tr>");
					i++;
				}
				pw.println("</table>");
			}
		}
//================================================================================================


		if(s.equals("12"))
		{
			LinkedHashMap<String, LinkedHashMap<String, Double>> linkedHashMap = new LinkedHashMap<String, LinkedHashMap<String, Double>>();
			linkedHashMap = MongoDBDataStoreUtilities.mostLikedProductInEveryCityBarchart();
			pw.println("<h3 align='center'>total number of submitted reviews of\n" +
					"every product for top 3 most liked products in every city</h3>");
			for(Map.Entry<String, LinkedHashMap<String, Double>> mapEntry :linkedHashMap.entrySet())
			{
				String key1 = mapEntry.getKey();
				LinkedHashMap<String, Double> hashMap = mapEntry.getValue();
				pw.println("<h3 align=\"center\">"+key1+"</h3>");
				int i=1;
				pw.println("<table border='2' bordercolor=\"#ff0000\">");
				pw.println("<div id=\"chart_div\"><tr><th>Sr No.</th><th>ProductModelName</th><th>Rating</th></tr>");
				for(Map.Entry<String, Double> entry :hashMap.entrySet())
				{
					String entryKey = entry.getKey();
					Double entryValue = entry.getValue();
					pw.println("<tr><th>"+i+"</th>");
					pw.println("<th>"+entryKey+"</th>");
					pw.println("<th>"+entryValue+"</th></tr>");
					i++;
				}
				pw.println("</div></table>");
			}
//====================================================================================================
		}
//=================================================================================

		if(s.equals("13"))
		{
			LinkedHashMap<String, LinkedHashMap<String, Double>> linkedHashMap = new LinkedHashMap<String, LinkedHashMap<String, Double>>();
			linkedHashMap = MongoDBDataStoreUtilities.medianProductPricesInEachCity();
			pw.println("<h3 align='center'>List of median product prices per city</h3>");
			for(Map.Entry<String, LinkedHashMap<String, Double>> mapEntry :linkedHashMap.entrySet())
			{
				String mapEntryKey = mapEntry.getKey();
				LinkedHashMap<String, Double> hashMap = mapEntry.getValue();
				pw.println("<h3 align=\"center\">"+mapEntryKey+"</h3>");
				int i=1;
				pw.println("<table border='2' bordercolor=\"#ff0000\">");
				pw.println("<tr><th>Sr No.</th><th>ProductModelName</th><th>Rating</th></tr>");
				for(Map.Entry<String, Double> entry :hashMap.entrySet())
				{
					String entryKey = entry.getKey();
					Double entryValue = entry.getValue();
					pw.println("<tr><th>"+i+"</th>");
					pw.println("<th>"+entryKey+"</th>");
					pw.println("<th>"+entryValue+"</th></tr>");
					i++;
				}
				pw.println("</table>");
			}
//======================================================================================
		if(s.equals("15"))
		{
			LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>> mapLinkedHashMap = new LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>>();
			mapLinkedHashMap = MongoDBDataStoreUtilities.mostDislikedProductinCityByRetailerName();
			pw.println("<h3 align='center'>List of top 5 most Disliked products sorted by\n" +
					"retailer name for every city</h3>");
			for(Map.Entry<String, LinkedHashMap<String, ArrayList<Object>>> mapEntry :mapLinkedHashMap.entrySet())
			{
				String mapEntryKey = mapEntry.getKey();
				LinkedHashMap<String, ArrayList<Object>> mapEntryValue = mapEntry.getValue();
				pw.println("<h3 align=\"center\">"+mapEntryKey+"</h3>");
				int i=1;
				pw.println("<table border='2' bordercolor=\"#ff0000\">");
				pw.println("<tr><th>Sr No.</th><th><b>RetailerName</b></th><th><b>ProductModelName</b></th><th><b>Rating</b></th></tr>");
				for(Map.Entry<String, ArrayList<Object>> mapping :mapEntryValue.entrySet())
				{
					ArrayList<Object> mappingValue = mapping.getValue();
					pw.println("<tr><th>"+i+"</th>");
					pw.println("<th>"+mappingValue.get(0)+"</th>");
					pw.println("<th>"+mappingValue.get(1)+"</th>");
					pw.println("<th>"+mappingValue.get(2)+"</th></tr>");
					i++;
				}
				pw.println("</table>");
			}
		}
//=========================================================================================

		if(s.equals("16"))
		{
			LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>> mapLinkedHashMap = new LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>>();
			mapLinkedHashMap = MongoDBDataStoreUtilities.mostDislikedProductInEveryZipCodeByRetailerName();
			pw.println("<h3 align='center'>List of top 5 most Disliked products sorted by\n" +
					"retailer name for every zip-code</h3>");
			for(Map.Entry<String, LinkedHashMap<String, ArrayList<Object>>> mapEntry :mapLinkedHashMap.entrySet())
			{
				String key1 = mapEntry.getKey();
				LinkedHashMap<String, ArrayList<Object>> listLinkedHashMap = mapEntry.getValue();
				pw.println("<h3 align=\"center\">Zip Code: "+key1+"</h3>");
				int i=1;
				pw.println("<table border='2' bordercolor=\"#ff0000\">");
				pw.println("<tr><th>Sr No.</th><th><b>RetailerName</b></th><th><b>ProductModelName</b></th><th><b>Rating</b></th></tr>");
				for(Map.Entry<String, ArrayList<Object>> mapping :listLinkedHashMap.entrySet())
				{
					ArrayList<Object> mappingValue = mapping.getValue();
					pw.println("<tr><th>"+i+"</th>");
					pw.println("<th>"+mappingValue.get(0)+"</th>");
					pw.println("<th>"+mappingValue.get(1)+"</th>");
					pw.println("<th>"+mappingValue.get(2)+"</th></tr>");
					i++;
				}
				pw.println("</table>");
			}
		}
//============================================================================================

		if(s.equals("18"))
		{


			LinkedHashMap<String, LinkedHashMap<String, Review>> hashMapLinkedHashMap = new LinkedHashMap<String, LinkedHashMap<String, Review>>();
			hashMapLinkedHashMap = MongoDBDataStoreUtilities.getReviewAgeGreaterThan50InEveryCity();
			pw.println("<h3 align='center'>List of reviews where reviewer age greater than 50\n" +
					"and the list is sorted by age in every city</h3>");
			for(Map.Entry<String, LinkedHashMap<String, Review>> mapEntry :hashMapLinkedHashMap.entrySet())
			{
				String mapEntryKey = mapEntry.getKey();
				LinkedHashMap<String, Review> productsListHashMap = mapEntry.getValue();
				pw.println("<h3 align=\"center\">"+mapEntryKey+"</h3>");
				String s1;
				String s2;
				int s3;
				Date s4;
				String s5;
				int s6;
				int i=1;
				pw.println("<table border='2' bordercolor=\"#ff0000\">");
				pw.println("<tr><th>Sr No.</th><th>User Age</th><th>ProductModelName</th><th>Email</th><th>ReviewRating</th><th>ReviewDate</th><th>Reviewtext</th></th>");
				for(Map.Entry<String, Review> m :productsListHashMap.entrySet())
				{
					Review value = m.getValue();
					s1 = value.getProductName();
					s2 = value.getEmailId();
					s3 = value.getReviewRating();
					s6 = value.getUserAge();
					DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
					s4 = value.getReviewDate();
					String reviewDate = format.format(s4);
					s5 = value.getReviewText();
					pw.println("<tr><th>"+i+"</th>");
					pw.println("<th>"+s6+"</th>");
					pw.println("<th>"+s1+"</th>");
					pw.println("<th>"+s2+"</th>");
					pw.println("<th>"+s3+"</th>");
					pw.println("<th>"+reviewDate+"</th>");
					pw.println("<th>"+s5+"</th></tr>");
					i++;
				}
				pw.println("</table>");
			}
		}
	}
}
}


