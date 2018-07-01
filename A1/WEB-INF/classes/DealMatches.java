
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.util.*;

public class DealMatches implements Serializable {
	
	HashMap<String, Product> productFound;
	HashMap<String, Product> productHashMap;
	ArrayList<Object> products;
	ArrayList<String> twitterTweets = new ArrayList<String>();
	
	public ArrayList<String> getTwitterTweets()
	{
		return twitterTweets;
	}
	
	public HashMap<String, Product> getSelectedProductsFromTweets()
	{

		productFound = new HashMap<String, Product>();
		products = MySqlDataStoreUtilities.fetchAllProducts();
		productHashMap = (HashMap<String, Product>)products.get(7);
		twitterTweets = new ArrayList<String>();
		String s=null;
		
		try
		{
			for(Map.Entry<String, Product> entry: productHashMap.entrySet())
			{
				if(productFound.size()<2 && !productFound.containsKey(entry.getKey()))
				{
					BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("C:\\apache-tomcat-7.0.34\\webapps\\A1\\DealMatches.txt")));
					s = bufferedReader.readLine();
					if(s==null)
					{}
					else
					{
						do{
							if(s.contains(entry.getKey()))
							{
								twitterTweets.add(s);
								productFound.put(entry.getKey(), entry.getValue());
								break;
							}
						}while ((s=bufferedReader.readLine()) != null);
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return productFound;
	}
	
	public static void main(String args[]){
		
		DealMatches dealMatches = new DealMatches();
		dealMatches.getSelectedProductsFromTweets();
	}
}
