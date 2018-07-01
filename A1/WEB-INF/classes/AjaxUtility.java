

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

public class AjaxUtility {

	public static Connection getConnection()
	{
		Connection conn = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportablesdatabase", "root", "root");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return conn;
	}

	public static ArrayList<Object> fetchMysqlProducts()
	{
		ArrayList<Object> products = new ArrayList<Object>();

		HashMap<String, Smartphone> smartphones= new HashMap<String, Smartphone>();
		HashMap<String, Laptop> laptops= new HashMap<String, Laptop>();
		HashMap<String, Smartwatch> smartwatches= new HashMap<String, Smartwatch>();
		HashMap<String, Speaker> speakers= new HashMap<String, Speaker>();
		HashMap<String,WarrantyServlet> warranty= new HashMap<String, WarrantyServlet>();
		HashMap<String,Headphone> headphones= new HashMap<String, Headphone>();
		HashMap<String, Accessory> extraItems= new HashMap<String, Accessory>();


		HashMap<String, Product> productsMap= new HashMap<String, Product>();

		Smartphone smartphone;
		Laptop laptop;
		Smartwatch smartwatch;
		Speaker speaker;
		Accessory accessory;
		WarrantyServlet warrantyServlet;
		Headphone headphone;
		Product product;

		try
		{
			Connection conn = null;

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportablesdatabase?autoReconnect=true&useSSL=false", "root", "root");

			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT * FROM productdetails;");
			ResultSet rs = s.getResultSet();

			while (rs.next ())
			{
				Integer id = rs.getInt("orderId");
				String orderId = id.toString();
				String productType = rs.getString("productType");
				String retailerName = rs.getString ("retailerName");
				String imagePath = rs.getString ("imagePath");
				String productName = rs.getString ("productName");
				String companyName = rs.getString ("companyName");
				Float priceTotal = rs.getFloat("priceTotal");
				String colorName = rs.getString ("colorName");

				product = new Product();

				product.setType(productType);
				product.setId(orderId);
				product.setRetailer(retailerName);
				product.setImage(imagePath);
				product.setName(productName);
				product.setCompany(companyName);
				product.setPrice(priceTotal);
				product.setColor(colorName);

				productsMap.put(productName, product);

				if(productType.equals("Smartphone"))
				{
					smartphone = new Smartphone();

					smartphone.setId(orderId);
					smartphone.setRetailer(retailerName);
					smartphone.setImage(imagePath);
					smartphone.setName(productName);
					smartphone.setCompany(companyName);
					smartphone.setPrice(priceTotal);
					smartphone.setColor(colorName);

					smartphones.put(productName, smartphone);
				}

				if(productType.equals("Smartwatch"))
				{
					smartwatch = new Smartwatch();

					smartwatch.setId(orderId);
					smartwatch.setRetailer(retailerName);
					smartwatch.setImage(imagePath);
					smartwatch.setName(productName);
					smartwatch.setCompany(companyName);
					smartwatch.setPrice(priceTotal);
					smartwatch.setColor(colorName);

					smartwatches.put(productName, smartwatch);
				}

				if(productType.equals("WarrantyServlet"))
				{
					warrantyServlet = new WarrantyServlet();

					warrantyServlet.setId(orderId);
					warrantyServlet.setRetailer(retailerName);
					warrantyServlet.setImage(imagePath);
					warrantyServlet.setName(productName);
					warrantyServlet.setCompany(companyName);
					warrantyServlet.setPrice(priceTotal);
					warrantyServlet.setColor(colorName);

					warranty.put(productName, warrantyServlet);
				}

				if(productType.equals("Headphone"))
				{
					headphone = new Headphone();

					headphone.setId(orderId);
					headphone.setRetailer(retailerName);
					headphone.setImage(imagePath);
					headphone.setName(productName);
					headphone.setCompany(companyName);
					headphone.setPrice(priceTotal);
					headphone.setColor(colorName);

					headphones.put(productName, headphone);
				}

				if(productType.equals("Speaker"))
				{
					speaker = new Speaker();

					speaker.setId(orderId);
					speaker.setRetailer(retailerName);
					speaker.setImage(imagePath);
					speaker.setName(productName);
					speaker.setCompany(companyName);
					speaker.setPrice(priceTotal);
					speaker.setColor(colorName);

					speakers.put(productName, speaker);
				}

		if(productType.equals("Laptop"))
				{
					laptop = new Laptop();

					laptop.setId(orderId);
					laptop.setRetailer(retailerName);
					laptop.setImage(imagePath);
					laptop.setName(productName);
					laptop.setCompany(companyName);
					laptop.setPrice(priceTotal);
					laptop.setColor(colorName);

					laptops.put(productName, laptop);
				}

				if(productType.equals("Accessory"))
				{
					accessory = new Accessory();

					accessory.setId(orderId);
					accessory.setRetailer(retailerName);
					accessory.setImage(imagePath);
					accessory.setName(productName);
					accessory.setCompany(companyName);
					accessory.setPrice(priceTotal);
					accessory.setColor(colorName);

					extraItems.put(productName, accessory);
				}



			}
			products.add(smartphones);
			products.add(laptops);
			products.add(smartwatches);
			products.add(speakers);
			products.add(warranty);
			products.add(extraItems);
			products.add(headphones);
			products.add(productsMap);
			rs.close ();
			s.close ();
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return products;
	}
	

}


