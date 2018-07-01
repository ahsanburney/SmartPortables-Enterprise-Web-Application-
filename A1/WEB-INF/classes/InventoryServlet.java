

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;



public class InventoryServlet extends  HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = "";
        PrintWriter pw = response.getWriter();

        response.setContentType("text/html;charset=UTF-8");
        pw.println("<!doctype html>" +
                "<html><head>" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");

        pw.println("<title>Smart Portables</title>" +

                "<script type='text/javascript' language ='javascript' src='Datavisualization.js'></script>"+

                "<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
        pw.println("<body><div id=\"container\">");
        pw.println("<header>" +
                "<script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>"+
                "<h1><a href=\"/\">Smart<span>Portables</span></a></h1><h3>ADMIN AREA</h3></header>");
        pw.println("<nav><ul>");
        pw.println("<li class=\"\"><a href=\"AdministratorServlet\">Product List</a></li>");
        pw.println("<li><a href=\"AddProduct?type=addProduct\">Add Product</a></li>");
        pw.println("<li><a href=\"InventoryServlet\">Inventory Report</a></li>");
        pw.println("<li><a href=\"SalesReportServlet\">Sales Report</a></li>");
        pw.println("<li><a href=\"AnalyticsServlet\">Data Analytics</a></li>");
        pw.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");
        pw.println("<div align='center' id=\"body\">" +
                "<article><h3 align=\"center\">Select any Inventory Report Query and Click Submit to View Result</h3>");
        pw.println("<fieldset>");
        pw.println("<table border='2' bordercolor=\"#ff0000\"><th>");
        pw.println("<form action='InventoryServlet' method='post'>");




        pw.println("<tr>");
        pw.println("<th> <input type='checkbox' name='request' value='1'> Select </th>");
        pw.println("<th align='center'>All Products Name</th></tr>");

        pw.println("<tr>");
        pw.println("<th> <input type='checkbox' name='request' value='2'> Select </th>");
        pw.println("<th align='center'>Product Price</th></tr>");

        pw.println("<tr>");
        pw.println("<th> <input type='checkbox' name='request' value='3'> Select </th>");
        pw.println("<th align='center'> Number Of Items Available in Inventory</th></tr>");

        pw.println("<tr>");
        pw.println("<th> <input type='checkbox' name='request' value='8'> Select </th>");
        pw.println("<th> Generate a Bar Chart that shows the product names and the\n" +
                "total number of items available for every product</th></tr>");
        pw.println("<div type ='hidden' id='chart_div'></div>");

        pw.println("<tr>");
        pw.println("<th> <input type='checkbox' name='request' value='5'> Select </th>");
        pw.println("<th align='center'> On Sale Products</th></tr>");

        pw.println("<tr>");
        pw.println("<th> <input type='checkbox' name='request' value='4'> Select </th>");
        pw.println("<th align='center'> Products With Manufacturer rebate</th></tr>");



        pw.println("<tr>");
        pw.println("<th></th>");
        pw.println("<th><input name=\"send\" margin:auto; display:block;  class=\"formbutton\" value=\"Submit\" type=\"submit\" /></th>");
        pw.println("</tr>");
        pw.println("</form>");
        pw.println("</th></table>");
        pw.println("</fieldset>" +
                "</article></div>" +
                "</div></body>" +
                "</html>");

        pw.close();
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        LinkedHashMap<String, ArrayList<Object>> barChartForProductNameAndItemsAvailable;
        barChartForProductNameAndItemsAvailable = MySqlDataStoreUtilities.ProductnamesBarchartAndTotalnumberofItems();

        PrintWriter pw = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");

        String s = "";
        String t = "";
        String u = "";
        String v = "";
        s = request.getParameter("request");
        System.out.println(s);
        pw.println("<!doctype html>" +
                "<html><head>" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");





        pw.println("<title>Smart Portables</title>" +
                "<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />" +
                "" +
                "</head>");
        pw.println("<body><div id=\"container\">");
        pw.println("<header>" +
                "<script type=\"text/javascript\" src=\"${pageContext.request.contextPath}/Datavisualization.js\"></script>"+
                "<h1><a href=\"/\">Smart<span>Portables</span></a></h1><h3>ADMIN AREA</h3></header>");
        pw.println("<nav><ul>");
        pw.println("<li class=\"\"><a href=\"AdministratorServlet\">Product List</a></li>");
        pw.println("<li><a href=\"AddProduct?type=addProduct\">Add Product</a></li>");
        pw.println("<li><a href=\"InventoryServlet\">Inventory Report</a></li>");
        pw.println("<li><a href=\"SalesReportServlet\">Sales Report</a></li>");
        pw.println("<li><a href=\"AnalyticsServlet\">Data Analytics</a></li>");
        pw.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");

        //======================================================================================


        if(s.equals("1"))
        {
            String name;
            LinkedHashMap<String, ArrayList<Object>> allProductsInventory;
            allProductsInventory = MySqlDataStoreUtilities.AllProducts();
            pw.println("<h3 align='center'>List of All products and how many items of every product currently available in the store</h3>");
            pw.println("<table border='2' bordercolor=\"#ff0000\">");
            pw.println("<tr><th align=\"center\"><b>Product Model Name</b></th></tr>");
            for(Map.Entry<String, ArrayList<Object>> mapEntry :allProductsInventory.entrySet()){

                {
                    ArrayList<Object> values = mapEntry.getValue();
                    name = (String)values.get(0);

                    pw.println("<tr><th >"+name+"</th>");
                }
            }
            pw.println("</table>");

        }

        if(s.equals("3"))
        {
            String name;
            String price;
            String quantity;
            LinkedHashMap<String, ArrayList<Object>> allProductsInventory;
            allProductsInventory = MySqlDataStoreUtilities.AllProducts();
            pw.println("<h3 align='center'>List of All products and how many items of every product currently available in the store</h3>");
            pw.println("<table border='2' bordercolor=\"#ff0000\">");
            pw.println("<tr><th align=\"center\"><b>Product Model Name</b></th><th align=\"center\"><b>Product Price</b></th><th align=\"center\"><b>Items Available</b></th></tr>");
            for(Map.Entry<String, ArrayList<Object>> mapEntry :allProductsInventory.entrySet()){

                {
                    ArrayList<Object> values = mapEntry.getValue();
                    name = (String)values.get(0);
                    price = (String)values.get(2)+"$";
                    quantity = (String)values.get(3);
                    pw.println("<tr><th >"+name+"</th>");
                    pw.println("<th align=\"center\">"+price+"</th>");
                    pw.println("<th align=\"center\">"+quantity+"</th></tr>");
                }
            }
            pw.println("</table>");

        }


        if(s.equals("2"))
        {

            String price;
            String quantity;
            LinkedHashMap<String, ArrayList<Object>> allProductsInventory;
            allProductsInventory = MySqlDataStoreUtilities.AllProducts();
            pw.println("<h3 align='center'>List of All products and how many items of every product currently available in the store</h3>");
            pw.println("<table border='2' bordercolor=\"#ff0000\">");
            pw.println("<tr><th align=\"center\"><b>Product Price</b></th></tr>");
            for(Map.Entry<String, ArrayList<Object>> mapEntry :allProductsInventory.entrySet()){

                {
                    ArrayList<Object> values = mapEntry.getValue();

                    price = (String)values.get(2)+"$";

                    pw.println("<tr><th align=\"center\">"+price+"</th></tr>");

                }
            }
            pw.println("</table>");

        }







//===============================================================================================
        if(s.equals("5"))
        {
            String name;
            String price;
            String quantity;
            LinkedHashMap<String, ArrayList<Object>> onSaleProducts;
            onSaleProducts = MySqlDataStoreUtilities.OnSaleProducts();
            pw.println("<h3 align='center'>List of all products currently on sale</h3>");
            pw.println("<table border='2' bordercolor=\"#ff0000\">");
            pw.println("<tr><th align=\"center\"><b>Product Model Name</b></th><th align=\"center\"><b>On Sale</b></th></tr>");
            for(Map.Entry<String, ArrayList<Object>> m :onSaleProducts.entrySet())
            {
                ArrayList<Object> values = m.getValue();
                name = (String)values.get(0);
                quantity = (String)values.get(3);
                pw.println("<tr><th >"+name+"</th>");
                pw.println("<th align=\"center\">"+quantity+"</th></tr>");
            }
            pw.println("</table>");
        }

//================================================================================================
        if(s.equals("4"))
        {
            String name;
            String price;
            String quantity;
            LinkedHashMap<String, ArrayList<Object>> rebateProducts;
            rebateProducts = MySqlDataStoreUtilities.ProductsWithManufacturerRebate();
            pw.println("<h3 align='center'>List of all products currently that have manufacturer rebates</h3>");
            pw.println("<table border='2' bordercolor=\"#ff0000\">");
            pw.println("<tr><th align=\"center\"><b>Product Model Name</b></th><th align=\"center\"><b>Manufacturer Rebate</b></th></tr>");
            for(Map.Entry<String, ArrayList<Object>> m :rebateProducts.entrySet())
            {
                ArrayList<Object> values = m.getValue();
                name = (String)values.get(0);
                quantity = (String)values.get(3)+"%";
                pw.println("<tr><th >"+name+"</th>");
                pw.println("<th align=\"center\">"+quantity+"</th></tr>");
            }
            pw.println("</table>");
        }

//================================================================================================
        if(s.equals("8"))
        {
			/*try{
                String name;
                String itemsAvailable;
                LinkedHashMap<String, ArrayList<Object>> barChartForProductNameAndItemsAvailable;
                barChartForProductNameAndItemsAvailable = MySqlDataStoreUtilities.ProductnamesBarchartAndTotalnumberofItems();

                String reviewsJson =new  Gson().toJson(barChartForProductNameAndItemsAvailable);

                response.setContentType("application/JSON");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(reviewsJson);



            }catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
			 */
            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    "/WEB-INF/classes/Datavisualization.js");



            dispatcher.include(request, response);
            
        }

    }

}
