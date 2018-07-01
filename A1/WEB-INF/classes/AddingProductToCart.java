

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;



public class AddingProductToCart extends HttpServlet {
	
	
  public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
      
		response.setContentType("text/html;charset=UTF-8");
		
        HttpSession httpSession = request.getSession();
		
         Cart cartFunctionality;
        cartFunctionality = (Cart) httpSession.getAttribute("cart");
		
        if(cartFunctionality == null){
          cartFunctionality = new Cart();
          httpSession.setAttribute("cart", cartFunctionality);
        }
		
		String nameOfProduct = request.getParameter("productName");
        Float priceOfProduct = Float.parseFloat(request.getParameter("price"));
		Integer quantityOfProduct = Integer.parseInt(request.getParameter("quantity"));
		String imageOfProduct = request.getParameter("image");
		
        cartFunctionality.addToCart(nameOfProduct, nameOfProduct, priceOfProduct, quantityOfProduct, imageOfProduct);
        httpSession.setAttribute("cart", cartFunctionality);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("ViewCartDetailServlet");
		requestDispatcher.forward(request,response);
	
}
}