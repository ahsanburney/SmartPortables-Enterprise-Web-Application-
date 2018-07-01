


import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class SearchAutoCompleteServlet extends HttpServlet {

    ArrayList<Object> products;

    HashMap<String, Smartphone> smartphones;
    HashMap<String, Laptop> laptops;
    HashMap<String, Smartwatch> smartwatches;
    HashMap<String, Speaker> speakers;
    HashMap<String, WarrantyServlet> warranty;
    HashMap<String, Accessory> extraItems;
    HashMap<String,Headphone> headphones;

    HashMap<String, Product> productsMap = new HashMap<String, Product>();

    public void init()
    {}

    void loadData()
    {
        try{
            products = AjaxUtility.fetchMysqlProducts();

            smartphones = (HashMap<String,Smartphone>)products.get(0);
            laptops = (HashMap<String, Laptop>)products.get(1);
            smartwatches = (HashMap<String, Smartwatch>)products.get(2);
            speakers = (HashMap<String, Speaker>)products.get(3);
            warranty = (HashMap<String, WarrantyServlet>)products.get(4);
            extraItems = (HashMap<String, Accessory>)products.get(5);
            headphones = (HashMap<String, Headphone>)products.get(6);
            productsMap = (HashMap<String, Product>)products.get(7);


        }catch(Exception E){
            System.out.println("Exception");
        }
    }


    private ServletContext servletContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.servletContext = config.getServletContext();
    }

   
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        loadData();

        String action = request.getParameter("action");
        String productId = request.getParameter("id");
        StringBuffer stringBuffer = new StringBuffer();

        if (productId != null) {
            productId = productId.trim().toLowerCase();
        } else {
            servletContext.getRequestDispatcher("/errorServlet").forward(request, response);
        }

        boolean b = false;
        if (action.equals("complete")) {
            if (!productId.equals("")) {

                Iterator iterator = productsMap.keySet().iterator();

                while (iterator.hasNext()) {
                    String id = (String) iterator.next();
                    Product product = (Product) productsMap.get(id);

                    if (product.getName().toLowerCase().startsWith(productId) || product.getCompany().toLowerCase().startsWith(productId))
                    {

                        stringBuffer.append("<product>");
                        stringBuffer.append("<id>" + product.getId() + "</id>");
                        stringBuffer.append("<name>" + product.getName() + "</name>");
                        stringBuffer.append("<company>" + product.getCompany() + "</company>");
                        stringBuffer.append("</product>");
                        b = true;
                    }
                }
            }

            if (b) {
                response.setContentType("text/xml");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("<products>" + stringBuffer.toString() + "</products>");
            } else {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        }

        if (action.equals("lookup")) {
            request.setAttribute("product", productsMap.get(productId));
            request.setAttribute("productName", productId);
            servletContext.getRequestDispatcher("/AutoCompleteProductServlet").forward(request, response);
        }
    }
}
