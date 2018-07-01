

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.util.*;

public class MongoDBDataStoreUtilities
{
	static DBCollection Reviews;
	public static void getConnection()
	{
		MongoClient mongoClient;
		mongoClient = new MongoClient("localhost", 27017);
		DB database= mongoClient.getDB("CustomerReviews");
		Reviews = database.getCollection("Reviews");
		System.out.println("Connection created");
	}

	public static void insertIntoReviewCollection(String productName, String category, double totalPrice, String companyName, String zip, String city,
												  String state, String sale, String company, String manufacturerRebate,
												  String emailId, int age, String gender, String occupation, int reviewRating,
												  Date dateOfReview, String reviewText)


	{
		MongoClient mongoClient;
		mongoClient = new MongoClient("localhost", 27017);
		DB database= mongoClient.getDB("CustomerReviews");
		Reviews = database.getCollection("Reviews");
		BasicDBObject basicDBObject = new BasicDBObject();
		basicDBObject.put("ProductModelName", productName);
		basicDBObject.put("ProductCategory", category);
		basicDBObject.put("ProductPrice", totalPrice);
		basicDBObject.put("RetailerName", companyName);
		basicDBObject.put("RetailerZip", zip);
		basicDBObject.put("RetailerCity", city);
		basicDBObject.put("RetailerState", state);
		basicDBObject.put("ProductOnSale", sale);
		basicDBObject.put("ManufacturerName", company);
		basicDBObject.put("manufacturerRebate", manufacturerRebate);
		basicDBObject.put("emailId", emailId);
		basicDBObject.put("UserAge", age);
		basicDBObject.put("UserGender", gender);
		basicDBObject.put("UserOccupation", occupation);
		basicDBObject.put("ReviewRating", reviewRating);
		basicDBObject.put("ReviewDate", dateOfReview);
		basicDBObject.put("ReviewText:", reviewText);
		Reviews.insert(basicDBObject);
	}

	public static HashMap<String, Review> fetchReviewsFromDB()
	{
		HashMap<String, Review> reviewHashMap = new HashMap<String, Review>();
		MongoClient mongoClient;
		mongoClient = new MongoClient("localhost", 27017);
		DB database= mongoClient.getDB("CustomerReviews");
		Reviews = database.getCollection("Reviews");
		DBCursor dbCursor = Reviews.find();
		while (dbCursor.hasNext())
		{
			BasicDBObject dbObject= (BasicDBObject) dbCursor.next();
			Review review = new Review(dbObject.getString("ProductModelName"),
					dbObject.getString("ProductCategory"),
					dbObject.getDouble("ProductPrice")
					,dbObject.getString("RetailerName")
					,dbObject.getString("RetailerZip")
					,dbObject.getString("RetailerCity")
					,dbObject.getString("RetailerState")
					,dbObject.getString("ProductOnSale")
					,dbObject.getString("ManufacturerName")
					,dbObject.getString("manufacturerRebate")
					,dbObject.getString("emailId")
					,dbObject.getInt("UserAge")
					,dbObject.getString("UserGender")
					,dbObject.getString("UserOccupation")
					,dbObject.getInt("ReviewRating")
					,dbObject.getDate("ReviewDate")
					,dbObject.getString("ReviewText:"));

			reviewHashMap.put(dbObject.getString("name")+dbObject.getString("_id"), review);
		}
		return reviewHashMap;
	}

	public static LinkedHashMap<String, Double> fiveMostLikedProducts()
	{
		LinkedHashMap<String, Double> fiveMostLiked = new LinkedHashMap<String, Double>();
		MongoClient mongoClient;
		mongoClient = new MongoClient("localhost", 27017);
		DB database= mongoClient.getDB("CustomerReviews");
		Reviews = database.getCollection("Reviews");
		AggregationOutput aggregate = Reviews.aggregate(new BasicDBObject("$group", new BasicDBObject("_id", "$ProductModelName").append("averageRating", new BasicDBObject("$avg", "$ReviewRating"))), new BasicDBObject("$sort", new BasicDBObject("avgRating", -1)), new BasicDBObject("$limit", 5));
		String productModelName="";
		double average = 0;
		for (DBObject doc : aggregate.results())
		{
			productModelName = (String) doc.get("_id");
			average = (Double) doc.get("averageRating");
			if(average>3)
			{
				fiveMostLiked.put(productModelName, average);
			}
		}
		return fiveMostLiked;
	}

