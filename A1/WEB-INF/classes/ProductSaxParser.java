

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;
import java.io.*;



public class ProductSaxParser extends DefaultHandler {

	Smartphone smartphone;
	Laptop laptop;
	Accessory accessory;
	Smartwatch smartwatch;
	Speaker speaker;
	WarrantyServlet warranty;
	Headphone headphone;

	HashMap<String,Smartphone> smartphones;
	HashMap<String,Laptop> laptops;
	HashMap<String,Accessory> accessories;
	HashMap<String,Smartwatch> smartwatches;
	HashMap<String,Speaker> speakers;
	HashMap<String,WarrantyServlet> warranties;
	HashMap<String,Headphone> headphones;

	ArrayList<Object> products;

	String productXmlFileName = "C:/apache-tomcat-7.0.34/webapps/A1/WEB-INF/classes/ProductCatalog.xml";


	String elementValueRead;

	int x=1;
	int y=1;
	int z=1;
	int f=1;
	int a=1;
	int w=1;
	int h=1;

	public ArrayList<Object> loadDataStore() {


		smartphones = new HashMap<String,Smartphone>();
		laptops = new HashMap<String,Laptop>();
		accessories = new HashMap<String,Accessory>();
		smartwatches = new HashMap<String,Smartwatch>();
		speakers = new HashMap<String,Speaker>();
		warranties=new HashMap<String,WarrantyServlet>();
		headphones = new HashMap<String,Headphone>();

		products = new ArrayList<Object>();

		parseDocument(productXmlFileName);


		products.add(smartphones);
		products.add(laptops);
		products.add(accessories);
		products.add(smartwatches);
		products.add(speakers);
		products.add(headphones);
		products.add(warranties);

		return products;
	}

	private void parseDocument(String xmlFileName) {

		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			File file = new File(xmlFileName);
			parser.parse(file, this);
		} catch (ParserConfigurationException e) {
			System.out.println("error occured");
		} catch (SAXException e) {
			System.out.println("some error");
		} catch (IOException e) {
			System.out.println(" error occured 3");
		}
	}


	

	
////////////////////////////////////////////////////////////

	/*************

	 There are a number of methods to override in SAX handler  when parsing your XML document :

	 Group 1. startDocument() and endDocument() :  Methods that are called at the start and end of an XML document.
	 Group 2. startElement() and endElement() :  Methods that are called  at the start and end of a document element.
	 Group 3. characters() : Method that is called with the text content in between the start and end tags of an XML document element.

	 There are few other methods that you could use for notification for different purposes, check the API at the following URL:

	 https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html

	 ***************/

