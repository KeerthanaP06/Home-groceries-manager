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

@WebServlet("/editScreen")
public class EditGrocery extends HttpServlet {
    private static final String query = "SELECT item,quantity,brand_name FROM homegroceries where item=?";
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter p = resp.getWriter();
        resp.setContentType("text/html"); // Set content type to HTML
        
        String item = req.getParameter("item");
        
        // Load JDBC
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        try (Connection con = DriverManager.getConnection("jdbc:mysql:///grocery","root","Keerthu@123");
             PreparedStatement ps = con.prepareStatement(query);) {
            
            ps.setString(1, item);
            ResultSet rs = ps.executeQuery();
            rs.next();
            
            // Begin HTML
            p.println("<!DOCTYPE html>"
                    + "<html>"
                    + "<head>"
                    + "<meta charset='UTF-8'>"
                    + "<title>Edit Grocery</title>"
                    + "<style>"
                    + "body {"
                    + "    background-color: lavender;" 
                    + "  background-position: fixed;"
                    + "    display: flex;"
                    + "    justify-content: center;"
                    + "    align-items: center;"
                    + "    height: 100vh;"
                    + "    overflow: hidden;"
                    + "}"
                    + ".content {"
                    + "    display: flex;"
                    + "    justify-content: center;"
                    + "    align-items: center;"
                    + "      width: 80%;"  // Adjusted width
                    + "    margin-top: 20px;"
                    + "}"
                    + ".left, .right {"
                    + "    padding: 0px;"
                    + "}"
                    + "form {"
                    + "    background-color: white;"
                    + "    padding: 20px;"
                    + "    border-radius: 20px;"
                    + "}"
                    + "form table {"
                    + "    margin: 0 auto;"
                    + "}"
                    + "form input[type='text'] {"
                    + "    width: 100%;"
                    + "    padding: 8px;"
                    + "    margin-bottom: 10px;"
                    + "    margin-right:20px;"
                    + "}"
                    + "form input[type='submit'], form input[type='reset'] {"
                    + "    padding: 10px;"
                    + "    margin-top: 10px;"
                    + "    background-color: #4CAF50;"
                    + "    color: white;"
                    + "    border: none;"
                    + "    border-radius: 10px;"
                    + "    cursor: pointer;"
                    + "}"
                    + ".right img {"
                    + "    width: 180%;" // Adjusted image size
                    + "    margin-left: -150px;" // Moved image slightly to the left
                    + "}"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<div class='content'>");
            
            // Table on the left
            p.println("<div class='left'>");
            p.println("<form action='editurl?item=" + item + "' method='post'>");
            p.println("<table>");
            p.println("<tr>");
            p.println("<td>Item</td>");
            p.println("<td><input type='text' name='item' value='" + rs.getString(1) + "'></td>");
            p.println("</tr>");
            p.println("<tr>");
            p.println("<td>Quantity</td>");
            p.println("<td><input type='text' name='quantity' value='" + rs.getInt(2) + "'></td>");
            p.println("</tr>");
            p.println("<tr>");
            p.println("<td>Brand Name</td>");
            p.println("<td><input type='text' name='brand_name' value='" + rs.getString(3) + "'></td>");
            p.println("</tr>");
            p.println("<tr>");
            p.println("<td><input type='submit' value='Edit'></td>");
            p.println("<td><input type='reset' value='Cancel'></td>");
            p.println("</tr>");
            p.println("</table>");
            p.println("</form>");
            p.println("</div>");
            
            // Image on the right
            p.println("<div class='right'>");
            p.println("<img src='edit_grocery.png' alt='Edit Grocery' style='width: 150%;'>"); // Adjusted image size
            p.println("</div>");
            
            // End HTML
            p.println("</div>"
                    + "</body>"
                    + "</html>");
            
        } catch(SQLException e) {
            e.printStackTrace();
            p.println(e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            p.println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
