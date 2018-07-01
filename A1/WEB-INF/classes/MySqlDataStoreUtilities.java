

import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

import java.sql.DriverManager;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;


public class MySqlDataStoreUtilities {
    public static Connection makeConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportablesdatabase", "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }


    public static void LoadProductsToDatabase(){

        ArrayList<Object> products;
        HashMap<String, Smartphone> smartphones;
        HashMap<String, Laptop> laptops;
        HashMap<String, Accessory> accessories;
        HashMap<String, Smartwatch> smartwatches;
        HashMap<String, Speaker> speakers;
        HashMap<String, WarrantyServlet> warranties;
        HashMap<String, Headphone> headphones;
        ProductSaxParser productSaxParser = new ProductSaxParser();
        products = productSaxParser.loadDataStore();
        smartphones = (HashMap<String, Smartphone>) products.get(0);
        laptops = (HashMap<String, Laptop>) products.get(1);
        accessories = (HashMap<String, Accessory>) products.get(2);
        smartwatches = (HashMap<String, Smartwatch>) products.get(3);
        speakers = (HashMap<String, Speaker>) products.get(4);
        headphones = (HashMap<String, Headphone>)products.get(5);
        warranties = (HashMap<String, WarrantyServlet>)products.get(6);

        try {
            Connection connection = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportablesdatabase?autoReconnect=true&useSSL=false", "root", "root");
            for(Map.Entry<String,Smartphone> mapping :smartphones.entrySet())
            {
                String load = "INSERT INTO productdetails(orderId, productType, retailerName, imagePath, productName, companyName, priceTotal, colorName,manufacturerRebate) " + "VALUES (?,?,?,?,?,?,?,?,?);";
                PreparedStatement preparedStatement = connection.prepareStatement(load);
                String category = "Smartphone";
                Smartphone smartphone = mapping.getValue();
                preparedStatement.setString(1,smartphone.getId());
                preparedStatement.setString(2,category);
                preparedStatement.setString(3,smartphone.getRetailer());
                preparedStatement.setString(4,smartphone.getImage());
                preparedStatement.setString(5,smartphone.getName());
                preparedStatement.setString(6,smartphone.getCompany());
                preparedStatement.setFloat(7,smartphone.getPrice());
                preparedStatement.setString(8,smartphone.getColor());
                preparedStatement.setFloat(9,smartphone.getMafRebate());
                preparedStatement.setFloat(9,smartphone.getMafRebate());

                preparedStatement.execute();
            }

            for(Map.Entry<String,Laptop> mapping :laptops.entrySet())
            {
                String load = "INSERT INTO productdetails(orderId, productType, retailerName, imagePath, productName, companyName, priceTotal, colorName,manufacturerRebate) " + "VALUES (?,?,?,?,?,?,?,?,?);";
                PreparedStatement preparedStatement = connection.prepareStatement(load);
                String category = "Laptop";
                Laptop laptop = mapping.getValue();
                preparedStatement.setString(1,laptop.getId());
                preparedStatement.setString(2,category);
                preparedStatement.setString(3,laptop.getRetailer());
                preparedStatement.setString(4,laptop.getImage());
                preparedStatement.setString(5,laptop.getName());
                preparedStatement.setString(6,laptop.getCompany());
                preparedStatement.setFloat(7,laptop.getPrice());
                preparedStatement.setString(8,laptop.getColor());
                preparedStatement.setFloat(9,laptop.getMafRebate());
                preparedStatement.execute();
            }

            for(Map.Entry<String,Accessory> mapping :accessories.entrySet())
            {
                String load = "INSERT INTO productdetails(orderId, productType, retailerName, imagePath, productName, companyName, priceTotal, colorName, manufacturerRebate) " + "VALUES (?,?,?,?,?,?,?,?,?);";
                PreparedStatement preparedStatement = connection.prepareStatement(load);
                String category = "Accessory";
                Accessory accessory = mapping.getValue();
                preparedStatement.setString(1,accessory.getId());
                preparedStatement.setString(2,category);
                preparedStatement.setString(3,accessory.getRetailer());
                preparedStatement.setString(4,accessory.getImage());
                preparedStatement.setString(5,accessory.getName());
                preparedStatement.setString(6,accessory.getCompany());
                preparedStatement.setFloat(7,accessory.getPrice());
                preparedStatement.setString(8,accessory.getColor());
                preparedStatement.setFloat(9,accessory.getMafRebate());
                preparedStatement.execute();
            }

            for(Map.Entry<String,Smartwatch> mapping :smartwatches.entrySet())
            {
                String load = "INSERT INTO productdetails(orderId, productType, retailerName, imagePath, productName, companyName, priceTotal, colorName,manufacturerRebate) " + "VALUES (?,?,?,?,?,?,?,?,?);";
                PreparedStatement preparedStatement = connection.prepareStatement(load);
                String category = "Smartwatch";
                Smartwatch smartwatch = mapping.getValue();
                preparedStatement.setString(1,smartwatch.getId());
                preparedStatement.setString(2,category);
                preparedStatement.setString(3,smartwatch.getRetailer());
                preparedStatement.setString(4,smartwatch.getImage());
                preparedStatement.setString(5,smartwatch.getName());
                preparedStatement.setString(6,smartwatch.getCompany());
                preparedStatement.setFloat(7,smartwatch.getPrice());
                preparedStatement.setString(8,smartwatch.getColor());
                preparedStatement.setFloat(9,smartwatch.getMafRebate());
                preparedStatement.execute();
            }

            for(Map.Entry<String,Speaker> mapping :speakers.entrySet())
            {
                String load = "INSERT INTO productdetails(orderId, productType, retailerName, imagePath, productName, companyName, priceTotal, colorName,manufacturerRebate) " + "VALUES (?,?,?,?,?,?,?,?,?);";
                PreparedStatement preparedStatement = connection.prepareStatement(load);
                String category = "Speaker";
                Speaker speaker = mapping.getValue();
                preparedStatement.setString(1,speaker.getId());
                preparedStatement.setString(2,category);
                preparedStatement.setString(3,speaker.getRetailer());
                preparedStatement.setString(4,speaker.getImage());
                preparedStatement.setString(5,speaker.getName());
                preparedStatement.setString(6,speaker.getCompany());
                preparedStatement.setFloat(7,speaker.getPrice());
                preparedStatement.setString(8,speaker.getColor());
                preparedStatement.setFloat(9,speaker.getMafRebate());
                preparedStatement.execute();
            }

            for(Map.Entry<String,Headphone> mapping :headphones.entrySet())
            {
                String load = "INSERT INTO productdetails(orderId, productType, retailerName, imagePath, productName, companyName, priceTotal, colorName,manufacturerRebate) " + "VALUES (?,?,?,?,?,?,?,?,?);";
                PreparedStatement preparedStatement = connection.prepareStatement(load);
                String category = "Headphone";
                Headphone headphone = mapping.getValue();
                preparedStatement.setString(1,headphone.getId());
                preparedStatement.setString(2,category);
                preparedStatement.setString(3,headphone.getRetailer());
                preparedStatement.setString(4,headphone.getImage());
                preparedStatement.setString(5,headphone.getName());
                preparedStatement.setString(6,headphone.getCompany());
                preparedStatement.setFloat(7,headphone.getPrice());
                preparedStatement.setString(8,headphone.getColor());
                preparedStatement.setFloat(9,headphone.getMafRebate());
                preparedStatement.execute();
            }

            for(Map.Entry<String,WarrantyServlet> mapping :warranties.entrySet())
            {
                String load = "INSERT INTO productdetails(orderId, productType, retailerName, imagePath, productName, companyName, priceTotal, colorName,manufacturerRebate) " + "VALUES (?,?,?,?,?,?,?,?,?);";
                PreparedStatement preparedStatement = connection.prepareStatement(load);
                String category = "Warranty";
                WarrantyServlet warrantyServlet = mapping.getValue();
                preparedStatement.setString(1,warrantyServlet.getId());
                preparedStatement.setString(2,category);
                preparedStatement.setString(3,warrantyServlet.getRetailer());
                preparedStatement.setString(4,warrantyServlet.getImage());
                preparedStatement.setString(5,warrantyServlet.getName());
                preparedStatement.setString(6,warrantyServlet.getCompany());
                preparedStatement.setFloat(7,warrantyServlet.getPrice());
                preparedStatement.setString(8,warrantyServlet.getColor());
                preparedStatement.setFloat(9,warrantyServlet.getMafRebate());
                preparedStatement.execute();
            }

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteallProductsDatabase()
    {
        try
        {
            Connection connection = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportablesdatabase?autoReconnect=true&useSSL=false", "root", "root");
            String delete = "delete from productdetails;";
            Statement statement = connection.createStatement();
            statement.execute(delete);
            connection.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void insertProduct(String productId, String category, String retailerName, String path, String productName,
                                     String companyName, String condition, String priceTotal, String colorName,String manfRebate)
    {
        try
        {
            Connection connection = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportablesdatabase?autoReconnect=true&useSSL=false", "root", "root");
            String insert = "INSERT INTO productdetails(orderId, productType, retailerName, imagePath, productName, companyName, priceTotal, colorName, manufacturerRebate) " + "VALUES (?,?,?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1,productId);
            preparedStatement.setString(2,category);
            preparedStatement.setString(3,retailerName);
            preparedStatement.setString(4,path);
            preparedStatement.setString(5,productName);
            preparedStatement.setString(6,companyName);
            preparedStatement.setString(7,priceTotal);
            preparedStatement.setString(8,colorName);
            preparedStatement.setString(9,manfRebate);

            preparedStatement.execute();
            connection.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void updateProduct(String productId, String productCategory, String retailerName, String imagePath, String productName,
                                     String companyName, String condition, String totalPrice, String colorName)
    {
        try
        {
            Connection connection = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportablesdatabase?autoReconnect=true&useSSL=false", "root", "root");
            String update = "Update productdetails set retailerName=?, imagePath=?, productName=?, companyName=?, priceTotal=?, colorName=? where orderId=?";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1,retailerName);
            preparedStatement.setString(2,imagePath);
            preparedStatement.setString(3,productName);
            preparedStatement.setString(4,companyName);
            preparedStatement.setString(5,totalPrice);
            preparedStatement.setString(6,colorName);
            preparedStatement.setString(7,productId);
            preparedStatement.execute();

            connection.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void deleteProduct(String name)
    {
        try
        {
            Connection connection = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportablesdatabase?autoReconnect=true&useSSL=false", "root", "root");
            String delete = "DELETE FROM productdetails where productName=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setString(1,name);
            preparedStatement.execute();
            connection.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public static ArrayList<Object> fetchAllProducts()
    {
        ArrayList<Object> products = new ArrayList<Object>();

        HashMap<String, Smartphone> smartphones= new HashMap<String, Smartphone>();
        HashMap<String, Laptop> laptops= new HashMap<String, Laptop>();
        HashMap<String, Speaker> speakers= new HashMap<String, Speaker>();
        HashMap<String, Headphone> headphones= new HashMap<String, Headphone>();
        HashMap<String, Accessory> accessories= new HashMap<String, Accessory>();
        HashMap<String, Smartwatch> smartwatches= new HashMap<String, Smartwatch>();
        HashMap<String, WarrantyServlet> warranties= new HashMap<String, WarrantyServlet>();

        HashMap<String, Product> productHashMap= new HashMap<String, Product>();

        Smartphone smartphone;
        Laptop laptop;
        Speaker speaker;
        Headphone headphone;
        Smartwatch smartwatch;
        WarrantyServlet warranty;
        Accessory accessory;
        Product product;

        try
        {
            Connection conn = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportablesdatabase?autoReconnect=true&useSSL=false", "root", "root");
            Statement statement = conn.createStatement ();
            statement.executeQuery ("SELECT * FROM productdetails");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next ())
            {
                Integer changeId = resultSet.getInt("orderId");
                String id = changeId.toString();
                String productType = resultSet.getString("productType");
                String retailerName = resultSet.getString ("retailerName");
                String imagePath = resultSet.getString ("imagePath");
                String productName = resultSet.getString ("productName");
                String companyName = resultSet.getString ("companyName");
                Float totalPrice = resultSet.getFloat("priceTotal");
                String colorName = resultSet.getString ("colorName");
                product = new Product();
                product.setType(productType);
                product.setId(id);
                product.setRetailer(retailerName);
                product.setImage(imagePath);
                product.setName(productName);
                product.setCompany(companyName);
                product.setPrice(totalPrice);
                product.setColor(colorName);
                productHashMap.put(productName, product);
                if(productType.equals("Smartphone"))
                {
                    smartphone = new Smartphone();
                    smartphone.setId(id);
                    smartphone.setRetailer(retailerName);
                    smartphone.setImage(imagePath);
                    smartphone.setName(productName);
                    smartphone.setCompany(companyName);
                    smartphone.setPrice(totalPrice);
                    smartphone.setColor(colorName);
                    smartphones.put(productName, smartphone);
                }
                if(productType.equals("Speaker"))
                {
                    speaker = new Speaker();

                    speaker.setId(id);
                    speaker.setRetailer(retailerName);
                    speaker.setImage(imagePath);
                    speaker.setName(productName);
                    speaker.setCompany(companyName);
                    speaker.setPrice(totalPrice);
                    speaker.setColor(colorName);
                    speakers.put(productName, speaker);
                }
                if(productType.equals("Laptop"))
                {
                    laptop = new Laptop();
                    laptop.setId(id);
                    laptop.setRetailer(retailerName);
                    laptop.setImage(imagePath);
                    laptop.setName(productName);
                    laptop.setCompany(companyName);
                    laptop.setPrice(totalPrice);
                    laptop.setColor(colorName);
                    laptops.put(productName, laptop);
                }
                if(productType.equals("Headphone"))
                {
                    headphone = new Headphone();

                    headphone.setId(id);
                    headphone.setRetailer(retailerName);
                    headphone.setImage(imagePath);
                    headphone.setName(productName);
                    headphone.setCompany(companyName);
                    headphone.setPrice(totalPrice);
                    headphone.setColor(colorName);
                    headphones.put(productName, headphone);
                }
                if(productType.equals("Smartwatch"))
                {
                    smartwatch = new Smartwatch();
                    smartwatch.setId(id);
                    smartwatch.setRetailer(retailerName);
                    smartwatch.setImage(imagePath);
                    smartwatch.setName(productName);
                    smartwatch.setCompany(companyName);
                    smartwatch.setPrice(totalPrice);
                    smartwatch.setColor(colorName);
                    smartwatches.put(productName, smartwatch);
                }
                if(productType.equals("Accessory"))
                {
                    accessory = new Accessory();
                    accessory.setId(id);
                    accessory.setRetailer(retailerName);
                    accessory.setImage(imagePath);
                    accessory.setName(productName);
                    accessory.setCompany(companyName);
                    accessory.setPrice(totalPrice);
                    accessory.setColor(colorName);
                    accessories.put(productName, accessory);
                }
                if(productType.equals("Warranty"))
                {
                    warranty = new WarrantyServlet();
                    warranty.setId(id);
                    warranty.setRetailer(retailerName);
                    warranty.setImage(imagePath);
                    warranty.setName(productName);
                    warranty.setCompany(companyName);
                    warranty.setPrice(totalPrice);
                    warranty.setColor(colorName);
                    warranties.put(productName, warranty);
                }

            }

            products.add(smartphones);
            products.add(speakers);
            products.add(laptops);
            products.add(headphones);
            products.add(warranties);
            products.add(smartwatches);
            products.add(accessories);
            products.add(productHashMap);
            resultSet.close ();
            statement.close ();
            conn.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return products;
    }

    public static void insertIntoregisterTable(String fName, String lName, String emailId, String password)
    {
        try
        {
            Connection connection = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportablesdatabase?autoReconnect=true&useSSL=false", "root", "root");
            String insert = "INSERT INTO registerdetails(fName,lName,emailId, password) " + "VALUES (?,?,?,?);";
            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setString(1,fName);
            statement.setString(2,lName);
            statement.setString(3,emailId);
            statement.setString(4,password);
            statement.execute();
            connection.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public static HashMap<String,CustomerDetails> fetchCustomerDetails()
    {
        CustomerDetails customerDetails = null;
        HashMap<String,CustomerDetails> customerDetailsHashMap = new HashMap<String, CustomerDetails>();

        try
        {
            Connection connection = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportablesdatabase?autoReconnect=true&useSSL=false", "root", "root");
            Statement statement = connection.createStatement ();
            statement.executeQuery ("SELECT fName, lName, emailId, password FROM registerdetails");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next ())
            {
                String fName = resultSet.getString("fName");
                String lName = resultSet.getString ("lName");
                String email = resultSet.getString ("emailId");
                String password = resultSet.getString ("password");
                customerDetails = new CustomerDetails(fName, lName, email, password);
                customerDetailsHashMap.put(email, customerDetails);
            }
            resultSet.close ();
            statement.close ();
            connection.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return customerDetailsHashMap;
    }

    public static HashMap<String,String> getAdminDetails()
    {
        HashMap<String,String> admininstratorDetails = new HashMap<String, String>();
        try
        {
            Connection connection = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportablesdatabase?autoReconnect=true&useSSL=false", "root", "root");
            Statement statement = connection.createStatement ();
            statement.executeQuery ("SELECT email, pass FROM adminsdetails");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next ())
            {
                String email = resultSet.getString("email");
                String pass = resultSet.getString ("pass");
                admininstratorDetails.put(email, pass);
            }
            resultSet.close ();
            statement.close ();
            connection.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return admininstratorDetails;
    }

    public static void insertOrder(String name, String orderId, float price,int quantity, String dateofOrder, String dateofdelivery, String customerEmailId, String shippingAddress)
    {
        try
        {
            Connection connection = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportablesdatabase?autoReconnect=true&useSSL=false", "root", "root");
            String insert = "INSERT INTO orderdetail(orderId,name,price,quantity,dateofOrder, dateofdelivery, customerEmailId, shippingAddress) " + "VALUES (?,?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1,orderId);
            preparedStatement.setString(2,name);
            preparedStatement.setFloat(3,price);
            preparedStatement.setInt(4,quantity);
            preparedStatement.setString(5,dateofOrder);
            preparedStatement.setString(6,dateofdelivery);
            preparedStatement.setString(7,customerEmailId);
            preparedStatement.setString(8,shippingAddress);
            preparedStatement.execute();
            connection.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void calculateTotal(String id, String dateofOrder, String dateofdelivery, float totalPrice, String customerEmailId, String shippingAddress)
    {
        try
        {
            Connection connection = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportablesdatabase?autoReconnect=true&useSSL=false", "root", "root");
            String insert = "INSERT INTO ordertotal(orderId, dateofOrder, dateofdelivery, totalPrice, customerEmailId, shippingAddress) " + "VALUES (?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(insert);

            preparedStatement.setString(1,id);
            preparedStatement.setString(2,dateofOrder);
            preparedStatement.setString(3,dateofdelivery);
            preparedStatement.setFloat(4,totalPrice);
            preparedStatement.setString(5,customerEmailId);
            preparedStatement.setString(6,shippingAddress);
            preparedStatement.execute();
            connection.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static HashMap<String,OrderItem> getOrderDetails()
    {
        OrderItem orderItem = null;
        HashMap<String,OrderItem> orderDetails = new HashMap<String, OrderItem>();
        try
        {
            Connection connection = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportablesdatabase?autoReconnect=true&useSSL=false", "root", "root");
            Statement statement = connection.createStatement ();
            statement.executeQuery ("SELECT orderId, name,price,quantity, dateofOrder, dateofdelivery, customerEmailId, shippingAddress FROM orderdetail");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next ())
            {
                String orderId = resultSet.getString("orderId");
                String name = resultSet.getString ("name");
                Float price = resultSet.getFloat ("price");
                Integer quantity = resultSet.getInt ("quantity");
                String dateofOrder = resultSet.getString("dateofOrder");
                String dateofdelivery = resultSet.getString ("dateofdelivery");
                String customerEmailId = resultSet.getString ("customerEmailId");
                String shippingAddress = resultSet.getString ("shippingAddress");
                orderItem = new OrderItem(orderId,name, price,quantity, dateofOrder, dateofdelivery, customerEmailId, shippingAddress);
                orderDetails.put(customerEmailId+orderId+name, orderItem);
            }
            resultSet.close ();
            statement.close ();
            connection.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return orderDetails;
    }

    public static void updateOrder(String productName, String orderId, float price, String dateOfdelivery, String address)
    {
        try
        {
            Connection connection = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportablesdatabase?autoReconnect=true&useSSL=false", "root", "root");
            String insert = "Update orderdetail set name=?, price=?, dateofdelivery=?, shippingAddress=? where orderId=?";
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1,productName);
            preparedStatement.setFloat(2,price);
            preparedStatement.setString(3,dateOfdelivery);
            preparedStatement.setString(4,address);
            preparedStatement.setString(5,orderId);
            preparedStatement.execute();
            connection.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void cancelOrder(String orderId, String name, String email)
    {
        try
        {
            Connection connection = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportablesdatabase?autoReconnect=true&useSSL=false", "root", "root");
            String s = "DELETE FROM orderdetail where orderId=? and name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(s);
            preparedStatement.setString(1,orderId);
            preparedStatement.setString(2,name);
            preparedStatement.execute();
            connection.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }


    public static LinkedHashMap<String, ArrayList<Object>> TopFiveMostSoldProducts()
    {
        LinkedHashMap<String, ArrayList<Object>> top5SoldProducts = new LinkedHashMap<String, ArrayList<Object>>();

        try
        {
            Connection connection = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportablesdatabase?autoReconnect=true&useSSL=false", "root", "root");
            Statement statement = connection.createStatement ();
            statement.executeQuery ("SELECT name, price, SUM(quantity) as sumItemQty FROM orderdetail GROUP BY name order by sumItemQty desc limit 5;");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next ())
            {
                String name = resultSet.getString("name");
                Float price = resultSet.getFloat ("price");
                Integer sum = resultSet.getInt ("sumItemQty");

                ArrayList<Object> list = new ArrayList<Object>();
                list.add(name);
                list.add(price);
                list.add(sum);

                top5SoldProducts.put(name, list);
            }
            resultSet.close ();
            statement.close ();
            connection.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return top5SoldProducts;

    }

    //=============================================================================================//

    public static LinkedHashMap<String, ArrayList<Object>> AllProducts()
    {
        LinkedHashMap<String, ArrayList<Object>> inventory = new LinkedHashMap<String, ArrayList<Object>>();

        try
        {
            Connection connection = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportablesdatabase?autoReconnect=true&useSSL=false", "root", "root");
            Statement statement = connection.createStatement ();
            statement.executeQuery ("SELECT productName,productType, priceTotal, totalAvailable FROM productdetails GROUP BY productName;");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next ())
            {
                String name = resultSet.getString("productName");
                String type = resultSet.getString("productType");
                String price = resultSet.getString ("priceTotal");
                String sum = resultSet.getString ("totalAvailable");

                ArrayList<Object> list = new ArrayList<Object>();
                list.add(name);
                list.add(type);
                list.add(price);
                list.add(sum);

                inventory.put(name, list);
            }
            resultSet.close ();
            statement.close ();
            connection.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return inventory;

    }

    public static LinkedHashMap<String, ArrayList<Object>> OnSaleProducts()
    {
        LinkedHashMap<String, ArrayList<Object>> sale = new LinkedHashMap<String, ArrayList<Object>>();

        try
        {
            Connection connection = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportablesdatabase?autoReconnect=true&useSSL=false", "root", "root");
            Statement statement = connection.createStatement ();
            statement.executeQuery ("SELECT productName,productType, priceTotal, onSale FROM productdetails where onsale = \"yes\" GROUP BY productName;");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next ())
            {
                String name = resultSet.getString("productName");
                String type = resultSet.getString("productType");
                String price = resultSet.getString ("priceTotal");
                String sum = resultSet.getString ("onSale");

                ArrayList<Object> list = new ArrayList<Object>();
                list.add(name);
                list.add(type);
                list.add(price);
                list.add(sum);

                sale.put(name, list);
            }
            resultSet.close ();
            statement.close ();
            connection.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return sale;

    }


    //=====================================================================================================//

    public static LinkedHashMap<String, ArrayList<Object>> ProductsWithManufacturerRebate()
    {
        LinkedHashMap<String, ArrayList<Object>> rebate = new LinkedHashMap<String, ArrayList<Object>>();

        try
        {
            Connection connection = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportablesdatabase?autoReconnect=true&useSSL=false", "root", "root");
            Statement statement = connection.createStatement ();
            statement.executeQuery ("SELECT productName,productType, priceTotal, manufacturerRebate FROM productdetails where (manufacturerRebate != 'null')  GROUP BY productName;");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next ())
            {
                String name = resultSet.getString("productName");
                String type = resultSet.getString("productType");
                String price = resultSet.getString ("priceTotal");
                String sum = resultSet.getString ("manufacturerRebate");

                ArrayList<Object> list = new ArrayList<Object>();
                list.add(name);
                list.add(type);
                list.add(price);
                list.add(sum);

                rebate.put(name, list);
            }
            resultSet.close ();
            statement.close ();
            connection.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return rebate;

    }

    //================================================================================================
    public static LinkedHashMap<String, ArrayList<Object>> AllProductsSold()
    {
        LinkedHashMap<String, ArrayList<Object>> productsSold = new LinkedHashMap<String, ArrayList<Object>>();

        try
        {
            Connection connection = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportablesdatabase?autoReconnect=true&useSSL=false", "root", "root");
            Statement statement = connection.createStatement ();
            statement.executeQuery ("SELECT name, price, SUM(quantity) as sumItemQty, SUM(price) as sumItemPrice FROM orderdetail GROUP BY name order by sumItemQty desc;");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next ())
            {
                String name = resultSet.getString("name");
                Float price = resultSet.getFloat ("price");
                Integer sum = resultSet.getInt ("sumItemQty");
                Float priceTotal = resultSet.getFloat ("sumItemPrice");


                ArrayList<Object> list = new ArrayList<Object>();
                list.add(name);
                list.add(price);
                list.add(sum);
                list.add(priceTotal);

                productsSold.put(name, list);
            }
            resultSet.close ();
            statement.close ();
            connection.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return productsSold;

    }

    //=========================================================================================================

    public static LinkedHashMap<String, ArrayList<Object>> TotalDailySales()
    {
        LinkedHashMap<String, ArrayList<Object>> dailySales = new LinkedHashMap<String, ArrayList<Object>>();

        try
        {
            Connection connection = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportablesdatabase?autoReconnect=true&useSSL=false", "root", "root");
            Statement statement = connection.createStatement ();
            statement.executeQuery ("SELECT DATE(STR_TO_DATE(dateofOrder,'%m/%d/%Y %H:%i:%s')) as DATE, SUM(price) as TotalPrice FROM orderdetail GROUP BY DATE(STR_TO_DATE(dateofOrder,'%m/%d/%Y %H:%i:%s'));");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next ())
            {
                String date = resultSet.getString("DATE");
                Float price = resultSet.getFloat ("TotalPrice");


                ArrayList<Object> list = new ArrayList<Object>();
                list.add(date);
                list.add(price);

                dailySales.put(date, list);
            }
            resultSet.close ();
            statement.close ();
            connection.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return dailySales;

    }

    //====================================================================================================================
    public static LinkedHashMap<String, ArrayList<Object>> ProductnamesBarchartAndTotalnumberofItems()
    {
        LinkedHashMap<String, ArrayList<Object>> barchart = new LinkedHashMap<String, ArrayList<Object>>();

        try
        {
            Connection connection = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportablesdatabase?autoReconnect=true&useSSL=false", "root", "root");
            Statement statement = connection.createStatement ();
            statement.executeQuery ("SELECT productName, totalavailable FROM productdetails GROUP BY productName;");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next ())
            {
                String name = resultSet.getString("productName");
                String itemsAvailable = resultSet.getString ("totalAvailable");


                ArrayList<Object> list = new ArrayList<Object>();
                list.add(name);
                list.add(itemsAvailable);

                barchart.put(name, list);
            }
            resultSet.close ();
            statement.close ();
            connection.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return barchart;

    }

    //===============================================================================================================


    public static LinkedHashMap<String, ArrayList<Object>> ProductnamesBarchartAndTotalSales()
    {
        LinkedHashMap<String, ArrayList<Object>> barchart = new LinkedHashMap<String, ArrayList<Object>>();

        try
        {
            Connection connection = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportablesdatabase?autoReconnect=true&useSSL=false", "root", "root");
            Statement statement = connection.createStatement ();
            statement.executeQuery ("SELECT name, SUM(price) as totalSales FROM orderdetail GROUP BY name desc;");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next ())
            {
                String productName = resultSet.getString("name");
                String totalSales = resultSet.getString ("totalSales");


                ArrayList<Object> list = new ArrayList<Object>();
                list.add(productName);
                list.add(totalSales);

                barchart.put(productName, list);
            }
            resultSet.close ();
            statement.close ();
            connection.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return barchart;

    }




}

