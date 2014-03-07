/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hnhp0025
 */

@WebServlet(name = "MenuServlet", urlPatterns = {"/MenuServlet"})
public class MenuServlet extends HttpServlet{
        /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    Connection connection;

    public void init (ServletConfig config) throws ServletException {
        try {
        // load the driver
             Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
	// Get a Connection to the database          		
            connection = 
            DriverManager.getConnection("jdbc:derby://localhost:1527/menu", 
            "admin", "admin");	
        }  catch( Exception e ) { 
             e.printStackTrace();
             return;
        }
   }
    
     
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        Statement stmt = null;
        // get user selected food from previous page
        String[] food = req.getParameterValues("selectbox");
        String name = "";
//      String quantityget = req.getParameter("Quantity");
//      int quantity = Integer.parseInt(quantityget);
        double totalprice = 0.0;
        // create array list to store the price
        ArrayList<Double> price = new ArrayList<Double>();
        
        // if no food is selected, redirect to the menu page and output error message
        if (food==null){
             res.sendRedirect("nonselected.jsp");
        }


        
        try { 
            // store selected food names for SQL query
            for (int i = 0; i < food.length; i++){
                name += "'"+ food[i] + "', ";

            }
            
            // cut out the last comma from SQL query
            name = name.substring(0, name.length()-2);
            // create database statement
            stmt = connection.createStatement();
            // get data from the menu table
            ResultSet rs = stmt.executeQuery("SELECT * from APP.MENU WHERE FOODNAME IN ("+name+")");
            // get the price for each food selected
            while (rs.next()) {
                        price.add(rs.getDouble("PRICE"));
                    }	
            stmt.close();
            
            // create the html for the confirmation form
            res.setContentType("text/html");
            out.println("<html><head><title>Order Confirmation</title></head><body><center>");
            out.println("<p><h3>Order Confirmation</h3></p>");
            // sends user to the refreshed page, where it either updates the list or confirms the order
            out.println("<form name=\"form2\" action=\"confirm.jsp\">");
            out.println("<table border=\"1\">");
            for(int i = 0; i < food.length; i++)
            {       
                    // output the selected food, quantity, and price in a table
                    // allows user to change the quantity of each food selected
                    out.println("<tr><td><input type=\"text\" value=\""+food[i]+"\" name=\"food\" size=\"15\" readonly=\"readonly\" /></td><td> <input type=\"text\" value=\"1\" name=\"quantity\" size=\"1\" maxlength=\"2\"/></td>  <td>$ <input type=\"text\" value=\""+price.get(i)+"\" name=\"price\" size=\"5\" readonly=\"readonly\" /></td></tr>");
                    // calculate total price
                    totalprice += price.get(i);
            }
            // output total price and total quantity
            out.println("<tr><td>Total Price:</td> <td>" +price.size()+ "</td> <td>$" + totalprice + "</td></tr></table>");
            // refresh button updates the quantity and total price
            out.println("<p><input type=\"submit\" value=\"Refresh\" name=\"confirm\"/>");
            // confirm button confirms the order
            out.println("<input type=\"submit\" value=\"Confirm\" name=\"confirm\"/></p>");
            out.println("</form></center></body></html>");
            out.close();
        }
        catch(Exception e1)
        {
                e1.printStackTrace();
        }
        finally {            
            out.close();
        }
    }
    
}
