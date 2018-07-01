

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class OrderConfirmationServlet extends HttpServlet {

	Order order;
	OrderDataStore orderDataStore;
	HashMap<String,Order> orderHashMap;

	public void init()
	{
		orderDataStore = new OrderDataStore();
		orderHashMap = new HashMap<String, Order>();
	}

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String fName = (String)session.getAttribute("firstName");

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		pw.println("<!doctype html><html><head>" +
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		pw.println("<title>Smart Portables</title>" +
				"<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");

		pw.println("<body onload='init()'>" +
				"<div id=\"container\"><header><h1><a href=\"/\">Smart<span>Portables</span></a></h1>");

		pw.println("</header>");

		if(fName != null && !fName.isEmpty())
		{

			pw.println("<nav>" +
					"<ul>" +
					"<li class=\"start selected\"><a href=\"HomeServlet\">Welcome "+fName+"</a></li>");
		}
		else{
			pw.println("<nav><ul><li class=\"start selected\"><a href=\"HomeServlet\">Home</a></li>");
		}

		pw.println("<li class=\"\"><a href=\"DataServlet?productType=Smartphones\">SmartPhones</a></li>");
		pw.println("<li><a href=\"DataServlet?productType=Speakers\">Speakers</a></li>");
		pw.println("<li><a href=\"DataServlet?productType=Laptops\">Laptops</a></li>");
		pw.println("<li><a href=\"DataServlet?productType=Accessories\">Accessories</a></li>");

		if(fName != null && !fName.isEmpty())
		{
			pw.println("<li><a href=\"ViewCartDetailServlet\">Cart</a></li>");
			pw.println("<li><a href=\"ViewOrders\">Your Orders</a></li>");
			pw.println("<li><a href=\"LogoutServlet\">Logout</a></li>");
		}
		else
		{
			pw.println("<li><a href=\"LoginServlet\">Login</a></li>");
			pw.println("<li><a href=\"registration.html\">Signup</a></li>");
			pw.println("<li><a href=\"ViewCartDetailServlet\">Cart</a></li>");
		}


		pw.println("</ul></nav>");

		String deliveryAdress = request.getParameter("shippingAddress");
		Random randomValue = new Random();
		int firstValue = 1;
		int lastValue = 1000;
		int value = randomValue.nextInt(lastValue-firstValue) + firstValue;
		String id = "SP#"+value;
		String confirmationNo = "Confirmation# "+value;
		DateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date1 = new Date();
		String dateOfOrder = simpleDateFormat.format(date1).toString();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 14);
		Date date = calendar.getTime();
		String DATE_FORMAT = "MM/dd/yyyy";
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(DATE_FORMAT);
		String dateOfdelivery = simpleDateFormat1.format(date);
		Cart cartFunctionality;
		cartFunctionality = (Cart) session.getAttribute("cart");
		HashMap<String, List<Object>> cartItems = cartFunctionality.getCartItems();
		String customerEmailId=(String)session.getAttribute("userid");
		Float totalAmount=cartFunctionality.getTotalAmount();
		ArrayList<String> orderItems = new ArrayList<String>();
		String orderDetails;
		String productName;
		float productPrice;
		int productQty;

		for(Map.Entry<String, List<Object>> listEntry : cartItems.entrySet()){

			String key = listEntry.getKey();
			List<Object> values = listEntry.getValue();
			productName = (String)values.get(0);
			productPrice = (Float)values.get(1);
			productQty = (Integer)values.get(2);
			System.out.println(productQty);
			MySqlDataStoreUtilities.insertOrder(productName, id, totalAmount,productQty, dateOfOrder, dateOfdelivery, customerEmailId, deliveryAdress);
			orderDetails = (String)values.get(0);
			orderItems.add(orderDetails);
		}
		MySqlDataStoreUtilities.calculateTotal(id, dateOfOrder, dateOfdelivery, totalAmount,customerEmailId, deliveryAdress);
		order = new Order(id, confirmationNo, customerEmailId, dateOfOrder, dateOfdelivery, deliveryAdress, totalAmount, orderItems);

		orderHashMap = orderDataStore.getOrderHashMap();
		orderHashMap.put(order.getorderId(), order);
		orderDataStore.writeOrderHashMap(orderHashMap);
		session.removeAttribute("cart");
		pw.println("<h3><br><br>Your Order with Order Number "+id+" has been Placed Succesfully. The order will be delivered around " + dateOfdelivery + " </h3><br><br>");
		pw.close();

	}

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	}

}