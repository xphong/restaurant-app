<%-- 
    Document   : nonselected
    Created on : Feb 13, 2012, 1:33:18 PM
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
        <!-- Same page as the index, except displays error -->
        <h3>Please select a food from the menu</h3>

        <h1> MENU</h1>
         <%@page import="java.util.ArrayList, java.sql.*" %>
        <%
            Connection conn = null;
            ResultSet rs = null;
            Statement stmt = null;
            String query="";
            ArrayList<String> food = new ArrayList<String>();
            ArrayList<Double> price = new ArrayList<Double>();
        %>
        <%   
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/menu ", 
            "admin", "admin");	
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * from APP.MENU");
        %>

        <% while(rs.next()){ %>
        <% food.add(rs.getString("FOODNAME")); %>
        <% price.add(rs.getDouble("PRICE")); %>
        <%
            }
        %>
          <form name="form1" action="order.html">
         <select name="selectbox" multiple="multiple">
         <% for(int i = 0; i < food.size(); i++)
            {
         %>
             <option value="<%=food.get(i)%>"><%=food.get(i)%> ---------- $<%=price.get(i)%></option>
         <%
            }
         %>
         </select>


         <p></p>
         <p></p><input type="reset" value="Reset" name="Reset"/><input type="submit" value="Check Out" name="Checkout" />
      </form>
    </center>
    </body>
</html>