	public static LinkedHashMap<String, Integer> fiveZipcodesOfMostSold()
	{
		LinkedHashMap<String, Integer> fiveZipCodes = new LinkedHashMap<String, Integer>();
		MongoClient mongoClient;
		mongoClient = new MongoClient("localhost", 27017);
		DB database= mongoClient.getDB("CustomerReviews");
		Reviews = database.getCollection("Reviews");
		AggregationOutput aggregate = Reviews.aggregate(new BasicDBObject("$group", new BasicDBObject("_id", "$RetailerZip").append("counter", new BasicDBObject("$sum", 1))), new BasicDBObject("$sort", new BasicDBObject("counter", -1)), new BasicDBObject("$limit", 5));
		String zipCodes="";
		int counter = 0;
		for (DBObject doc : aggregate.results())
		{
			zipCodes = (String) doc.get("_id");
			counter = (Integer) doc.get("counter");
			fiveZipCodes.put(zipCodes, counter);
		}
		return fiveZipCodes;
	}

	public static LinkedHashMap<String, Double> ListofAllProductsAndTheirRatings()
	{
		LinkedHashMap<String, Double> ListofAllProductsAndTheirRatings = new LinkedHashMap<String, Double>();
		MongoClient mongoClient;
		mongoClient = new MongoClient("localhost", 27017);
		DB database= mongoClient.getDB("CustomerReviews");
		Reviews = database.getCollection("Reviews");
		AggregationOutput aggregate = Reviews.aggregate(new BasicDBObject("$group",new BasicDBObject("_id", "$ProductModelName").append("averageRating", new BasicDBObject("$avg", "$ReviewRating"))), new BasicDBObject("$sort", new BasicDBObject("averageRating", -1)));
		String productModelName="";
		double average = 0;
		for (DBObject doc : aggregate.results())
		{
			productModelName = (String) doc.get("_id");
			average = (Double) doc.get("averageRating");
			ListofAllProductsAndTheirRatings.put(productModelName, average);

		}
		return ListofAllProductsAndTheirRatings;
	}

	public static HashMap<String, Review> Printalistofreviewswhereratinggreaterthan3()
	{
		HashMap<String, Review> reviewHashMap = new HashMap<String, Review>();
		MongoClient mongoClient;
		mongoClient = new MongoClient("localhost", 27017);
		DB database= mongoClient.getDB("CustomerReviews");
		Reviews = database.getCollection("Reviews");
		BasicDBObject basicDBObject = new BasicDBObject("ReviewRating", new BasicDBObject("$gt", 3));
		DBCursor dbCursor = Reviews.find(basicDBObject);
		while (dbCursor.hasNext())
		{
			BasicDBObject dbObject= (BasicDBObject) dbCursor.next();
			Review review = new Review(dbObject.getString("ProductModelName")
					,dbObject.getString("ProductCategory")
					,dbObject.getDouble("ProductPrice")
					,dbObject.getString("RetailerName")
					,dbObject.getString("RetailerZip")
					,dbObject.getString("RetailerCity")
					,dbObject.getString("RetailerState")
					,dbObject.getString("ProductOnSale")
					,dbObject.getString("ManufacturerName")
					,dbObject.getString("manufacturerRebate")
					,dbObject.getString("emailId")
					,dbObject.getInt("UserAge")
					,dbObject.getString("UserGender")
					,dbObject.getString("UserOccupation")
					,dbObject.getInt("ReviewRating")
					,dbObject.getDate("ReviewDate")
					,dbObject.getString("ReviewText:"));
			reviewHashMap.put(dbObject.getString("ProductModelName")+dbObject.getString("_id"), review);
		}
		return reviewHashMap;
	}

