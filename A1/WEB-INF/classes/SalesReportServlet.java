

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SalesReportServlet extends HttpServlet  {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = "";
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        pw.println("<!doctype html>" +
                "<html><head>" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
        pw.println("<title>Smart Portables</title>" +
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
                "<article><h3 align=\"center\">Select any Sales Report Query and Click Submit to View Result</h3>");
        pw.println("<fieldset>");
        pw.println("<table border='2' bordercolor=\"#ff0000\"><th>");
        pw.println("<form action='SalesReportServlet' method='post'>");

        pw.println("<tr>");
        pw.println("<th> <input type='checkbox' name='request' value='1'> Select </th>");
        pw.println("<th align='center'>Name of all Products Sold</th></tr>");

        pw.println("<tr>");
        pw.println("<th> <input type='checkbox' name='request' value='3'> Select </th>");
        pw.println("<th align='center'>Product Price</th></tr>");

        pw.println("<tr>");
        pw.println("<th> <input type='checkbox' name='request' value='4'> Select </th>");
        pw.println("<th align='center'> Number of Item Sold </th></tr>");


        pw.println("<tr>");
        pw.println("<th> <input type='checkbox' name='request' value='5'> Select </th>");
        pw.println("<th align='center'> Total Sales for every product</th></tr>");

        pw.println("<tr>");
        pw.println("<th> <input type='checkbox' name='request' value='2'> Select </th>");
        pw.println("<th> Generate a Bar Chart that shows the product names and the\n" +
                "total sales for every product</th></tr>");
        pw.println("<div type ='hidden' id='chart_div'></div>");

        pw.println("<tr>");
        pw.println("<th> <input type='checkbox' name='request' value='6'> Select </th>");
        pw.println("<th align='center'> Date of Sale</th></tr>");

        pw.println("<tr>");
        pw.println("<th> <input type='checkbox' name='request' value='7'> Select </th>");
        pw.println("<th align='center'> Total sales for every day-date</th></tr>");

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

        PrintWriter pw = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");

        String s = "";
        s = request.getParameter("request");

        System.out.println(s);
        pw.println("<!doctype html>" +
                "<html><head>" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
        pw.println("<title>Smart Portables</title>" +
                "<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
        pw.println("<body><div id=\"container\">");
        pw.println("<header>" +
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
            float price;
            int quantity;
            float totalPrice;
            LinkedHashMap<String, ArrayList<Object>> MostSoldProducts;
            MostSoldProducts = MySqlDataStoreUtilities.AllProductsSold();
            pw.println("<h3 align='center'>List of all products sold and how many items of every product sold</h3>");
            pw.println("<table border='2' bordercolor=\"#ff0000\">");
            pw.println("<tr><th><b>Product Model Name</b></th></tr>");
            for(Map.Entry<String, ArrayList<Object>> m :MostSoldProducts.entrySet())
            {
                ArrayList<Object> values = m.getValue();
                name = (String)values.get(0);


                pw.println("<tr><th>"+name+"</th></tr>");
            }
            pw.println("</table>");
        }

     /*   if(s.equals("1"))
        {
            String name;
            float price;
            int quantity;
            float totalPrice;
            LinkedHashMap<String, ArrayList<Object>> MostSoldProducts;
            MostSoldProducts = MySqlDataStoreUtilities.AllProductsSold();
            pw.println("<h3 align='center'>List of all products sold and how many items of every product sold</h3>");
            pw.println("<table border='2' bordercolor=\"#ff0000\">");
            pw.println("<tr><th><b>Product Model Name</b></th><th><b>Product Price</b></th><th><b>Quantity Sold</b></th><th><b>Total Sales</b></th></tr>");
            for(Map.Entry<String, ArrayList<Object>> m :MostSoldProducts.entrySet())
            {
                ArrayList<Object> values = m.getValue();
                name = (String)values.get(0);
                price = (Float)values.get(1);
                quantity = (Integer)values.get(2);
                totalPrice = (Float)values.get(3);

                pw.println("<tr><th>"+name+"</th>");
                pw.println("<th>"+price+"$"+"</th>");
                pw.println("<th>"+quantity+"</th>");
                pw.println("<th>"+totalPrice+"$"+"</th></tr>");
            }
            pw.println("</table>");
        }

        */

        if(s.equals("3"))
        {
            float price;
            LinkedHashMap<String, ArrayList<Object>> MostSoldProducts;
            MostSoldProducts = MySqlDataStoreUtilities.AllProductsSold();
            pw.println("<h3 align='center'>List of all products sold and how many items of every product sold</h3>");
            pw.println("<table border='2' bordercolor=\"#ff0000\">");
            pw.println("<tr><th><b>Product Price</b></th></tr>");
            for(Map.Entry<String, ArrayList<Object>> m :MostSoldProducts.entrySet())
            {
                ArrayList<Object> values = m.getValue();
                price = (Float)values.get(1);

                pw.println("<th>"+price+"$"+"</th></tr>");
            }
            pw.println("</table>");
        }

        if(s.equals("4"))
        {
            String name;
            float price;
            int quantity;
            float totalPrice;
            LinkedHashMap<String, ArrayList<Object>> MostSoldProducts;
            MostSoldProducts = MySqlDataStoreUtilities.AllProductsSold();
            pw.println("<h3 align='center'>List of all products sold and how many items of every product sold</h3>");
            pw.println("<table border='2' bordercolor=\"#ff0000\">");
            pw.println("<tr><th><b>Product Model Name</b></th><th><b>Quantity Sold</b></th></tr>");
            for(Map.Entry<String, ArrayList<Object>> m :MostSoldProducts.entrySet())
            {
                ArrayList<Object> values = m.getValue();
                name = (String)values.get(0);
                quantity = (Integer)values.get(2);


                pw.println("<tr><th>"+name+"</th>");
                pw.println("<th>"+quantity+"</th>");

            }
            pw.println("</table>");
        }

        if(s.equals("5"))
        {
            String name;
            float price;
            int quantity;
            float totalPrice;
            LinkedHashMap<String, ArrayList<Object>> MostSoldProducts;
            MostSoldProducts = MySqlDataStoreUtilities.AllProductsSold();
            pw.println("<h3 align='center'>List of all products sold and how many items of every product sold</h3>");
            pw.println("<table border='2' bordercolor=\"#ff0000\">");
            pw.println("<tr><th><b>Product Model Name</b></th><th><b>Product Price</b></th><th><b>Quantity Sold</b></th><th><b>Total Sales</b></th></tr>");
            for(Map.Entry<String, ArrayList<Object>> m :MostSoldProducts.entrySet())
            {
                ArrayList<Object> values = m.getValue();
                name = (String)values.get(0);
                price = (Float)values.get(1);
                quantity = (Integer)values.get(2);
                totalPrice = (Float)values.get(3);

                pw.println("<tr><th>"+name+"</th>");
                pw.println("<th>"+price+"$"+"</th>");
                pw.println("<th>"+quantity+"</th>");
                pw.println("<th>"+totalPrice+"$"+"</th></tr>");
            }
            pw.println("</table>");
        }
//===============================================================================================
        if(s.equals("6"))
        {
            String date;
            float price;

            LinkedHashMap<String, ArrayList<Object>> TotalSalesEveryDay;
            TotalSalesEveryDay = MySqlDataStoreUtilities.TotalDailySales();
            pw.println("<h3 align='center'>List of total daily sales transactions</h3>");
            pw.println("<table border='2' bordercolor=\"#ff0000\">");
            pw.println("<tr><th><b>Date</b></th></tr>");
            for(Map.Entry<String, ArrayList<Object>> m :TotalSalesEveryDay.entrySet())
            {
                ArrayList<Object> values = m.getValue();
                date = (String)values.get(0);

                pw.println("<tr><th>"+date+"</th>");

            }
            pw.println("</table>");
        }

        if(s.equals("7"))
        {
            String date;
            float price;

            LinkedHashMap<String, ArrayList<Object>> TotalSalesEveryDay;
            TotalSalesEveryDay = MySqlDataStoreUtilities.TotalDailySales();
            pw.println("<h3 align='center'>List of total daily sales transactions</h3>");
            pw.println("<table border='2' bordercolor=\"#ff0000\">");
            pw.println("<tr><th><b>Date</b></th><th><b>Total Sales</b></th></tr>");
            for(Map.Entry<String, ArrayList<Object>> m :TotalSalesEveryDay.entrySet())
            {
                ArrayList<Object> values = m.getValue();
                date = (String)values.get(0);
                price = (Float)values.get(1);


                pw.println("<tr><th>"+date+"</th>");
                pw.println("<th>"+price+"$"+"</th>");

            }
            pw.println("</table>");
        }

 //===========================================================================================
        if(s.equals(1) && s.equals("5"))
        {
            String name;
            float price;

            LinkedHashMap<String, ArrayList<Object>> MostSoldProducts;
            MostSoldProducts = MySqlDataStoreUtilities.AllProductsSold();
            pw.println("<h3 align='center'>List of all products sold and how many items of every product sold</h3>");
            pw.println("<table border='2' bordercolor=\"#ff0000\">");
            pw.println("<tr><th><b>Product Model Name</b></th><th><b>Product Price</b></th><th><b>Quantity Sold</b></th><th><b>Total Sales</b></th></tr>");
            for(Map.Entry<String, ArrayList<Object>> m :MostSoldProducts.entrySet())
            {
                ArrayList<Object> values = m.getValue();
                name = (String)values.get(0);
                price = (Float)values.get(1);


                pw.println("<tr><th>"+name+"</th>");
                pw.println("<th>"+price+"$"+"</th>");

            }
            pw.println("</table>");
        }

 //=============================================================================================

//================================================================================================
        if(s.equals("2"))
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    "/WEB-INF/classes/DatavisualizationSales.js");

            dispatcher.include(request, response);

           /* try{
                String name;
                String itemsAvailable;
                LinkedHashMap<String, ArrayList<Object>> barChartForProductNameAndTotalSales;
                barChartForProductNameAndTotalSales = MySqlDataStoreUtilities.ProductnamesBarchartAndTotalSales();

                String gson =new  Gson().toJson(barChartForProductNameAndTotalSales);

                response.setContentType("application/JSON");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(gson);

            }catch(Exception ex) {
                System.out.println(ex.getMessage());
            }*/
/*
            String name;
            String totalSales;
            LinkedHashMap<String, ArrayList<Object>> barChartForProductNameAndTotalSales;
            barChartForProductNameAndTotalSales = MySqlDataStoreUtilities.ProductnamesBarchartAndTotalSales();
            pw.println("<h3 align='center'>a Bar Chart that shows the product names and the\n" +
                    "total sales for every product</h3>");
            pw.println("<table border='2' bordercolor=\"#ff0000\">");
            pw.println("<tr><th align=\"center\"><b>Product Model Name</b></th><th align=\"center\"><b>Total Sales</b></th></tr>");
            for(Map.Entry<String, ArrayList<Object>> m :barChartForProductNameAndTotalSales.entrySet())
            {
                ArrayList<Object> values = m.getValue();
                name = (String)values.get(0);
                totalSales = (String)values.get(1);
                pw.println("<tr><th >"+name+"</th>");
                pw.println("<th align=\"center\">"+totalSales+"</th>");
            }
            pw.println("</table>");
			*/
        }


//================================================================================================

    }

}

