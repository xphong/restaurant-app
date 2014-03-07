package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import java.sql.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.Vector _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>Phong's Restaurant Menu</title>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("    <center>\n");
      out.write(" \n");
      out.write("<h1> MENU</h1>\n");
      out.write("   \n");
      out.write("        ");

            Connection conn = null;
            ResultSet rs = null;
            Statement stmt = null;
            String query="";
            // creates arraylist to store food and price
            ArrayList<String> food = new ArrayList<String>();
            ArrayList<Double> price = new ArrayList<Double>();
        
      out.write("\n");
      out.write("        ");
  // loads the driver
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            // get connection to the database
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/menu ", 
            "admin", "admin");	
            stmt = conn.createStatement();
            // input the SQL query to get data from the menu table
            rs = stmt.executeQuery("SELECT * from APP.MENU");
        
      out.write("\n");
      out.write("\n");
      out.write("        ");
 // get the food and price from the table
        while(rs.next()){ 
      out.write("\n");
      out.write("        ");
 food.add(rs.getString("FOODNAME")); 
      out.write("\n");
      out.write("        ");
 price.add(rs.getDouble("PRICE")); 
      out.write("\n");
      out.write("        ");

            }
        
      out.write("\n");
      out.write("          <form name=\"form1\" action=\"order.html\">\n");
      out.write("         <select name=\"selectbox\" multiple=\"multiple\">\n");
      out.write("         ");
 // displays the menu in a selectable list box
         for(int i = 0; i < food.size(); i++)
            {
         
      out.write("\n");
      out.write("             <option value=\"");
      out.print(food.get(i));
      out.write('"');
      out.write('>');
      out.print(food.get(i));
      out.write(" ---------- $");
      out.print(price.get(i));
      out.write("</option>\n");
      out.write("         ");

            }
         
      out.write("\n");
      out.write("         </select>\n");
      out.write("\n");
      out.write("\n");
      out.write("         <p></p>\n");
      out.write("         <!-- Reset button clears the select for the list box, check out button sends user to the confirmation page -->\n");
      out.write("         <p></p><input type=\"reset\" value=\"Reset\" name=\"Reset\"/><input type=\"submit\" value=\"Check Out\" name=\"Checkout\" />\n");
      out.write("      </form>\n");
      out.write("    </center>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