	public static HashMap<String, Review> fetchReviewListwhereRating5andprice100()
	{
		HashMap<String, Review> reviewHashMap = new HashMap<String, Review>();
		MongoClient mongoClient;
		mongoClient = new MongoClient("localhost", 27017);
		DB database= mongoClient.getDB("CustomerReviews");
		Reviews = database.getCollection("Reviews");
		DBObject condition = new BasicDBObject("ReviewRating", new BasicDBObject("$eq", 5));
		DBObject condition2 = new BasicDBObject("ProductPrice", new BasicDBObject("$gt", 1000));
		BasicDBList concate = new BasicDBList();
		concate.add(condition);
		concate.add(condition2);
		DBObject resultSet = new BasicDBObject("$and", concate);
		DBCursor dbCursor = Reviews.find(resultSet);
		while (dbCursor.hasNext())
		{
			BasicDBObject dbObject= (BasicDBObject) dbCursor.next();
			Review review = new Review(dbObject.getString("ProductModelName")
					,dbObject.getString("ProductCategory")
					,dbObject.getDouble("ProductPrice")
					,dbObject.getString("RetailerName")
					,dbObject.getString("RetailerZip")
					,dbObject.getString("RetailerCity")
					,dbObject.getString("RetailerState")
					,dbObject.getString("ProductOnSale")
					,dbObject.getString("ManufacturerName")
					,dbObject.getString("manufacturerRebate")
					,dbObject.getString("emailId")
					,dbObject.getInt("UserAge")
					,dbObject.getString("UserGender")
					,dbObject.getString("UserOccupation")
					,dbObject.getInt("ReviewRating")
					,dbObject.getDate("ReviewDate")
					,dbObject.getString("ReviewText:"));
			reviewHashMap.put(dbObject.getString("ProductModelName")+dbObject.getString("_id"), review);
		}
		return reviewHashMap;
	}

	public static LinkedHashMap<String, Integer> fetchReviewsCountForProducts()
	{
		LinkedHashMap<String, Integer> stringIntegerLinkedHashMap = new LinkedHashMap<String, Integer>();
		MongoClient mongoClient;
		mongoClient = new MongoClient("localhost", 27017);
		DB database= mongoClient.getDB("CustomerReviews");
		Reviews = database.getCollection("Reviews");
		AggregationOutput aggregate = Reviews.aggregate(new BasicDBObject("$group",new BasicDBObject("_id", "$ProductModelName").append("counter", new BasicDBObject("$sum", 1))), new BasicDBObject("$sort", new BasicDBObject("counter", -1)));
		String productModelName="";
		int counter = 0;
		for (DBObject doc : aggregate.results())
		{
			productModelName = (String) doc.get("_id");
			counter = (Integer) doc.get("counter");
			stringIntegerLinkedHashMap.put(productModelName, counter);
		}

		return stringIntegerLinkedHashMap;
	}

	public static HashMap<String, Review> fetchReviewsOfChicagoShoppers()
	{
		HashMap<String, Review> reviewHashMap = new HashMap<String, Review>();
		MongoClient mongoClient;
		mongoClient = new MongoClient("localhost", 27017);
		DB database= mongoClient.getDB("CustomerReviews");
		Reviews = database.getCollection("Reviews");
		BasicDBObject basicDBObject = new BasicDBObject("RetailerCity", new BasicDBObject("$eq", "Chicago"));
		DBCursor dbCursor = Reviews.find(basicDBObject);
		while (dbCursor.hasNext())
		{
			BasicDBObject dbObject= (BasicDBObject) dbCursor.next();
			Review review = new Review(dbObject.getString("ProductModelName")
					,dbObject.getString("ProductCategory")
					,dbObject.getDouble("ProductPrice")
					,dbObject.getString("RetailerName")
					,dbObject.getString("RetailerZip")
					,dbObject.getString("RetailerCity")
					,dbObject.getString("RetailerState")
					,dbObject.getString("ProductOnSale")
					,dbObject.getString("ManufacturerName")
					,dbObject.getString("manufacturerRebate")
					,dbObject.getString("emailId")
					,dbObject.getInt("UserAge")
					,dbObject.getString("UserGender")
					,dbObject.getString("UserOccupation")
					,dbObject.getInt("ReviewRating")
					,dbObject.getDate("ReviewDate")
					,dbObject.getString("ReviewText:"));
			reviewHashMap.put(dbObject.getString("productName")+dbObject.getString("_id"), review);
		}
		return reviewHashMap;

	}