////////////////////////////////////////////////////////////



	@Override
	public void startElement(String string1, String string2, String categoryName, Attributes att) throws SAXException {

		if (categoryName.equalsIgnoreCase("smartphone")) {
			smartphone = new Smartphone();
			smartphone.setId(att.getValue("id"));
			smartphone.setRetailer(att.getValue("retailer"));
		}

		if (categoryName.equalsIgnoreCase("smartwatch")) {
			smartwatch = new Smartwatch();
			smartwatch.setId(att.getValue("id"));
			smartwatch.setRetailer(att.getValue("retailer"));
		}

		if (categoryName.equalsIgnoreCase("speaker")) {
			speaker = new Speaker();
			speaker.setId(att.getValue("id"));
			speaker.setRetailer(att.getValue("retailer"));
			
		}




		if (categoryName.equalsIgnoreCase("laptop")) {
			laptop = new Laptop();
			laptop.setId(att.getValue("id"));
			laptop.setRetailer(att.getValue("retailer"));
		}



		if (categoryName.equalsIgnoreCase("accessory")) {
			accessory = new Accessory();
			accessory.setId(att.getValue("id"));
			accessory.setRetailer(att.getValue("retailer"));
		}

		if (categoryName.equalsIgnoreCase("headphone")) {
			headphone = new Headphone();
			headphone.setId(att.getValue("id"));
			headphone.setRetailer(att.getValue("retailer"));
		}
		if (categoryName.equalsIgnoreCase("warranty")) {
			warranty = new WarrantyServlet();
			warranty.setId(att.getValue("id"));
			warranty.setRetailer(att.getValue("retailer"));
		}

	}

	@Override
	public void endElement(String string1, String string2, String categoryName) throws SAXException {
		

		if (categoryName.equals("smartphone")) {
			smartphones.put("S"+x, smartphone);
			x++;

			return;
		}
		if (categoryName.equalsIgnoreCase("smartPhoneImage")) {
			smartphone.setImage(elementValueRead);

			return;
		}
		if (categoryName.equalsIgnoreCase("smartPhoneName")) {
			smartphone.setName(elementValueRead);

			return;
		}
		if (categoryName.equalsIgnoreCase("smartPhoneCompany")) {
			smartphone.setCompany(elementValueRead);

			return;
		}
		if (categoryName.equalsIgnoreCase("smartPhoneCondition")) {
			smartphone.setCondition(elementValueRead);

			return;
		}
		if(categoryName.equalsIgnoreCase("smartPhonePrice")){
			smartphone.setPrice(Float.parseFloat(elementValueRead));

			return;
		}
		if(categoryName.equalsIgnoreCase("smartPhoneColor")){
			smartphone.setColor(elementValueRead);

			return;
		}
		if(categoryName.equalsIgnoreCase("smartPhoneDescription")){
			smartphone.setDescription(elementValueRead);
			return;
		}
		if(categoryName.equalsIgnoreCase("smartPhoneManfRebate")){
			smartphone.setMafRebate(Float.parseFloat(elementValueRead));

			return;
		}
		if(categoryName.equalsIgnoreCase("smartPhoneRetDiscount")){
			smartphone.setReDiscount(Float.parseFloat(elementValueRead));

			return;
		}

		//=========================================================HeadPhones==============================

		if (categoryName.equals("headphone")) {
			headphones.put("S"+h, headphone);
			h++;

			return;
		}
		if (categoryName.equalsIgnoreCase("headPhoneImage")) {
			headphone.setImage(elementValueRead);

			return;
		}
		if (categoryName.equalsIgnoreCase("headPhoneName")) {
			headphone.setName(elementValueRead);

			return;
		}
		if (categoryName.equalsIgnoreCase("headPhoneCompany")) {
			headphone.setCompany(elementValueRead);

			return;
		}
		if (categoryName.equalsIgnoreCase("headPhoneCondition")) {
			headphone.setCondition(elementValueRead);

			return;
		}
		if(categoryName.equalsIgnoreCase("headPhonePrice")){
			headphone.setPrice(Float.parseFloat(elementValueRead));

			return;
		}
		if(categoryName.equalsIgnoreCase("headPhoneColor")){
			headphone.setColor(elementValueRead);

			return;
		}
		if(categoryName.equalsIgnoreCase("headPhoneDescription")){
			headphone.setDescription(elementValueRead);
			return;
		}
		if(categoryName.equalsIgnoreCase("headPhoneManfRebate")){
			headphone.setMafRebate(Float.parseFloat(elementValueRead));

			return;
		}
		if(categoryName.equalsIgnoreCase("headPhoneRetDiscount")){
			headphone.setReDiscount(Float.parseFloat(elementValueRead));

			return;
		}

//=============================================Warraty===========================================================
		if (categoryName.equals("warranty")) {
			warranties.put("S"+w, warranty);
			w++;

			return;
		}
		if (categoryName.equalsIgnoreCase("warrantyImage")) {
			warranty.setImage(elementValueRead);

			return;
		}
		if (categoryName.equalsIgnoreCase("warrantyName")) {
			warranty.setName(elementValueRead);

			return;
		}
		if (categoryName.equalsIgnoreCase("warrantyCompany")) {
			warranty.setCompany(elementValueRead);

			return;
		}
		if (categoryName.equalsIgnoreCase("warrantyCondition")) {
			warranty.setCondition(elementValueRead);

			return;
		}
		if(categoryName.equalsIgnoreCase("warrantyPrice")){
			warranty.setPrice(Float.parseFloat(elementValueRead));

			return;
		}
		if(categoryName.equalsIgnoreCase("warrantyColor")){
			warranty.setColor(elementValueRead);

			return;
		}
		if(categoryName.equalsIgnoreCase("warrantyDescription")){
			warranty.setDescription(elementValueRead);
			return;
		}
		if(categoryName.equalsIgnoreCase("warrantyManfRebate")){
			warranty.setMafRebate(Float.parseFloat(elementValueRead));

			return;
		}
		if(categoryName.equalsIgnoreCase("warrantyRetDiscount")){
			warranty.setReDiscount(Float.parseFloat(elementValueRead));

			return;
		}



//============================================================================================================
		if (categoryName.equals("speaker")) {
			speakers.put("S"+f, speaker);
			f++;

			return;
		}
		if (categoryName.equalsIgnoreCase("speakerImage")) {
			speaker.setImage(elementValueRead);

			return;
		}
		if (categoryName.equalsIgnoreCase("speakerName")) {
			speaker.setName(elementValueRead);

			return;
		}
		if (categoryName.equalsIgnoreCase("speakerCompany")) {
			speaker.setCompany(elementValueRead);

			return;
		}
		if (categoryName.equalsIgnoreCase("speakerCondition")) {
			speaker.setCondition(elementValueRead);

			return;
		}
		if(categoryName.equalsIgnoreCase("speakerPrice")){
			speaker.setPrice(Float.parseFloat(elementValueRead));

			return;
		}
		if(categoryName.equalsIgnoreCase("speakerColor")){
			speaker.setColor(elementValueRead);

			return;
		}
		if(categoryName.equalsIgnoreCase("speakerDescription")){
			speaker.setDescription(elementValueRead);
			return;
		}
		if(categoryName.equalsIgnoreCase("speakerManfRebate")){
			speaker.setMafRebate(Float.parseFloat(elementValueRead));

			return;
		}
		if(categoryName.equalsIgnoreCase("speakerRetDiscount")){
			speaker.setReDiscount(Float.parseFloat(elementValueRead));

			return;
		}


//===========================================================================================
		if (categoryName.equals("smartwatch")) {
			smartwatches.put("S"+z, smartwatch);
			z++;

			return;
		}
		if (categoryName.equalsIgnoreCase("smartWatchImage")) {
			smartwatch.setImage(elementValueRead);

			return;
		}
		if (categoryName.equalsIgnoreCase("smartWatchName")) {
			smartwatch.setName(elementValueRead);

			return;
		}
		if (categoryName.equalsIgnoreCase("smartWatchCompany")) {
			smartwatch.setCompany(elementValueRead);

			return;
		}
		if (categoryName.equalsIgnoreCase("smartWatchCondition")) {
			smartwatch.setCondition(elementValueRead);

			return;
		}
		if(categoryName.equalsIgnoreCase("smartWatchPrice")){
			smartwatch.setPrice(Float.parseFloat(elementValueRead));

			return;
		}
		if(categoryName.equalsIgnoreCase("smartWatchColor")){
			smartwatch.setColor(elementValueRead);

			return;
		}
		if(categoryName.equalsIgnoreCase("smartWatchDescription")){
			smartwatch.setDescription(elementValueRead);
			return;
		}
		if(categoryName.equalsIgnoreCase("smartWatchManfRebate")){
			smartwatch.setMafRebate(Float.parseFloat(elementValueRead));

			return;
		}
		if(categoryName.equalsIgnoreCase("smartWatchRetDiscount")){
			smartwatch.setReDiscount(Float.parseFloat(elementValueRead));

			return;
		}

		

		if (categoryName.equals("laptop")) {
			laptops.put("L"+y, laptop);
			y++;
			return;
		}
		if (categoryName.equalsIgnoreCase("laptopImage")) {
			laptop.setImage(elementValueRead);
			return;
		}
		if (categoryName.equalsIgnoreCase("laptopName")) {
			laptop.setName(elementValueRead);
			return;
		}
		if (categoryName.equalsIgnoreCase("laptopCompany")) {
			laptop.setCompany(elementValueRead);
			return;
		}
		if (categoryName.equalsIgnoreCase("laptopCondition")) {
			laptop.setCondition(elementValueRead);
			return;
		}
		if(categoryName.equalsIgnoreCase("laptopPrice")){
			laptop.setPrice(Float.parseFloat(elementValueRead));
			return;
		}


		if(categoryName.equalsIgnoreCase("laptopColor")){
			laptop.setColor(elementValueRead);
			return;
		}
		if(categoryName.equalsIgnoreCase("laptopDescription")){
			laptop.setDescription(elementValueRead);
			return;
		}

		if(categoryName.equalsIgnoreCase("laptopManfRebate")){
			laptop.setMafRebate(Float.parseFloat(elementValueRead));

			return;
		}
		if(categoryName.equalsIgnoreCase("laptopretDiscount")){
			laptop.setReDiscount(Float.parseFloat(elementValueRead));

			return;
		}

		

		if (categoryName.equals("accessory")) {
			accessories.put("A"+a, accessory);
			a++;

			return;
		}
		if (categoryName.equalsIgnoreCase("accessoryImage")) {
			accessory.setImage(elementValueRead);

			return;
		}
		if (categoryName.equalsIgnoreCase("accessoryName")) {
			accessory.setName(elementValueRead);

			return;
		}
		if (categoryName.equalsIgnoreCase("accessoryCompany")) {
			accessory.setCompany(elementValueRead);

			return;
		}
		if (categoryName.equalsIgnoreCase("accessoryCondition")) {
			accessory.setCondition(elementValueRead);

			return;
		}
		if(categoryName.equalsIgnoreCase("accessoryPrice")){
			accessory.setPrice(Float.parseFloat(elementValueRead));

			return;
		}
		if(categoryName.equalsIgnoreCase("accessoryColor")){
			accessory.setColor(elementValueRead);

			return;
		}
		if(categoryName.equalsIgnoreCase("accessoryDescription")){
			accessory.setDescription(elementValueRead);
			return;
		}
		if(categoryName.equalsIgnoreCase("accessoryManfRebate")){
			accessory.setMafRebate(Float.parseFloat(elementValueRead));

			return;
		}
		if(categoryName.equalsIgnoreCase("accessoryRetDiscount")){
			accessory.setReDiscount(Float.parseFloat(elementValueRead));

			return;
		}

	}

	@Override
	public void characters(char[] content, int begin, int end) throws SAXException {
		elementValueRead = new String(content, begin, end);
	}


	public static void main(String[] args) {
		ProductSaxParser productSaxParser = new ProductSaxParser();
		productSaxParser.loadDataStore();
	}


}
