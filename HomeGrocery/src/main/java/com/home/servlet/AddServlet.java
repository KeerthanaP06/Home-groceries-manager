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

@WebServlet("/register")
public class AddServlet extends HttpServlet {
    private static final String query = "INSERT INTO homegroceries(item,quantity,brand_name) VALUES(?,?,?)";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        // Getting grocery info
        String item = req.getParameter("item");
        int quantity = Integer.parseInt(req.getParameter("quantity")); // Change parameter name to "quantity"
        String brand_name = req.getParameter("brand_name");

        // Load JDBC
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection con = DriverManager.getConnection("jdbc:mysql:///grocery", "root", "Keerthu@123");
                PreparedStatement ps = con.prepareStatement(query);) {
            ps.setString(1, item);
            ps.setInt(2, quantity);
            ps.setString(3, brand_name);
            int count = ps.executeUpdate();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Add Result</title>");
            out.println("<style>");
            out.println("body {");
            out.println("    background-color: #FFE0B2;"); // Set light orange background color
            out.println("    display: flex;");
            out.println("    justify-content: center;");
            out.println("    align-items: center;");
            out.println("    height: 100vh;");
            out.println("    overflow: hidden;"); // Hide overflow
            out.println("}");
            out.println("h2 {");
            out.println("    color: #FF5722;"); // Set orange color for the heading
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
            if (count == 1) {
                out.println("<h2>Item added successfully</h2>");
            } else {
                out.println("<h2>Item not added successfully</h2>");
            }
            out.println("<div class='links'>");
            out.println("<button onclick=\"window.location.href='HomePage.jsp'\">Click to back to Home</button>");
            out.println("<button onclick=\"window.location.href='GroceriesList'\"> View your Grocery List</button>");
            out.println("</div>");
            
           out.println("</body>");
            out.println("</html>");

        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<h1>" + e.getMessage() + "<h2>");
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h1>" + e.getMessage() + "<h2>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