	public static LinkedHashMap<String, ArrayList<Object>> Findhighestpriceproductreviewedsoldineverycity()
	{
		LinkedHashMap<String, ArrayList<Object>> Findhighestpriceproductreviewedsoldineverycity = new LinkedHashMap<String, ArrayList<Object>>();
		MongoClient mongoClient;
		mongoClient = new MongoClient("localhost", 27017);
		DB database= mongoClient.getDB("CustomerReviews");
		Reviews = database.getCollection("Reviews");
		AggregationOutput aggregate = Reviews.aggregate(new BasicDBObject("$sort", new BasicDBObject("ProductPrice", -1)), new BasicDBObject("$group", new BasicDBObject("_id", "$RetailerCity").append("HighestPrice", new BasicDBObject("$max", "$ProductPrice")).append("ProductModelName", new BasicDBObject("$max", "$ProductModelName"))), new BasicDBObject("$sort", new BasicDBObject("RetailerCity", 1)));
		String RetailerCity="";
		double HighestPrice = 0;
		String ProductModelName="";
		for (DBObject doc : aggregate.results())
		{
			RetailerCity = (String) doc.get("_id");
			HighestPrice = (Double) doc.get("HighestPrice");
			ProductModelName = (String) doc.get("ProductModelName");
			ArrayList<Object> objectArrayList = new ArrayList<Object>();
			objectArrayList.add(RetailerCity);
			objectArrayList.add(ProductModelName);
			objectArrayList.add(HighestPrice);
			Findhighestpriceproductreviewedsoldineverycity.put(RetailerCity, objectArrayList);
		}
		return Findhighestpriceproductreviewedsoldineverycity;
	}

	public static LinkedHashMap<String, ArrayList<Object>> Findhighestpriceproductreviewedsoldineveryzipcode()
	{
		LinkedHashMap<String, ArrayList<Object>> linkedHashMap = new LinkedHashMap<String, ArrayList<Object>>();
		MongoClient mongoClient;
		mongoClient = new MongoClient("localhost", 27017);
		DB database= mongoClient.getDB("CustomerReviews");
		Reviews = database.getCollection("Reviews");
		AggregationOutput aggregate = Reviews.aggregate(new BasicDBObject("$sort", new BasicDBObject("ProductPrice", -1)), new BasicDBObject("$group", new BasicDBObject("_id", "$RetailerZip").append("highestPrice", new BasicDBObject("$max", "$ProductPrice")).append("ProductModelName", new BasicDBObject("$max", "$ProductModelName"))),new BasicDBObject("$sort", new BasicDBObject("RetailerZip", 1)));
		String RetailerZip="";
		double highestPrice = 0;
		String ProductModelName="";

		for (DBObject doc : aggregate.results())
		{
			RetailerZip = (String) doc.get("_id");
			highestPrice = (Double) doc.get("highestPrice");
			ProductModelName = (String) doc.get("ProductModelName");
			ArrayList<Object> objectArrayList = new ArrayList<Object>();
			objectArrayList.add(RetailerZip);
			objectArrayList.add(ProductModelName);
			objectArrayList.add(highestPrice);
			linkedHashMap.put(RetailerZip, objectArrayList);
		}

		return linkedHashMap;
	}

	public static LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>> getthetop5listoflikedproductsforeverycity()
	{

		LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>> linkedHashMap = new LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>>();
		MongoClient mongoClient;
		mongoClient = new MongoClient("localhost", 27017);
		DB database= mongoClient.getDB("CustomerReviews");
		Reviews = database.getCollection("Reviews");
		AggregationOutput aggregate = Reviews.aggregate(new BasicDBObject("$group", new BasicDBObject("_id", "$RetailerCity")));
		String RetailerCity="";
		for (DBObject doc : aggregate.results())
		{
			RetailerCity = (String) doc.get("_id");
			AggregationOutput aggregate1 = Reviews.aggregate(new BasicDBObject("$match", new BasicDBObject("RetailerCity", new BasicDBObject("$eq", RetailerCity) ) ), new BasicDBObject("$group", new BasicDBObject("_id", "$ProductModelName").append("averageRating", new BasicDBObject("$average", "$ReviewRating"))), new BasicDBObject("$sort", new BasicDBObject("averageRating", -1)), new BasicDBObject("$limit", 5));
			String city = RetailerCity;
			String productModelName="";
			double average = 0;
			LinkedHashMap<String, ArrayList<Object>> hashMap = new LinkedHashMap<String, ArrayList<Object>>();
			for (DBObject doc2 : aggregate1.results())
			{
				productModelName = (String) doc2.get("_id");
				average = (Double) doc2.get("averageRating");
				ArrayList<Object> objectArrayList = new ArrayList<Object>();
				objectArrayList.add(RetailerCity);
				objectArrayList.add(productModelName);
				objectArrayList.add(average);
				hashMap.put(productModelName, objectArrayList);
			}

			linkedHashMap.put(RetailerCity, hashMap);
		}
		return linkedHashMap;
	}

