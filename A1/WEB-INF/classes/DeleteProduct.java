

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;

import org.w3c.dom.*;
import javax.xml.parsers.*;


public class DeleteProduct extends HttpServlet {

	ArrayList<Object> products;
	HashMap<String, Smartphone> phones;
	HashMap<String, Laptop> laptop;
	HashMap<String, Accessory> accessory;
	HashMap<String, Smartwatch> watches;
	HashMap<String, Speaker> speakers;
	HashMap<String,Headphone> headphone;
	HashMap<String,WarrantyServlet> warranty;

	ProductSaxParser productSaxParser = new ProductSaxParser();

	String productXmlFileLocation = "C:/apache-tomcat-7.0.34/webapps/A1/WEB-INF/classes/productCatalog.xml";

	void DataXMLFile()
	{
		try{
			products = productSaxParser.loadDataStore();

			phones = (HashMap<String,Smartphone>)products.get(0);
			laptop = (HashMap<String, Laptop>)products.get(1);
			accessory = (HashMap<String, Accessory>)products.get(2);
			watches = (HashMap<String, Smartwatch>)products.get(3);
			speakers= (HashMap<String, Speaker>)products.get(4);
			headphone = (HashMap<String, Headphone>)products.get(5);
			warranty = (HashMap<String, WarrantyServlet>)products.get(6);

		}catch(Exception E){
			System.out.println("Exception occured");
		}
	}


	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {

		DataXMLFile();

		PrintWriter pw = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");

		pw.println("<!doctype html>" +
				"<html><head>" +
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		pw.println("<title>Smart Portables</title>" +
				"<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		pw.println("<body><div id=\"container\">");
		pw.println("<header><h1>" +
				"<a href=\"/\">Smart<span>Portables</span></a></h1><h3>ADMIN AREA</h3></header>");
		pw.println("<nav><ul>");
		pw.println("<li class=\"\"><a href=\"AdministratorServlet\">Product List</a></li>");
		pw.println("<li><a href=\"StoreManagerPortal?type=addProduct\">Add Product</a></li>");
		pw.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");

		pw.println("<fieldset><div>");


		String nameOfProduct = request.getParameter("productName");
		String typeOFProduct = request.getParameter("type");

		MySqlDataStoreUtilities.deleteProduct(nameOfProduct);

		if(typeOFProduct.equals("smartwatch"))
		{
			try
			{
				DocumentBuilderFactory instance = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = instance.newDocumentBuilder();
				Document parse = builder.parse(productXmlFileLocation);

				NodeList tagName = parse.getElementsByTagName("smartwatch");

				for (int i = 0; i < tagName.getLength(); i++)
				{
					Element watch = (Element)tagName.item(i);
					Element watchName = (Element)watch.getElementsByTagName("smartWatchName").item(0);
					String content = watchName.getTextContent();
					if (content.equals(nameOfProduct))
					{
						watch.getParentNode().removeChild(watch);
					}
				}

				DOMSource domSource = new DOMSource(parse);

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				StreamResult streamResult = new StreamResult(productXmlFileLocation);
				transformer.transform(domSource, streamResult);
			}
			catch(Exception e)
			{
				pw.println("<p>Deleting product failed!<p>");
				e.printStackTrace();
			}

			pw.println("<h3>Product name: " +nameOfProduct+" Successfully deleted<h3>");
		}
		//=====================================================HeadPhones
		if(typeOFProduct.equals("headphone"))
		{
			try
			{
				DocumentBuilderFactory instance = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = instance.newDocumentBuilder();
				Document parse = builder.parse(productXmlFileLocation);
			
				NodeList tagName = parse.getElementsByTagName("headphone");

				for (int i = 0; i < tagName.getLength(); i++)
				{
					Element headphone = (Element)tagName.item(i);
					Element headPhoneName = (Element)headphone.getElementsByTagName("headPhoneName").item(0);
					String content = headPhoneName.getTextContent();
					if (content.equals(nameOfProduct))
					{
						headphone.getParentNode().removeChild(headphone);
					}
				}

				DOMSource domSource = new DOMSource(parse);

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				StreamResult streamResult = new StreamResult(productXmlFileLocation);
				transformer.transform(domSource, streamResult);
			}
			catch(Exception e)
			{
				pw.println("<p>Deleting product failed<p>");
				e.printStackTrace();
			}

			pw.println("<h3>Product name: " +nameOfProduct+" Successfully deleted<h3>");
		}
//===================================================Warranty===================================================
		if(typeOFProduct.equals("warranty"))
		{
			try
			{
				DocumentBuilderFactory instance = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = instance.newDocumentBuilder();
				Document parse = builder.parse(productXmlFileLocation);
				Element element = parse.getDocumentElement();

				NodeList tagName = parse.getElementsByTagName("warranty");

				for (int i = 0; i < tagName.getLength(); i++)
				{
					Element warranty = (Element)tagName.item(i);
					Element warrantyName = (Element)warranty.getElementsByTagName("warrantyName").item(0);
					String content = warrantyName.getTextContent();
					if (content.equals(nameOfProduct))
					{
						warranty.getParentNode().removeChild(warranty);
					}
				}

				DOMSource domSource = new DOMSource(parse);

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				StreamResult streamResult = new StreamResult(productXmlFileLocation);
				transformer.transform(domSource, streamResult);
			}
			catch(Exception e)
			{
				pw.println("<p>Deleting product failed<p>");
				e.printStackTrace();
			}

			pw.println("<h3>Product name: " +nameOfProduct+" Successfully deleted<h3>");
		}
//==============================================================================================
		if(typeOFProduct.equals("speaker"))
		{
			try
			{
				DocumentBuilderFactory instance = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = instance.newDocumentBuilder();
				Document parse = builder.parse(productXmlFileLocation);
				Element element = parse.getDocumentElement();

				NodeList tagName = parse.getElementsByTagName("speaker");

				for (int i = 0; i < tagName.getLength(); i++)
				{
					Element speaker = (Element)tagName.item(i);
					Element speakerName = (Element)speaker.getElementsByTagName("speakerName").item(0);
					String content = speakerName.getTextContent();
					if (content.equals(nameOfProduct))
					{
						speaker.getParentNode().removeChild(speaker);
					}
				}

				DOMSource domSource = new DOMSource(parse);

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				StreamResult streamResult = new StreamResult(productXmlFileLocation);
				transformer.transform(domSource, streamResult);
			}
			catch(Exception e)
			{
				pw.println("<p>Deleting product failed<p>");
				e.printStackTrace();
			}

			pw.println("<h3>Product name: " +nameOfProduct+" Successfully deleted<h3>");
		}

		if(typeOFProduct.equals("smartphone"))
		{
			try
			{
				DocumentBuilderFactory instance = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = instance.newDocumentBuilder();
				Document parse = builder.parse(productXmlFileLocation);
				Element element = parse.getDocumentElement();

				NodeList tagName = parse.getElementsByTagName("smartphone");

				for (int i = 0; i < tagName.getLength(); i++)
				{
					Element smartphone = (Element)tagName.item(i);
					Element smartPhoneName = (Element)smartphone.getElementsByTagName("smartPhoneName").item(0);
					String content = smartPhoneName.getTextContent();
					if (content.equals(nameOfProduct))
					{
						smartphone.getParentNode().removeChild(smartphone);
					}
				}

				DOMSource domSource = new DOMSource(parse);

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				StreamResult streamResult = new StreamResult(productXmlFileLocation);
				transformer.transform(domSource, streamResult);
			}
			catch(Exception e)
			{
				pw.println("<p>Deleting product failed<p>");
				e.printStackTrace();
			}

			pw.println("<h3>Product name: " +nameOfProduct+" Successfully deleted<h3>");
		}


		if(typeOFProduct.equals("laptop"))
		{
			try
			{
				DocumentBuilderFactory instance = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = instance.newDocumentBuilder();
				Document parse = builder.parse(productXmlFileLocation);
				Element element = parse.getDocumentElement();

				NodeList tagName = parse.getElementsByTagName("laptop");

				for (int i = 0; i < tagName.getLength(); i++)
				{
					Element laptop = (Element)tagName.item(i);
					Element laptopName = (Element)laptop.getElementsByTagName("laptopName").item(0);
					String content = laptopName.getTextContent();
					if (content.equals(nameOfProduct))
					{
						laptop.getParentNode().removeChild(laptop);
					}
				}

				DOMSource source = new DOMSource(parse);

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				StreamResult streamResult = new StreamResult(productXmlFileLocation);
				transformer.transform(source, streamResult);
			}
			catch(Exception e)
			{
				pw.println("<p>Deleting product failed<p>");
				e.printStackTrace();
			}

			pw.println("<h3>Product name: " +nameOfProduct+" Successfully deleted<h3>");
		}


		if(typeOFProduct.equals("accessory"))
		{
			try
			{
				DocumentBuilderFactory instance = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = instance.newDocumentBuilder();
				Document parse = builder.parse(productXmlFileLocation);
				Element element = parse.getDocumentElement();

				NodeList tagName = parse.getElementsByTagName("accessory");

				for (int i = 0; i < tagName.getLength(); i++)
				{
					Element accessory = (Element)tagName.item(i);
					Element accessoryName = (Element)accessory.getElementsByTagName("accessoryName").item(0);
					String content = accessoryName.getTextContent();
					if (content.equals(nameOfProduct))
					{
						accessory.getParentNode().removeChild(accessory);
					}
				}

				DOMSource domSource = new DOMSource(parse);

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				StreamResult streamResult = new StreamResult(productXmlFileLocation);
				transformer.transform(domSource, streamResult);
			}
			catch(Exception e)
			{
				pw.println("<p>Deleting product failed<p>");
				e.printStackTrace();
			}

			pw.println("<h3>Product name: " +nameOfProduct+" Successfully Deleted<h3>");
		}


		pw.println("</div></div></div>" +
				"</body>" +
				"</html>");

		pw.close();

	}

	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {

		doPost(request, response);
	}

}
