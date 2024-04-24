package com.home.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GroceriesList")
public class GroceryList extends HttpServlet {
    private static final String query = "SELECT item,quantity,brand_name FROM homegroceries";
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter p = resp.getWriter();
        resp.setContentType("text/html"); // Set content type to HTML
        
        // Load JDBC
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        try (Connection con = DriverManager.getConnection("jdbc:mysql:///grocery", "root", "Keerthu@123");
             PreparedStatement ps = con.prepareStatement(query);) {
            
            ResultSet rs = ps.executeQuery();
            
            // Begin HTML
            p.println("<!DOCTYPE html>"
            		+" <html>"
            		+"<head>"
            		+"<meta charset='UTF-8'>"
            		+"<title>Grocery List</title>"
            		
            		+"<style>"
            		        + "body {"
            		        + "  background-color:  #95B9C7;" // Set background color here
            		        + "  display: flex;"
            		        + "  justify-content: center;"
            		        + "  align-items: center;"
            		        + "  height: 100vh;"
            		        + "  overflow:hidden;"
            		        + "}"
            		        + "table {"
            		        + "    border-collapse: collapse;"
            		        + "    width: 60%;"
            		        + "    margin: 0 auto; /* Center the table */"
            		        + "}"
            		        + "th, td {"
            		        + "    border: 1px solid black;"
            		        + "    padding: 8px;"
            		        + "    text-align: left;"
            		        + "}"
            		        + ".home-link {"
            		        + "    position: fixed;"
            		        + "    top: 500px;"
            		        + "    left: 20%;"
            		        + "}"
            		 
            		         +"</style>"+

            "</head>"+
            "<body>");

         
           
            // Begin HTML table
            p.println("<table>");
            
            // Print table headers
            p.println("<tr>");
            p.println("<th>Item</th>");
            p.println("<th>Quantity</th>");
            p.println("<th>Brand Name</th>");
            p.println("<th>Edit</th>");
            p.println("<th>Remove</th>");
            p.println("</tr>");
            
            // Print table data
            while (rs.next()) {
                p.println("<tr>");
                p.println("<td>" + rs.getString("item") + "</td>");
                p.println("<td>" + rs.getInt("quantity") + "</td>");
                p.println("<td>" + rs.getString("brand_name") + "</td>");
                p.println("<td><a href='editScreen?item=" + rs.getString(1) + "'> Edit</a></td>");
                p.println("<td><a href='deleteurl?item=" + rs.getString(1) + "'>Delete</a></td>");
                p.println("</tr>");
            }
            
            // End HTML table
            p.println("</table>");
            
            // Print Home link
            p.println("<a href='HomePage.jsp' class='home-link'>Home</a>");
            p.println("<div class='right'>");
            p.println("<img src='list_grocery.png' alt='Edit Grocery' style='width: 80%; height: 300%;'>"); // Adjusted image size
            p.println("</div>");
            // End HTML
            p.println("</body>");
            p.println("</html>");
            
        } catch (SQLException e) {
            e.printStackTrace();
            p.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            p.println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