	public static LinkedHashMap<String, LinkedHashMap<String, Review>> printalistofreviewsgroupedbyCity()
	{
		LinkedHashMap<String, LinkedHashMap<String, Review>> linkedHashMap = new LinkedHashMap<String, LinkedHashMap<String, Review>>();
		MongoClient mongoClient;
		mongoClient = new MongoClient("localhost", 27017);
		DB database= mongoClient.getDB("CustomerReviews");
		Reviews = database.getCollection("Reviews");
		AggregationOutput aggregationOutput = Reviews.aggregate(new BasicDBObject("$group", new BasicDBObject("_id", "$RetailerCity")));
		String RetailerCity="";
		for (DBObject doc : aggregationOutput.results())
		{
			RetailerCity = (String) doc.get("_id");
			LinkedHashMap<String, Review> hashMap = new LinkedHashMap<String, Review>();
			BasicDBObject query = new BasicDBObject("RetailerCity", new BasicDBObject("$eq", RetailerCity));
			DBCursor dbCursor = Reviews.find(query);
			while (dbCursor.hasNext())
			{
				BasicDBObject dbObject= (BasicDBObject) dbCursor.next();
				Review review = new Review(dbObject.getString("ProductModelName")
						,dbObject.getString("ProductCategory")
						,dbObject.getDouble("ProductPrice")
						,dbObject.getString("RetailerName")
						,dbObject.getString("RetailerZip")
						,dbObject.getString("RetailerCity")
						,dbObject.getString("RetailerState")
						,dbObject.getString("ProductOnSale")
						,dbObject.getString("ManufacturerName")
						,dbObject.getString("manufacturerRebate")
						,dbObject.getString("emailId")
						,dbObject.getInt("UserAge")
						,dbObject.getString("UserGender")
						,dbObject.getString("UserOccupation")
						,dbObject.getInt("ReviewRating")
						,dbObject.getDate("ReviewDate")
						,dbObject.getString("ReviewText:"));

				hashMap.put(dbObject.getString("ProductModelName"), review);
			}

			linkedHashMap.put(RetailerCity, hashMap);
		}
		return linkedHashMap;
	}

	public static LinkedHashMap<String, LinkedHashMap<String, Review>> printalistofreviewsgroupedbyzipcode()
	{
		LinkedHashMap<String, LinkedHashMap<String, Review>> linkedHashMap = new LinkedHashMap<String, LinkedHashMap<String, Review>>();
		MongoClient mongoClient;
		mongoClient = new MongoClient("localhost", 27017);
		DB database= mongoClient.getDB("CustomerReviews");
		Reviews = database.getCollection("Reviews");
		AggregationOutput aggregationOutput = Reviews.aggregate(new BasicDBObject("$group", new BasicDBObject("_id", "$RetailerZip")));
		String RetailerZip="";
		for (DBObject doc : aggregationOutput.results())
		{
			RetailerZip = (String) doc.get("_id");
			LinkedHashMap<String, Review> productsListHashMap = new LinkedHashMap<String, Review>();
			BasicDBObject basicDBObject = new BasicDBObject("RetailerZip", new BasicDBObject("$eq", RetailerZip));
			DBCursor cursor = Reviews.find(basicDBObject);
			while (cursor.hasNext())
			{
				BasicDBObject dbObject= (BasicDBObject) cursor.next();
				Review review = new Review(dbObject.getString("ProductModelName")
						,dbObject.getString("ProductCategory")
						,dbObject.getDouble("ProductPrice")
						,dbObject.getString("RetailerName")
						,dbObject.getString("RetailerZip")
						,dbObject.getString("RetailerCity")
						,dbObject.getString("RetailerState")
						,dbObject.getString("ProductOnSale")
						,dbObject.getString("ManufacturerName")
						,dbObject.getString("manufacturerRebate")
						,dbObject.getString("emailId")
						,dbObject.getInt("UserAge")
						,dbObject.getString("UserGender")
						,dbObject.getString("UserOccupation")
						,dbObject.getInt("ReviewRating")
						,dbObject.getDate("ReviewDate")
						,dbObject.getString("ReviewText:"));
				productsListHashMap.put(dbObject.getString("ProductModelName")+dbObject.getString("_id"), review);
			}
			linkedHashMap.put(RetailerZip, productsListHashMap);
		}
		return linkedHashMap;
	}

