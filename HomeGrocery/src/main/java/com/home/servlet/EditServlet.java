package com.home.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editurl")
public class EditServlet extends HttpServlet {
    private static final String query = "UPDATE homegroceries SET quantity=?, brand_name=? WHERE item=?";
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html"); // Set content type to HTML
        
        String item = req.getParameter("item");
        // Getting edit data
        int editQuantity = Integer.parseInt(req.getParameter("quantity"));
        String editBrandName = req.getParameter("brand_name");
        
        // Load JDBC
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        try (Connection con = DriverManager.getConnection("jdbc:mysql:///grocery","root","Keerthu@123");
             PreparedStatement ps = con.prepareStatement(query);) {
            
            ps.setInt(1, editQuantity); // Set quantity parameter
            ps.setString(2, editBrandName); // Set brand_name parameter
            ps.setString(3, item); // Set item parameter
            
            int rowsAffected = ps.executeUpdate(); // Execute the update
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Edit Result</title>");
            out.println("<style>");
            out.println("body {");
            out.println("    background-color: yellow;"); // Set background color to yellow
            out.println("    color: red;"); // Set text color to red
            out.println("    display: flex;");
            out.println("    justify-content: center;");
            out.println("    align-items: center;");
            out.println("    height: 100vh;");
            out.println("    overflow:hidden;");
            out.println("}");
            out.println(".links {");
            out.println("    position: absolute;");
            out.println("    top: 150px;");
            out.println("    left: 350px;");
            out.println("}");
            out.println(".links button {");
            out.println("    margin-right: 20px;");
            out.println("    padding: 10px 20px;");
            out.println("    background-color: #4CAF50;");
            out.println("    color: white;");
            out.println("    border: none;");
            out.println("    border-radius: 5px;");
            out.println("    cursor: pointer;");
            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            // Links section
            out.println("<div class='links'>");
            out.println("<button onclick=\"window.location.href='HomePage.jsp'\">Click to back to Home</button>");
            out.println("<button onclick=\"window.location.href='GroceriesList'\"> View your Grocery List</button>");
            out.println("</div>");
            if (rowsAffected > 0) {
                out.println("<h2>Record updated successfully</h2>");
            } else {
                out.println("<h2>No records updated</h2>");
            }
            out.println("</body>");
            out.println("</html>");
            
        } catch(SQLException e) {
            e.printStackTrace();
            out.println(e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            out.println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
