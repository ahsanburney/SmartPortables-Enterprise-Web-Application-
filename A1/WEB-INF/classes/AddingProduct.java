

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;


import org.w3c.dom.*;
import javax.xml.parsers.*;


public class AddingProduct extends HttpServlet {

	ArrayList<Object> products;
	HashMap<String, Smartphone> phones;
	HashMap<String, Laptop> lappy;
	HashMap<String, Accessory> accessory;
	HashMap<String, Smartwatch> watches;
	HashMap<String, Speaker> speakers;
	HashMap<String,Headphone> headphone;
	HashMap<String,WarrantyServlet> warranty;

	ProductSaxParser saxParser = new ProductSaxParser();

	String productXmlFileLocation = "C:/apache-tomcat-7.0.34/webapps/A1/WEB-INF/classes/ProductCatalog.xml";

	void dataXMLFile()
	{
		try{
			products = saxParser.loadDataStore();
			phones = (HashMap<String,Smartphone>)products.get(0);
			lappy = (HashMap<String, Laptop>)products.get(1);
			accessory = (HashMap<String, Accessory>)products.get(2);
			watches = (HashMap<String, Smartwatch>)products.get(3);
			speakers= (HashMap<String, Speaker>)products.get(4);
			headphone = (HashMap<String, Headphone>)products.get(5);
			warranty = (HashMap<String, WarrantyServlet>)products.get(6);

		}catch(Exception E){
			System.out.println("Exception Occured");
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
		pw.println("<li><a href=\"AddProduct?type=addProduct\">Add Product</a></li>");
		pw.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");

		pw.println("<fieldset><div>");

		String id = request.getParameter("productId");
		String category = request.getParameter("productType");
		String retailerName = request.getParameter("retailer");
		String path = request.getParameter("imagePath");
		String productName = request.getParameter("productName");
		String companyName = request.getParameter("company");
		String productCondition = request.getParameter("condition");
		String productPrice = request.getParameter("price");
		String manfRebate = request.getParameter("manfRebate");
		String retDiscount = request.getParameter("retdiscount");
		String productColor = request.getParameter("color");

		if(retailerName!=null && !retailerName.equals("") && path!=null && !path.equals("") && productName!=null && !productName.equals("")
				&& companyName!=null && !companyName.equals("") && productCondition!=null && !productCondition.equals("") && productPrice!=null && !productPrice.equals("")
				&& productColor!=null && !productColor.equals("")&& manfRebate!=null && !manfRebate.equals("") && retDiscount!=null && !retDiscount.equals(""))
		{
			MySqlDataStoreUtilities.insertProduct(id, category, retailerName, path, productName, companyName, productCondition, productPrice, productColor,manfRebate);


			if(category.equals("Smartphone"))
			{
				try{
					DocumentBuilderFactory instance = DocumentBuilderFactory.newInstance();
					DocumentBuilder newDocumentBuilder = instance.newDocumentBuilder();
					Document parse = newDocumentBuilder.parse(productXmlFileLocation);
					Element element = parse.getDocumentElement();

					Element smartphone = parse.createElement("smartphone");
					smartphone.setAttribute("id", id);
					smartphone.setAttribute("retailer", retailerName);

					Element smartPhoneImage = parse.createElement("smartPhoneImage");
					smartPhoneImage.appendChild(parse.createTextNode(path));
					smartphone.appendChild(smartPhoneImage);

					Element smartPhoneName = parse.createElement("smartPhoneName");
					smartPhoneName.appendChild(parse.createTextNode(productName));
					smartphone.appendChild(smartPhoneName);

					Element phoneCompany = parse.createElement("smartPhoneCompany");
					phoneCompany.appendChild(parse.createTextNode(companyName));
					smartphone.appendChild(phoneCompany);

					Element smartPhoneCondition = parse.createElement("smartPhoneCondition");
					smartPhoneCondition.appendChild(parse.createTextNode(productCondition));
					smartphone.appendChild(smartPhoneCondition);

					Element smartPhonePrice = parse.createElement("smartPhonePrice");
					smartPhonePrice.appendChild(parse.createTextNode(productPrice));
					smartphone.appendChild(smartPhonePrice);

					Element smartPhoneColor = parse.createElement("smartPhoneColor");
					smartPhoneColor.appendChild(parse.createTextNode(productColor));
					smartphone.appendChild(smartPhoneColor);

					Element smartPhoneManfRebate = parse.createElement("smartPhoneManfRebate");
					smartPhoneManfRebate.appendChild(parse.createTextNode(manfRebate));
					smartphone.appendChild(smartPhoneManfRebate);

					Element smartPhoneRetDiscount = parse.createElement("smartPhoneRetDiscount");
					smartPhoneRetDiscount.appendChild(parse.createTextNode(retDiscount));
					smartphone.appendChild(smartPhoneRetDiscount);

					element.appendChild(smartphone);

					DOMSource source = new DOMSource(parse);

					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					StreamResult result = new StreamResult(productXmlFileLocation);
					transformer.transform(source, result);
				}
				catch(Exception e)
				{
					pw.println("<p>Adding Product Failed<p>");
					e.printStackTrace();
				}

				pw.println("<h3>Smartphone id: " +id+" added<h3>");
			}

			//===================Headphones====================//

			if(category.equals("Headphone"))
			{
				try{
					DocumentBuilderFactory instance = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = instance.newDocumentBuilder();
					Document parse = builder.parse(productXmlFileLocation);
					Element element = parse.getDocumentElement();

					Element headphone = parse.createElement("headphone");
					headphone.setAttribute("id", id);
					headphone.setAttribute("retailer", retailerName);

					Element headPhoneImage = parse.createElement("headPhoneImage");
					headPhoneImage.appendChild(parse.createTextNode(path));
					headphone.appendChild(headPhoneImage);

					Element headPhoneName = parse.createElement("headPhoneName");
					headPhoneName.appendChild(parse.createTextNode(productName));
					headphone.appendChild(headPhoneName);

					Element headPhoneCompany = parse.createElement("headPhoneCompany");
					headPhoneCompany.appendChild(parse.createTextNode(companyName));
					headphone.appendChild(headPhoneCompany);

					Element headPhoneCondition = parse.createElement("headPhoneCondition");
					headPhoneCondition.appendChild(parse.createTextNode(productCondition));
					headphone.appendChild(headPhoneCondition);

					Element headPhonePrice = parse.createElement("headPhonePrice");
					headPhonePrice.appendChild(parse.createTextNode(productPrice));
					headphone.appendChild(headPhonePrice);

					Element headPhoneColor = parse.createElement("headPhoneColor");
					headPhoneColor.appendChild(parse.createTextNode(productColor));
					headphone.appendChild(headPhoneColor);

					Element headPhoneManfRebate = parse.createElement("headPhoneManfRebate");
					headPhoneManfRebate.appendChild(parse.createTextNode(manfRebate));
					headphone.appendChild(headPhoneManfRebate);

					Element headPhoneRetDiscount = parse.createElement("headPhoneRetDiscount");
					headPhoneRetDiscount.appendChild(parse.createTextNode(retDiscount));
					headphone.appendChild(headPhoneRetDiscount);

					element.appendChild(headphone);

					DOMSource domSource = new DOMSource(parse);

					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					StreamResult result = new StreamResult(productXmlFileLocation);
					transformer.transform(domSource, result);
				}
				catch(Exception e)
				{
					pw.println("<p>Adding product failed<p>");
					e.printStackTrace();
				}

				pw.println("<h3>Headphone id: " +id+" added<h3>");
			}

			//=====================Warranty=====================//
			if(category.equals("Warranty"))
			{
				try{
					DocumentBuilderFactory instance = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = instance.newDocumentBuilder();
					Document parse = builder.parse(productXmlFileLocation);
					Element element = parse.getDocumentElement();

					Element warranty = parse.createElement("warranty");
					warranty.setAttribute("id", id);
					warranty.setAttribute("retailer", retailerName);

					Element warrantyImage = parse.createElement("warrantyImage");
					warrantyImage.appendChild(parse.createTextNode(path));
					warranty.appendChild(warrantyImage);

					Element warrantyName = parse.createElement("warrantyName");
					warrantyName.appendChild(parse.createTextNode(productName));
					warranty.appendChild(warrantyName);

					Element warrantyCompany = parse.createElement("warrantyCompany");
					warrantyCompany.appendChild(parse.createTextNode(companyName));
					warranty.appendChild(warrantyCompany);

					Element warrantyCondition = parse.createElement("warrantyCondition");
					warrantyCondition.appendChild(parse.createTextNode(productCondition));
					warranty.appendChild(warrantyCondition);

					Element warrantyPrice = parse.createElement("warrantyPrice");
					warrantyPrice.appendChild(parse.createTextNode(productPrice));
					warranty.appendChild(warrantyPrice);

					Element warrantyColor = parse.createElement("warrantyColor");
					warrantyColor.appendChild(parse.createTextNode(productColor));
					warranty.appendChild(warrantyColor);

					Element warrantyManfRebate = parse.createElement("warrantyManfRebate");
					warrantyManfRebate.appendChild(parse.createTextNode(manfRebate));
					warranty.appendChild(warrantyManfRebate);

					Element warrantyRetDiscount = parse.createElement("warrantyRetDiscount");
					warrantyRetDiscount.appendChild(parse.createTextNode(retDiscount));
					warranty.appendChild(warrantyRetDiscount);

					element.appendChild(warranty);

					DOMSource domSource = new DOMSource(parse);

					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					StreamResult result = new StreamResult(productXmlFileLocation);
					transformer.transform(domSource, result);
				}
				catch(Exception e)
				{
					pw.println("<p>Adding product failed<p>");
					e.printStackTrace();
				}

				pw.println("<h3>Warranty id: " +id+" added <h3>");
			}


			//=================Speakers=======================//

			if(category.equals("Speaker"))
			{
				try{
					DocumentBuilderFactory instance = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = instance.newDocumentBuilder();
					Document parse = builder.parse(productXmlFileLocation);
					Element element = parse.getDocumentElement();

					Element speaker = parse.createElement("speaker");
					speaker.setAttribute("id", id);
					speaker.setAttribute("retailer", retailerName);

					Element speakerImage = parse.createElement("speakerImage");
					speakerImage.appendChild(parse.createTextNode(path));
					speaker.appendChild(speakerImage);

					Element speakerName = parse.createElement("speakerName");
					speakerName.appendChild(parse.createTextNode(productName));
					speaker.appendChild(speakerName);

					Element speakerCompany = parse.createElement("speakerCompany");
					speakerCompany.appendChild(parse.createTextNode(companyName));
					speaker.appendChild(speakerCompany);

					Element speakerCondition = parse.createElement("speakerCondition");
					speakerCondition.appendChild(parse.createTextNode(productCondition));
					speaker.appendChild(speakerCondition);

					Element speakerPrice = parse.createElement("speakerPrice");
					speakerPrice.appendChild(parse.createTextNode(productPrice));
					speaker.appendChild(speakerPrice);

					Element speakerColor = parse.createElement("speakerColor");
					speakerColor.appendChild(parse.createTextNode(productColor));
					speaker.appendChild(speakerColor);

					Element speakerManfRebate = parse.createElement("speakerManfRebate");
					speakerManfRebate.appendChild(parse.createTextNode(manfRebate));
					speaker.appendChild(speakerManfRebate);

					Element speakerRetDiscount = parse.createElement("speakerRetDiscount");
					speakerRetDiscount.appendChild(parse.createTextNode(retDiscount));
					speaker.appendChild(speakerRetDiscount);

					element.appendChild(speaker);

					DOMSource domSource = new DOMSource(parse);

					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					StreamResult streamResult = new StreamResult(productXmlFileLocation);
					transformer.transform(domSource, streamResult);
				}
				catch(Exception e)
				{
					pw.println("<p>Adding product failed<p>");
					e.printStackTrace();
				}

				pw.println("<h3>Speaker id: " +id+" added<h3>");
			}

			//============watches

			if(category.equals("Smartwatch"))
			{
				try{
					DocumentBuilderFactory instance = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = instance.newDocumentBuilder();
					Document parse = builder.parse(productXmlFileLocation);
					Element element = parse.getDocumentElement();

					Element smartwatch = parse.createElement("smartwatch");
					smartwatch.setAttribute("id", id);
					smartwatch.setAttribute("retailer", retailerName);

					Element smartWatchImage = parse.createElement("smartWatchImage");
					smartWatchImage.appendChild(parse.createTextNode(path));
					smartwatch.appendChild(smartWatchImage);

					Element smartWatchName = parse.createElement("smartWatchName");
					smartWatchName.appendChild(parse.createTextNode(productName));
					smartwatch.appendChild(smartWatchName);

					Element smartWatchCompany = parse.createElement("smartWatchCompany");
					smartWatchCompany.appendChild(parse.createTextNode(companyName));
					smartwatch.appendChild(smartWatchCompany);

					Element smartWatchCondition = parse.createElement("smartWatchCondition");
					smartWatchCondition.appendChild(parse.createTextNode(productCondition));
					smartwatch.appendChild(smartWatchCondition);

					Element smartWatchPrice = parse.createElement("smartWatchPrice");
					smartWatchPrice.appendChild(parse.createTextNode(productPrice));
					smartwatch.appendChild(smartWatchPrice);

					Element smartWatchColor = parse.createElement("smartWatchColor");
					smartWatchColor.appendChild(parse.createTextNode(productColor));
					smartwatch.appendChild(smartWatchColor);

					Element smartWatchManfRebate = parse.createElement("smartWatchManfRebate");
					smartWatchManfRebate.appendChild(parse.createTextNode(manfRebate));
					smartwatch.appendChild(smartWatchManfRebate);

					Element smartWatchRetDiscount = parse.createElement("smartWatchRetDiscount");
					smartWatchRetDiscount.appendChild(parse.createTextNode(retDiscount));
					smartwatch.appendChild(smartWatchRetDiscount);

					element.appendChild(smartwatch);

					DOMSource domSource = new DOMSource(parse);

					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					StreamResult result = new StreamResult(productXmlFileLocation);
					transformer.transform(domSource, result);
				}
				catch(Exception e)
				{
					pw.println("<p>Adding product failed<p>");
					e.printStackTrace();
				}

				pw.println("<h3>Smartwatch id: " +id+" added<h3>");
			}



			if(category.equals("Laptop"))
			{
				try{
					DocumentBuilderFactory instance = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = instance.newDocumentBuilder();
					Document parse = builder.parse(productXmlFileLocation);
					Element element = parse.getDocumentElement();

					Element laptop = parse.createElement("laptop");
					laptop.setAttribute("id", id);
					laptop.setAttribute("retailer", retailerName);

					Element laptopImage = parse.createElement("laptopImage");
					laptopImage.appendChild(parse.createTextNode(path));
					laptop.appendChild(laptopImage);

					Element laptopName = parse.createElement("laptopName");
					laptopName.appendChild(parse.createTextNode(productName));
					laptop.appendChild(laptopName);

					Element smartPhoneCompany = parse.createElement("laptopCompany");
					smartPhoneCompany.appendChild(parse.createTextNode(companyName));
					laptop.appendChild(smartPhoneCompany);

					Element smartPhoneCondition = parse.createElement("laptopCondition");
					smartPhoneCondition.appendChild(parse.createTextNode(productCondition));
					laptop.appendChild(smartPhoneCondition);

					Element smartPhonePrice = parse.createElement("laptopPrice");
					smartPhonePrice.appendChild(parse.createTextNode(productPrice));
					laptop.appendChild(smartPhonePrice);

					Element smartPhoneColor = parse.createElement("laptopColor");
					smartPhoneColor.appendChild(parse.createTextNode(productColor));
					laptop.appendChild(smartPhoneColor);

					Element laptopManfRebate = parse.createElement("laptopManfRebate");
					laptopManfRebate.appendChild(parse.createTextNode(manfRebate));
					laptop.appendChild(laptopManfRebate);

					Element laptopretDiscount = parse.createElement("laptopretDiscount");
					laptopretDiscount.appendChild(parse.createTextNode(retDiscount));
					laptop.appendChild(laptopretDiscount);

					element.appendChild(laptop);

					DOMSource domSource = new DOMSource(parse);

					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					StreamResult streamResult = new StreamResult(productXmlFileLocation);
					transformer.transform(domSource, streamResult);
				}
				catch(Exception e)
				{
					pw.println("<p>Adding product failed<p>");
					e.printStackTrace();
				}

				pw.println("<h3>Laptop id: " +id+" added<h3>");
			}

			if(category.equals("Accessory"))
			{
				try{
					DocumentBuilderFactory instance = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = instance.newDocumentBuilder();
					Document parse = builder.parse(productXmlFileLocation);
					Element element = parse.getDocumentElement();

					Element accessory = parse.createElement("accessory");
					accessory.setAttribute("id", id);
					accessory.setAttribute("retailer", retailerName);

					Element accessoryImage = parse.createElement("accessoryImage");
					accessoryImage.appendChild(parse.createTextNode(path));
					accessory.appendChild(accessoryImage);

					Element accessoryName = parse.createElement("accessoryName");
					accessoryName.appendChild(parse.createTextNode(productName));
					accessory.appendChild(accessoryName);

					Element accessoryCompany = parse.createElement("accessoryCompany");
					accessoryCompany.appendChild(parse.createTextNode(companyName));
					accessory.appendChild(accessoryCompany);

					Element accessoryCondition = parse.createElement("accessoryCondition");
					accessoryCondition.appendChild(parse.createTextNode(productCondition));
					accessory.appendChild(accessoryCondition);

					Element accessoryPrice = parse.createElement("accessoryPrice");
					accessoryPrice.appendChild(parse.createTextNode(productPrice));
					accessory.appendChild(accessoryPrice);

					Element accessoryColor = parse.createElement("accessoryColor");
					accessoryColor.appendChild(parse.createTextNode(productColor));
					accessory.appendChild(accessoryColor);

					Element accessoryManfRebate = parse.createElement("accessoryManfRebate");
					accessoryManfRebate.appendChild(parse.createTextNode(manfRebate));
					accessory.appendChild(accessoryManfRebate);

					Element accessoryRetDiscount = parse.createElement("accessoryRetDiscount");
					accessoryRetDiscount.appendChild(parse.createTextNode(retDiscount));
					accessory.appendChild(accessoryRetDiscount);

					element.appendChild(accessory);

					DOMSource domSource = new DOMSource(parse);

					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					StreamResult streamResult = new StreamResult(productXmlFileLocation);
					transformer.transform(domSource, streamResult);
				}
				catch(Exception e)
				{
					pw.println("<p>Adding product failed<p>");
					e.printStackTrace();
				}

				pw.println("<h3>Accessory id: " +id+" added<h3>");
			}


		}

		else
		{
			pw.println("<p>Form filling error");
		}


		pw.println("</div></fieldset>" +
				"</article>" +
				"</div></div>" +
				"</body></html>");

		pw.close();

	}

	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {

		doPost(request, response);
	}

}