	public static LinkedHashMap<String, LinkedHashMap<String, Double>> getthetotalnumberofproductsreviewedandgotRating5inEveryCity()
	{
		LinkedHashMap<String, LinkedHashMap<String, Double>> linkedHashMap = new LinkedHashMap<String, LinkedHashMap<String, Double>>();
		MongoClient mongoClient;
		mongoClient = new MongoClient("localhost", 27017);
		DB database= mongoClient.getDB("CustomerReviews");
		Reviews = database.getCollection("Reviews");
		AggregationOutput aggregate = Reviews.aggregate(new BasicDBObject("$group", new BasicDBObject("_id", "$RetailerCity")));
		String RetailerCity="";
		for (DBObject doc : aggregate.results())
		{
			RetailerCity = (String) doc.get("_id");
			LinkedHashMap<String, Double> hashMap = new LinkedHashMap<String, Double>();
			AggregationOutput aggregationOutput = Reviews.aggregate(new BasicDBObject("$match", new BasicDBObject("RetailerCity",new BasicDBObject("$eq", RetailerCity) ) ), new BasicDBObject("$group", new BasicDBObject("_id", "$ProductModelName").append("averageRating", new BasicDBObject("$avg", "$ReviewRating"))), new BasicDBObject("$match", new BasicDBObject("averageRating", new BasicDBObject("$eq", 5) ) ), new BasicDBObject("$sort", new BasicDBObject("averageRating", -1)));
			String productModelName="";
			double avg = 0;
			for (DBObject doc2 : aggregationOutput.results())
			{
				productModelName = (String) doc2.get("_id");
				avg = (Double) doc2.get("averageRating");
				hashMap.put(productModelName, avg);
			}

			linkedHashMap.put(RetailerCity, hashMap);
		}
		return linkedHashMap;
	}

	public static LinkedHashMap<String, LinkedHashMap<String, Double>> mostLikedProductInEveryCityBarchart()
	{
		LinkedHashMap<String, LinkedHashMap<String, Double>> linkedHashMap = new LinkedHashMap<String, LinkedHashMap<String, Double>>();
		MongoClient mongoClient;
		mongoClient = new MongoClient("localhost", 27017);
		DB database= mongoClient.getDB("CustomerReviews");
		Reviews = database.getCollection("Reviews");
		AggregationOutput aggregationOutput = Reviews.aggregate(new BasicDBObject("$group", new BasicDBObject("_id", "$RetailerCity")));
		String RetailerCity="";
		for (DBObject doc : aggregationOutput.results())
		{
			RetailerCity = (String) doc.get("_id");
			LinkedHashMap<String, Double> hashMap = new LinkedHashMap<String, Double>();
			AggregationOutput output = Reviews.aggregate(new BasicDBObject("$match", new BasicDBObject("RetailerCity", new BasicDBObject("$eq", RetailerCity) ) ), new BasicDBObject("$group", new BasicDBObject("_id", "$ProductModelName").append("averageRating", new BasicDBObject("$avg", "$ReviewRating"))), new BasicDBObject("$match", new BasicDBObject("averageRating", new BasicDBObject("$eq", 5) ) ), new BasicDBObject("$sort", new BasicDBObject("averageRating", -1)), new BasicDBObject("$limit", 3));
			String productModelName="";
			double average = 0;
			for (DBObject doc2 : output.results())
			{
				productModelName = (String) doc2.get("_id");
				average = (Double) doc2.get("averageRating");
				hashMap.put(productModelName, average);

			}

			linkedHashMap.put(RetailerCity, hashMap);
		}
		return linkedHashMap;
	}

