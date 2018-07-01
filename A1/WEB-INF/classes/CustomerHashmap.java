import java.util.*;
import java.io.*;

public class CustomerHashmap {

	public HashMap<String, CustomerDetails> customers = new HashMap<String,CustomerDetails>();
	public String location = "C:/apache-tomcat-7.0.34/webapps/A1/WEB-INF/classes/DataStore";

	void fillCustomersData(){

			CustomerDetails details = new CustomerDetails("Ahsan", "Burney", "ahsanburney@gmail.com", "admin");
			customers.put(details.getemailId(), details);
	}

	public HashMap<String, CustomerDetails> getCustomerHashMap()
	{
		try{
			InputStream dataStore = CustomerHashmap.class.getResourceAsStream("DataStore");
			ObjectInputStream objectInputStream=new ObjectInputStream(dataStore);
			HashMap<String,CustomerDetails> map=(HashMap<String,CustomerDetails>)objectInputStream.readObject();
            objectInputStream.close();
			for(Map.Entry<String,CustomerDetails> detailsEntry :map.entrySet()){
			CustomerDetails value = detailsEntry.getValue();
			}
			if(map==null||map.isEmpty())
			{	System.out.println("empty");
			
		return customers;
			}
			else
			{
			return map;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Exception Occured");
		}
		return customers;
	}

	public void  writeCustomerHashMap(HashMap<String, CustomerDetails> customersUpdated){

		try{
			File customerHashmap=new File(location);
			FileOutputStream fileOutputStream=new FileOutputStream(customerHashmap);
			ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
				for(Map.Entry<String,CustomerDetails> detailsEntry :customersUpdated.entrySet()){
			CustomerDetails value = detailsEntry.getValue();
			}
			objectOutputStream.writeObject(customersUpdated);
			objectOutputStream.flush();
			objectOutputStream.close();
			fileOutputStream.close();
			
		}catch(Exception e){
			System.out.println("Exception Occured");
		}
	}
	
	private void  writeCustomerHashMap(){

		try{
			File customerHashmap=new File("CustomerStore");
			FileOutputStream fileOutputStream=new FileOutputStream(customerHashmap);
				ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(customers);
				objectOutputStream.flush();
				objectOutputStream.close();
				fileOutputStream.close();
			
		}catch(Exception e){
			System.out.println("Error Writing");
		}
	}
	
	private void readcustomerHashmap() {

		try{
			File customerHashmap=new File("CustomerStore");
			FileInputStream fileInputStream=new FileInputStream(customerHashmap);
			ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);

			HashMap<String,CustomerDetails> stringCustomerDetailsHashMap=(HashMap<String,CustomerDetails>)objectInputStream.readObject();

			objectInputStream.close();
			fileInputStream.close();
			System.out.println("map in File: "+stringCustomerDetailsHashMap);
			for(Map.Entry<String,CustomerDetails> detailsEntry :stringCustomerDetailsHashMap.entrySet()){
			CustomerDetails value = detailsEntry.getValue();
			}
		}catch(Exception e){
			
			System.out.println("Exception Occured");
		}

	}


	private void testDrive(){
		fillCustomersData();
		writeCustomerHashMap();
		readcustomerHashmap();
		
	}
	
	
	public static void main(String args[]){
        CustomerHashmap hashmap = new CustomerHashmap();
		hashmap.testDrive();
	}
	
	
}