<%-- 
    Document   : confirm
    Created on : Feb 13, 2012, 11:36:05 AM
    Author     : hnhp0025
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Confirmation</title>
    </head>
    <center>
    <body>
        <h3>Order Confirmation</h3>
        <% // Get parameters from previous page
           String[] quantity = request.getParameterValues("quantity");
           String[] food = request.getParameterValues("food");
           String[] price = request.getParameterValues("price");
           String[] confirmation = request.getParameterValues("confirm");
           Double totalprice = 0.0;
           int quantitycount = 0;
           %>
           
        <form name=\"form2\" action="confirm.jsp">
            <table border="1">
            <%   for(int i = 0; i < food.length; i++)
                 // output the selected food, quantity, and price in a table
                 // allows user to change the quantity of each food selected
            {%>
            <tr><td><input type="text" value="<%=food[i]%>"  name="food" size="15" readonly="readonly" /></td><td> <input type="text" value="<%=quantity[i]%>" name="quantity" size="1" maxlength="2" /></td><td>  $ <input type="text" value="<%=price[i]%>" name="price" size="5" readonly="readonly" /></td></tr>
                    
                    <%
                    // holds the price for the current row
                    double tempprice = Double.parseDouble(price[i]) * Integer.parseInt(quantity[i]);
                    // adds the price for the current row to the total price
                    totalprice += tempprice;
                    // calculates the total quantity
                    quantitycount += Integer.parseInt(quantity[i]);
                    %>
           <% }
           %>
           
           <!-- displays the total quantity and total price -->
           <tr><td>Total Price:  </td><td><%=quantitycount%></td><td>  $<%=totalprice%></td></tr>  </table>
           <% // if user selected refresh from before, this will recreate the buttons allowing the user to refresh or confirm again
           if (confirmation[0].equals("Refresh")){
                        %>
                        
          <p><input type="submit" value="Refresh" name="confirm"/>
          <input type="submit" value="Confirm" name="confirm"/></p>
    </form>
                <%}
                %>
               <% // if user selects confirm, this will display a confirmation message instead of showing the refresh, confirm buttons again
               if (confirmation[0].equals("Confirm")){
                  %>
                  <p>Your meal costs $<%=totalprice%>, thank you for your order.</p>
                  <p>Click <a href="index.jsp"> Here</a> to order again</p>
            <%}
           %>
           
    </center>
    </body>
</html>
