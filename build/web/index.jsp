<%-- 
    Document   : index
    Created on : Feb 6, 2012, 1:10:25 PM
    Author     : hnhp0025
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Phong's Restaurant Menu</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
    <center>
 
<h1> MENU</h1>
   <%@page import="java.util.ArrayList, java.sql.*" %>
        <%
            Connection conn = null;
            ResultSet rs = null;
            Statement stmt = null;
            String query="";
            // creates arraylist to store food and price
            ArrayList<String> food = new ArrayList<String>();
            ArrayList<Double> price = new ArrayList<Double>();
        %>
        <%  // loads the driver
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            // get connection to the database
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/menu ", 
            "admin", "admin");	
            stmt = conn.createStatement();
            // input the SQL query to get data from the menu table
            rs = stmt.executeQuery("SELECT * from APP.MENU");
        %>

        <% // get the food and price from the table
        while(rs.next()){ %>
        <% food.add(rs.getString("FOODNAME")); %>
        <% price.add(rs.getDouble("PRICE")); %>
        <%
            }
        %>
          <form name="form1" action="order.html">
         <select name="selectbox" multiple="multiple">
         <% // displays the menu in a selectable list box
         for(int i = 0; i < food.size(); i++)
            {
         %>
             <option value="<%=food.get(i)%>"><%=food.get(i)%> ---------- $<%=price.get(i)%></option>
         <%
            }
         %>
         </select>


         <p></p>
         <!-- Reset button clears the select for the list box, check out button sends user to the confirmation page -->
         <p></p><input type="reset" value="Reset" name="Reset"/><input type="submit" value="Check Out" name="Checkout" />
      </form>
    </center>
    </body>
</html>