	public static LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>> mostDislikedProductinCityByRetailerName()
	{
		LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>> linkedHashMap = new LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>>();
		MongoClient mongoClient;
		mongoClient = new MongoClient("localhost", 27017);
		DB database= mongoClient.getDB("CustomerReviews");
		Reviews = database.getCollection("Reviews");
		AggregationOutput aggregate = Reviews.aggregate(new BasicDBObject("$group", new BasicDBObject("_id", "$RetailerCity")));
		String RetailerCity="";
		for (DBObject doc : aggregate.results())
		{
			RetailerCity = (String) doc.get("_id");
			LinkedHashMap<String, ArrayList<Object>> hashMap = new LinkedHashMap<String, ArrayList<Object>>();
			AggregationOutput aggregationOutput = Reviews.aggregate(new BasicDBObject("$match", new BasicDBObject("RetailerCity", new BasicDBObject("$eq", RetailerCity) ) ), new BasicDBObject("$group", new BasicDBObject("_id", "$ProductModelName").append("RetailerName", new BasicDBObject("$first", "$RetailerName")).append("averageRating", new BasicDBObject("$avg", "$ReviewRating"))), new BasicDBObject("$sort", new BasicDBObject("avgRating", 1)), new BasicDBObject("$limit", 5));

			String RetailerName="";
			String productName="";
			double average = 0;
			for (DBObject doc2 : aggregationOutput.results())
			{
				productName = (String) doc2.get("_id");
				average = (Double) doc2.get("averageRating");
				RetailerName = (String) doc2.get("RetailerName");
				ArrayList<Object> objectArrayList = new ArrayList<Object>();
				objectArrayList.add(RetailerName);
				objectArrayList.add(productName);
				objectArrayList.add(average);
				hashMap.put(productName, objectArrayList);
			}

			linkedHashMap.put(RetailerCity, hashMap);
		}
		return linkedHashMap;
	}

	public static LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>> mostDislikedProductInEveryZipCodeByRetailerName()
	{
		LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>> linkedHashMap = new LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>>();
		MongoClient mongoClient;
		mongoClient = new MongoClient("localhost", 27017);
		DB database= mongoClient.getDB("CustomerReviews");
		Reviews = database.getCollection("Reviews");
		AggregationOutput aggregate = Reviews.aggregate(new BasicDBObject("$group", new BasicDBObject("_id", "$RetailerZip")));
		String RetailerZip="";
		for (DBObject doc : aggregate.results())
		{
			RetailerZip = (String) doc.get("_id");
			LinkedHashMap<String, ArrayList<Object>> hashMap = new LinkedHashMap<String, ArrayList<Object>>();
			AggregationOutput aggregationOutput = Reviews.aggregate(new BasicDBObject("$match", new BasicDBObject("RetailerZip", new BasicDBObject("$eq", RetailerZip) ) ), new BasicDBObject("$group", new BasicDBObject("_id", "$ProductModelName").append("retailer", new BasicDBObject("$first", "$RetailerName")).append("avgRating", new BasicDBObject("$avg", "$ReviewRating"))), new BasicDBObject("$sort", new BasicDBObject("avgRating", 1)), new BasicDBObject("$limit", 5));
			String RetailerName="";
			String productName="";
			double average = 0;
			for (DBObject doc2 : aggregationOutput.results())
			{
				productName = (String) doc2.get("_id");
				average = (Double) doc2.get("avgRating");
				RetailerName = (String) doc2.get("retailer");
				ArrayList<Object> objectArrayList = new ArrayList<Object>();
				objectArrayList.add(RetailerName);
				objectArrayList.add(productName);
				objectArrayList.add(average);
				hashMap.put(productName, objectArrayList);
			}
			linkedHashMap.put(RetailerZip, hashMap);
		}
		return linkedHashMap;
	}

