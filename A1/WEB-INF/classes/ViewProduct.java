

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import java.util.*;


public class ViewProduct extends HttpServlet {

    ArrayList<Object> products;
    HashMap<String, Smartphone> phones;
    HashMap<String, Laptop> lappy;
    HashMap<String, Speaker> speakers;
    HashMap<String, Smartwatch> watches;
    HashMap<String, Accessory> accessory;

    ProductSaxParser productSaxParser = new ProductSaxParser();

    void DataXMLFile() {
        try {
            products = productSaxParser.loadDataStore();

            phones = (HashMap<String, Smartphone>) products.get(0);
            lappy = (HashMap<String, Laptop>) products.get(1);
            accessory = (HashMap<String, Accessory>) products.get(2);
            watches = (HashMap<String, Smartwatch>) products.get(3);
            speakers = (HashMap<String, Speaker>) products.get(4);

        } catch (Exception E) {
            System.out.println("Exception");
        }
    }


    public void init() throws ServletException {


    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DataXMLFile();

        PrintWriter pw = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        String firstName = (String) session.getAttribute("firstName");


        pw.println("<!doctype html>" +
                "<html>" +
                "<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
        pw.println("<html lang='en'>" + "<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
        pw.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
        pw.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>");
        pw.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js'></script>");
        pw.println("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>");
        pw.println();

        pw.println("<title>Smart Portables</title>" +
                "<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");

        pw.println("<body onload='init()'>" +
                "<div id=\"container\">" +
                "<header><h1><a href=\"/\">Smart<span>Portables</span></a></h1>");

        pw.println("</header>");

        if (firstName != null && !firstName.isEmpty()) {

            pw.println("<nav>" +
                    "<ul>" +
                    "<li class=\"start selected\"><a href=\"HomeServlet\">Welcome "+firstName+"</a></li>");
        } else {
            pw.println("<nav><ul><li class=\"start selected\"><a href=\"HomeServlet\">Home</a></li>");
        }


        pw.println("<li >" +
                "<a href=\"DataServlet?productType=Smartphones\">SmartPhones</a></li>");
        pw.println("<li>" +
                "<a href=\"DataServlet?productType=Speakers\">Speakers</a></li>");
        pw.println("<li>" +
                "<a href=\"DataServlet?productType=Laptops\">Laptops</a></li>");
        pw.println("<li>" +
                "<a href=\"DataServlet?productType=Accessories\">Accessories</a></li>");

        if (firstName != null && !firstName.isEmpty()) {
            pw.println("<li>" +
                    "<a href=\"ViewCartDetailServlet\">Cart</a></li>");
            pw.println("<li>" +
                    "<a href=\"ViewOrders\">Your Orders</a></li>");
            pw.println("<li>" +
                    "<a href=\"LogoutServlet\">Logout</a></li>");
        } else {
            pw.println("<li>" +
                    "<a href=\"LoginServlet\">Login</a></li>");
            pw.println("<li>" +
                    "<a href=\"registration.html\">Signup</a></li>");
            pw.println("<li>" +
                    "<a href=\"ViewCartDetailServlet\">Cart</a></li>");
        }

        pw.println("</ul></nav>");

//==========================================display area code======================================

pw.println("<div id=\"body\">");
        pw.println("<section id=\"content\">");
        pw.println("<br><br><br><br>");
        String typeOfProduct = request.getParameter("productType");
        String nameOfProduct = request.getParameter("productName");
        String userName = (String) session.getAttribute("userid");
        String productImage = request.getParameter("image");
        String productPrice = request.getParameter("price");
        String productColor = request.getParameter("color");
        String productCondition = request.getParameter("condition");
        String productCompany = request.getParameter("company");
        String productRetailer = request.getParameter("retailer");
        String manf = request.getParameter("manf");
        String ret = request.getParameter("ret");
        String newPrice = request.getParameter("newPrice");



        pw.println("<table border=\"1\" style=\"width:100%\" style=\"height:100%\" border=\"2\" bordercolor=\"#ff0000\" cellspacing=\"0\" cellpadding=\"0\">");
        pw.println("<tr><th width=\"30%\">");
        pw.println("<a href=\"\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
        pw.println(productImage);
        pw.println("\" /></a>");
        pw.println("</th>");
        pw.println("<th width=\"40%\"><table border=\"2\" bordercolor=\"#ff0000\"><tr><th width=\"40\"><b>");
        pw.println(nameOfProduct);
        pw.println("</b></th></tr><tr><th width=\"40\"><b>Company:</b>");
        pw.println(productCompany);
        pw.println("</b></th></tr><tr><th width=\"40\"><b>Color:</b>");
        pw.println(productColor);
        pw.println("</th></tr><tr><th><b>Condition:</b>");
        pw.println(productCondition);
        pw.println("</th></tr><tr><th><b>Manufacturer Rebates:</b>");
        pw.println(manf + "%");
        pw.println("</th></tr><tr><th><b>Retailer Discounts:</b>");
        pw.println(ret + "%");
        pw.println("</th></tr></table></th>");
        pw.println("<th width=\"30%\"><table border=\"2\" bordercolor=\"#ff0000\"><tr><th><b>Price:");
        pw.println(productPrice+"$");
        pw.println("<tr><th><b>Price after Discount:");
        pw.println(newPrice+"$");

        pw.println("<tr><th><form method = 'get' action = 'AddingProductToCart'>");
        pw.println("<input class = 'formbutton' type = 'submit' name = '" + nameOfProduct + "' value = 'Add to Cart'>");
        pw.println("<input type='hidden' name='productName' value='" + nameOfProduct + "'>");
        pw.println("<input type='hidden' name='image' value='" + productImage + "'>");
        pw.println("<input type='hidden' name='price' value='" + newPrice + "'>");
        pw.println("<input type='hidden' name='quantity' value='" + 1 + "'>");
        pw.println("</form></td></tr>");

        pw.println("</table></th></tr></table>");
		
		pw.println("<br><br><br><br><br>");

//======================================================courousel==================================
        pw.println("<div class='bs-example'>");
        pw.println("<div id='myCarousel' class='carousel slide' data-ride='carousel'>");
        pw.println("<ol class='carousel-indicators'>");
		pw.print("<li data-target='#myCarousel' data-slide-to='1'></li>");
        pw.println("</ol>");
		
		
		
		
        pw.println("<div class='carousel-inner'>" +
                "<div class='item active'>");

       
        pw.println("</div>");

        //====================================second=============
        if(typeOfProduct.equals("Smartphones")) {

            for (Map.Entry<String, Accessory> mapping : accessory.entrySet()) {
                Accessory accessory = mapping.getValue();

                if (productCompany.equals(accessory.getCompany())) {

                    float manfRebate = accessory.getMafRebate();
                    float retDiscount = accessory.getReDiscount();
                    float originalPrice = accessory.getPrice();

                    float changePrice = (((100 - manfRebate) / 100) * originalPrice) * ((100 - retDiscount) / 100);
                    changePrice = Math.round(changePrice * 100) / 100;

                    pw.println("<div class='item'>");

                    pw.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#ff0000\" cellspacing=\"0\" cellpadding=\"0\">");
                    pw.println("<tr><th width=\"30%\">");
                    pw.println("<a href=\"\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
                    pw.println(accessory.getImage());
                    pw.println("\" /></a>");
                    pw.println("</th>");
                    pw.println("<th width=\"40%\"><table border=\"1\"><tr><th width=\"40\"><b>");
                    pw.println(accessory.getName());
                    pw.println("</b></th></tr><tr><th width=\"40\"><b>Company:</b>");
                    pw.println(accessory.getCompany());
                    pw.println("</b></th></tr><tr><th width=\"40\"><b>Color:</b>");
                    pw.println(accessory.getColor());
                    pw.println("</th></tr><tr><th><b>Condition:</b>");
                    pw.println(accessory.getCondition());
                    pw.println("</th></tr><tr><th><b>Manufacturer Rebates:</b>");
                    pw.println(manfRebate + "%");
                    pw.println("</th></tr><tr><th><b>Retailer Discounts:</b>");
                    pw.println(retDiscount + "%");
                    pw.println("</th></tr></table border=\"1\"></th>");
                    pw.println("<th width=\"30%\"><table><tr><th><b>Price:");
                    pw.println(originalPrice+"$");
                    pw.println("<tr><th><b>Price after Discount:");
                    pw.println(changePrice+"$");

                    pw.println("<tr><th><form method = 'get' action = 'AddingProductToCart'>");
                    pw.println("<input class = 'formbutton' type = 'submit' name = '" + accessory.getName() + "' value = 'Add to Cart'>");
                    pw.println("<input type='hidden' name='productName' value='" + accessory.getName() + "'>");
                    pw.println("<input type='hidden' name='image' value='" + accessory.getImage() + "'>");
                    pw.println("<input type='hidden' name='price' value='" + changePrice + "'>");
                    pw.println("<input type='hidden' name='quantity' value='" + 1 + "'>");
                    pw.println("</form></th></tr>");
                    pw.println("</table></th></tr></table>");
                    pw.println("</div>");
                }
            }
            //=========================================================



        }else
        if(typeOfProduct.equals("Speakers")) {

            for (Map.Entry<String, Accessory> mapping : accessory.entrySet()) {
                Accessory accessory = mapping.getValue();

                if (productCompany.equals(accessory.getCompany())) {

                    float manfRebate = accessory.getMafRebate();
                    float retDiscount = accessory.getReDiscount();
                    float originalPrice = accessory.getPrice();

                    float changePrice = (((100 - manfRebate) / 100) * originalPrice) * ((100 - retDiscount) / 100);
                    changePrice = Math.round(changePrice * 100) / 100;

                    pw.println("<div class='item'>");

                    pw.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#ff0000\" cellspacing=\"0\" cellpadding=\"0\">");
                    pw.println("<tr><th width=\"30%\">");
                    pw.println("<a href=\"\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
                    pw.println(accessory.getImage());
                    pw.println("\" /></a>");
                    pw.println("</th>");
                    pw.println("<th width=\"40%\"><table border=\"1\"><tr><th width=\"40\"><b>");
                    pw.println(accessory.getName());
                    pw.println("</b></th></tr><tr><th width=\"40\"><b>Company:</b>");
                    pw.println(accessory.getCompany());
                    pw.println("</b></th></tr><tr><th width=\"40\"><b>Color:</b>");
                    pw.println(accessory.getColor());
                    pw.println("</th></tr><tr><th><b>Condition:</b>");
                    pw.println(accessory.getCondition());
                    pw.println("</th></tr><tr><th><b>Manufacturer Rebates:</b>");
                    pw.println(manfRebate + "%");
                    pw.println("</th></tr><tr><th><b>Retailer Discounts:</b>");
                    pw.println(retDiscount + "%");
                    pw.println("</th></tr></table border=\"1\"></th>");
                    pw.println("<th width=\"30%\"><table><tr><th><b>Price:");
                    pw.println(originalPrice+"$");
                    pw.println("<tr><th><b>Price after Discount:");
                    pw.println(changePrice+"$");

                    pw.println("<tr><th><form method = 'get' action = 'AddingProductToCart'>");
                    pw.println("<input class = 'formbutton' type = 'submit' name = '" + accessory.getName() + "' value = 'Add to Cart'>");
                    pw.println("<input type='hidden' name='productName' value='" + accessory.getName() + "'>");
                    pw.println("<input type='hidden' name='image' value='" + accessory.getImage() + "'>");
                    pw.println("<input type='hidden' name='price' value='" + changePrice + "'>");
                    pw.println("<input type='hidden' name='quantity' value='" + 1 + "'>");
                    pw.println("</form></th></tr>");
                    pw.println("</table></th></tr></table>");
                    pw.println("</div>");
                }
            }
        }
            //====================================================================================
        
        else if(typeOfProduct.equals("Laptops"))
        {
            for (Map.Entry<String, Accessory> mapping : accessory.entrySet()) {
                Accessory accessory = mapping.getValue();

                if (productCompany.equals(accessory.getCompany())) {

                    float manfRebate = accessory.getMafRebate();
                    float retDiscount = accessory.getReDiscount();
                    float originalPrice = accessory.getPrice();

                    float changePrice = (((100 - manfRebate) / 100) * originalPrice) * ((100 - retDiscount) / 100);
                    changePrice = Math.round(changePrice * 100) / 100;

                    pw.println("<div class='item'>");

                    pw.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#ff0000\" cellspacing=\"0\" cellpadding=\"0\">");
                    pw.println("<tr><th width=\"30%\">");
                    pw.println("<a href=\"\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
                    pw.println(accessory.getImage());
                    pw.println("\" /></a>");
                    pw.println("</th>");
                    pw.println("<th width=\"40%\"><table border=\"1\"><tr><th width=\"40\"><b>");
                    pw.println(accessory.getName());
                    pw.println("</b></th></tr><tr><th width=\"40\"><b>Company:</b>");
                    pw.println(accessory.getCompany());
                    pw.println("</b></th></tr><tr><th width=\"40\"><b>Color:</b>");
                    pw.println(accessory.getColor());
                    pw.println("</th></tr><tr><th><b>Condition:</b>");
                    pw.println(accessory.getCondition());
                    pw.println("</th></tr><tr><th><b>Manufacturer Rebates:</b>");
                    pw.println(manfRebate + "%");
                    pw.println("</th></tr><tr><th><b>Retailer Discounts:</b>");
                    pw.println(retDiscount + "%");
                    pw.println("</th></tr></table border=\"1\"></th>");
                    pw.println("<th width=\"30%\"><table><tr><th><b>Price:");
                    pw.println(originalPrice+"$");
                    pw.println("<tr><th><b>Price after Discount:");
                    pw.println(changePrice+"$");

                    pw.println("<tr><th><form method = 'get' action = 'AddingProductToCart'>");
                    pw.println("<input class = 'formbutton' type = 'submit' name = '" + accessory.getName() + "' value = 'Add to Cart'>");
                    pw.println("<input type='hidden' name='productName' value='" + accessory.getName() + "'>");
                    pw.println("<input type='hidden' name='image' value='" + accessory.getImage() + "'>");
                    pw.println("<input type='hidden' name='price' value='" + changePrice + "'>");
                    pw.println("<input type='hidden' name='quantity' value='" + 1 + "'>");
                    pw.println("</form></th></tr>");
                    pw.println("</table></th></tr></table>");
                    pw.println("</div>");
                }
            }
        }
        
        else if(typeOfProduct.equals("Smartwatch"))
        {
            for (Map.Entry<String, Accessory> mapping : accessory.entrySet()) {
                Accessory accessory = mapping.getValue();

                if (productCompany.equals(accessory.getCompany())) {

                    float manfRebate = accessory.getMafRebate();
                    float retDiscount = accessory.getReDiscount();
                    float originalPrice = accessory.getPrice();

                    float changePrice = (((100 - manfRebate) / 100) * originalPrice) * ((100 - retDiscount) / 100);
                    changePrice = Math.round(changePrice * 100) / 100;

                    pw.println("<div class='item'>");

                    pw.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#ff0000\" cellspacing=\"0\" cellpadding=\"0\">");
                    pw.println("<tr><th width=\"30%\">");
                    pw.println("<a href=\"\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
                    pw.println(accessory.getImage());
                    pw.println("\" /></a>");
                    pw.println("</th>");
                    pw.println("<th width=\"40%\"><table border=\"1\"><tr><th width=\"40\"><b>");
                    pw.println(accessory.getName());
                    pw.println("</b></th></tr><tr><th width=\"40\"><b>Company:</b>");
                    pw.println(accessory.getCompany());
                    pw.println("</b></th></tr><tr><th width=\"40\"><b>Color:</b>");
                    pw.println(accessory.getColor());
                    pw.println("</th></tr><tr><th><b>Condition:</b>");
                    pw.println(accessory.getCondition());
                    pw.println("</th></tr><tr><th><b>Manufacturer Rebates:</b>");
                    pw.println(manfRebate + "%");
                    pw.println("</th></tr><tr><th><b>Retailer Discounts:</b>");
                    pw.println(retDiscount + "%");
                    pw.println("</th></tr></table border=\"1\"></th>");
                    pw.println("<th width=\"30%\"><table><tr><th><b>Price:");
                    pw.println(originalPrice+"$");
                    pw.println("<tr><th><b>Price after Discount:");
                    pw.println(changePrice+"$");

                    pw.println("<tr><th><form method = 'get' action = 'AddingProductToCart'>");
                    pw.println("<input class = 'formbutton' type = 'submit' name = '" + accessory.getName() + "' value = 'Add to Cart'>");
                    pw.println("<input type='hidden' name='productName' value='" + accessory.getName() + "'>");
                    pw.println("<input type='hidden' name='image' value='" + accessory.getImage() + "'>");
                    pw.println("<input type='hidden' name='price' value='" + changePrice + "'>");
                    pw.println("<input type='hidden' name='quantity' value='" + 1 + "'>");
                    pw.println("</form></th></tr>");
                    pw.println("</table></th></tr></table>");
                    pw.println("</div>");
                }
            }
        }
        
        //=====================================================================
        
        else if(typeOfProduct.equals("Accessories"))
        {
            for (Map.Entry<String, Accessory> mapping : accessory.entrySet()) {
                Accessory accessory = mapping.getValue();

                if (productCompany.equals(accessory.getCompany())) {

                    float manfRebate = accessory.getMafRebate();
                    float retDiscount = accessory.getReDiscount();
                    float originalPrice = accessory.getPrice();

                    float changePrice = (((100 - manfRebate) / 100) * originalPrice) * ((100 - retDiscount) / 100);
                    changePrice = Math.round(changePrice * 100) / 100;

                    pw.println("<div class='item'>");

                    pw.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#ff0000\" cellspacing=\"0\" cellpadding=\"0\">");
                    pw.println("<tr><th width=\"30%\">");
                    pw.println("<a href=\"\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
                    pw.println(accessory.getImage());
                    pw.println("\" /></a>");
                    pw.println("</th>");
                    pw.println("<th width=\"40%\"><table border=\"1\"><tr><td width=\"40\"><b>");
                    pw.println(accessory.getName());
                    pw.println("</b></th></tr><tr><td width=\"40\"><b>Company:</b>");
                    pw.println(accessory.getCompany());
                    pw.println("</b></th></tr><tr><td width=\"40\"><b>Color:</b>");
                    pw.println(accessory.getColor());
                    pw.println("</th></tr><tr><th><b>Condition:</b>");
                    pw.println(accessory.getCondition());
                    pw.println("</th></tr><tr><th><b>Manufacturer Rebates:</b>");
                    pw.println(manfRebate + "%");
                    pw.println("</th></tr><tr><th><b>Retailer Discounts:</b>");
                    pw.println(retDiscount + "%");
                    pw.println("</th></tr></table border=\"1\"></th>");
                    pw.println("<th width=\"30%\"><table><tr><td><b>Price:");
                    pw.println(originalPrice+"$");
                    pw.println("<tr><th><b>Price after Discount:");
                    pw.println(changePrice+"$");

                    pw.println("<tr><th><form method = 'get' action = 'AddingProductToCart'>");
                    pw.println("<input class = 'formbutton' type = 'submit' name = '" + accessory.getName() + "' value = 'Add to Cart'>");
                    pw.println("<input type='hidden' name='productName' value='" + accessory.getName() + "'>");
                    pw.println("<input type='hidden' name='image' value='" + accessory.getImage() + "'>");
                    pw.println("<input type='hidden' name='price' value='" + changePrice + "'>");
                    pw.println("<input type='hidden' name='quantity' value='" + 1 + "'>");
                    pw.println("</form></th></tr>");
                    pw.println("</table></th></tr></table>");
                    pw.println("</div>");
                }
            }
        }
		
		
        

        
        pw.println("</div>" +
                "<a class='carousel-control left' href='#myCarousel' data-slide='prev'>" +
                "<span class='glyphicon glyphicon-chevron-left'></span>" +
                "</a>" +
                "<a class='carousel-control right' href='#myCarousel' data-slide='next'>" +
                "<span class='glyphicon glyphicon-chevron-right'></span>" +
                "</a>" +
                "</div>" +
                "</div>");
				pw.println("</article></section>");
		

		pw.println("<aside class=\"sidebar\">");
        pw.println("<ul>" +
                "<li>" +
                "<h4>SmartPhones</h4><ul>");
        pw.println("<li>" +
                "<a href='DataServlet?productType=Smartphones'>SmartPhones</a></li>");
        pw.println("<li>" +
                "<a href=\"DataServlet?productType=Speakers\">Speakers</a></li>");
        pw.println("<li>" +
                "<a href=\"DataServlet?productType=Laptops\">Laptops</a></li>");
        pw.println("<li>" +
                "<a href=\"DataServlet?productType=Headphones\">HeadPhones</a></li>");
        pw.println("<li>" +
                "<a href=\"DataServlet?productType=Smartwatch\">SmartWatches</a></li>");
        pw.println("<li>" +
                "<a href=\"DataServlet?productType=Accessories\">External Storage</a></li>");
        pw.println("</ul>" +
                "</li>" +
                "</aside>" +
                "<div class=\"clear\">" +
                "</div></div>");

        pw.println("<footer>"+
                "<div class='footer-bottom'>"+
                "<p></p>"+
                "</div>"+
                "</footer>");
        pw.println("</body></html>");

		pw.close();
	
				
				


    }

}


	

