

import java.util.*;
import java.io.*;

public class OrderDataStore{

	public HashMap<String, Order> Orders = new HashMap<String,Order>();
	public String orderDataStoreLocation = "C:/apache-tomcat-7.0.34/webapps/A1/WEB-INF/classes/OrderStore";



	public HashMap<String, Order> getOrderHashMap()
	{
		try {
			InputStream OrderStore = OrderDataStore.class.getResourceAsStream("OrderStore");
			ObjectInputStream objectInputStream = new ObjectInputStream(OrderStore);
			HashMap<String, Order> mapInFile = (HashMap<String, Order>) objectInputStream.readObject();
			objectInputStream.close();

			for (Map.Entry<String, Order> stringOrderEntry : mapInFile.entrySet()) {
				Order value = stringOrderEntry.getValue();
			}
			if (mapInFile == null || mapInFile.isEmpty()) {
				return Orders;
			} else {
				return mapInFile;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Exception occured!");
		}
		return Orders;
	}


	public void  writeOrderHashMap(HashMap<String, Order> OrdersUpdated){

		try{
			File OrderHashmap=new File(orderDataStoreLocation);
			FileOutputStream fileOutputStream=new FileOutputStream(OrderHashmap);
			ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);

				objectOutputStream.writeObject(OrdersUpdated);
				objectOutputStream.flush();
				objectOutputStream.close();
				fileOutputStream.close();
			
		}catch(Exception e){
			System.out.println("Failed");
		}
	}


	
	
	private void readOrderHashmap() {

		try{
			File OrderHashmap=new File("OrderStore");
			FileInputStream fileInputStream=new FileInputStream(OrderHashmap);
			ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
			HashMap<String,Order> mapInFile=(HashMap<String,Order>)objectInputStream.readObject();
			objectInputStream.close();
			fileInputStream.close();
			for(Map.Entry<String,Order> entry :mapInFile.entrySet()){
				Order value = entry.getValue();
			}
		}catch(Exception e){
			
			System.out.println("Exception occured");
		}

	}


	private void testDrive(){
		readOrderHashmap();
	}
	
	
	public static void main(String args[]){
        OrderDataStore orderDataStore = new OrderDataStore();
		orderDataStore.testDrive();
	}
}