	public static LinkedHashMap<String, LinkedHashMap<String, Review>> getReviewAgeGreaterThan50InEveryCity()
	{
		LinkedHashMap<String, LinkedHashMap<String, Review>> linkedHashMap = new LinkedHashMap<String, LinkedHashMap<String, Review>>();
		MongoClient mongoClient;
		mongoClient = new MongoClient("localhost", 27017);
		DB database= mongoClient.getDB("CustomerReviews");
		Reviews = database.getCollection("Reviews");
		AggregationOutput aggregate = Reviews.aggregate(new BasicDBObject("$group", new BasicDBObject("_id", "$RetailerCity")));
		String RetailerCity="";
		for (DBObject doc : aggregate.results())
		{
			RetailerCity = (String) doc.get("_id");
			LinkedHashMap<String, Review> hashMap = new LinkedHashMap<String, Review>();
			DBObject dbObject1 = new BasicDBObject("RetailerCity", new BasicDBObject("$eq", RetailerCity));
			DBObject dbObject2 = new BasicDBObject("UserAge", new BasicDBObject("$gt", 50));
			BasicDBList and = new BasicDBList();
			and.add(dbObject1);
			and.add(dbObject2);
			DBObject resultSet = new BasicDBObject("$and", and);
			DBCursor dbCursor = Reviews.find(resultSet);
			dbCursor.sort(new BasicDBObject("UserAge ", 1));
			while (dbCursor.hasNext())
			{
				BasicDBObject dbObject= (BasicDBObject) dbCursor.next();
				Review review = new Review(dbObject.getString("ProductModelName")
						,dbObject.getString("ProductCategory")
						,dbObject.getDouble("ProductPrice")
						,dbObject.getString("RetailerName")
						,dbObject.getString("RetailerZip")
						,dbObject.getString("RetailerCity")
						,dbObject.getString("RetailerState")
						,dbObject.getString("ProductOnSale")
						,dbObject.getString("ManufacturerName")
						,dbObject.getString("manufacturerRebate")
						,dbObject.getString("emailId")
						,dbObject.getInt("UserAge")
						,dbObject.getString("UserGender")
						,dbObject.getString("UserOccupation")
						,dbObject.getInt("ReviewRating")
						,dbObject.getDate("ReviewDate")
						,dbObject.getString("ReviewText:"));
				hashMap.put(dbObject.getString("ProductModelName")+dbObject.getString("_id"), review);
			}

			linkedHashMap.put(RetailerCity, hashMap);
		}
		return linkedHashMap;
	}

	public static LinkedHashMap<String, LinkedHashMap<String, Double>> medianProductPricesInEachCity()
	{

		LinkedHashMap<String, LinkedHashMap<String, Double>> linkedHashMap = new LinkedHashMap<String, LinkedHashMap<String, Double>>();
		MongoClient mongoClient;
		mongoClient = new MongoClient("localhost", 27017);
		DB database= mongoClient.getDB("CustomerReviews");
		Reviews = database.getCollection("Reviews");
		AggregationOutput aggregate =Reviews.aggregate(new BasicDBObject("$group", new BasicDBObject("_id", "$RetailerCity")));
		String RetailerCity="";
		for (DBObject doc : aggregate.results())
		{
			RetailerCity = (String) doc.get("_id");
			LinkedHashMap<String, Double> hashMap = new LinkedHashMap<String, Double>();
			AggregationOutput aggregationOutput = Reviews.aggregate(new BasicDBObject("$match", new BasicDBObject("RetailerCity", new BasicDBObject("$eq", RetailerCity) ) ), new BasicDBObject("$sort", new BasicDBObject("productPrice", 1)), new BasicDBObject("$limit", 1));
			String productModelName="";
			double medianPrice = 0;
			for (DBObject doc2 : aggregationOutput.results())
			{
				productModelName = (String) doc2.get("_id");
				medianPrice = (Double) doc2.get("ProductPrice");
				hashMap.put(productModelName, medianPrice);
			}

			linkedHashMap.put(RetailerCity, hashMap);
		}
		return linkedHashMap;
	}
}