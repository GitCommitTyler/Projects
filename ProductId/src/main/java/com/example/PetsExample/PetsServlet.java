package com.example.PetsExample;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.PetsExample.model.*;



/**
 * Servlet implementation class PetsServlet
 */
public class PetsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PetsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
        // TODO Auto-generated method stub
        
        try {
        	PrintWriter out = response.getWriter();
        	out.println("<head><link rel=\"stylesheet\" href=\"styles.css\">\r\n"
        			+ "</head>");
            out.println("<html><body>");
                 
            InputStream in = getServletContext().getResourceAsStream("/WEB-INF/config.properties");
            Properties props = new Properties();
            props.load(in);
                
            DBConnection conn = new DBConnection(props.getProperty("url"), props.getProperty("userid"), props.getProperty("password"));
        	Statement stmt = conn.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rst = stmt.executeQuery("select * from products");
            out.println("<center><div><h2>Pets</h2>");
   
            out.println("<table><tr><th>Name</th><th>Color</th><th>Price</th></tr>");
            
            
            while (rst.next()) {
                out.println("<tr><td>" + rst.getString("name") + "</td>" + "<td>" +
                		rst.getString("color") + "</td><td>" + rst.getBigDecimal("price") + "</td></tr>");
            }
            
            out.println("</table>");
            
            stmt.close();        
            out.println("</div></center>");
        
            out.println("</body></html>");
            conn.closeConnection();
        
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        } catch (SQLException e) {
        	e.printStackTrace();
        }
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
    	out.println("<head><link rel=\"stylesheet\" href=\"styles.css\">\r\n"
    			+ "</head>");
        try {
        	Integer.parseInt(id);
        	
    		
        	out.println("<center><div><h2>Pet Found</h2>");
    		InputStream in = getServletContext().getResourceAsStream("/WEB-INF/config.properties");
            Properties props = new Properties();
            props.load(in);
	        DBConnection conn = new DBConnection(props.getProperty("url"), props.getProperty("userid"), props.getProperty("password"));
	    	Statement stmt = conn.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        ResultSet rst = stmt.executeQuery("select * from products where id="+id);
	        if(rst.next()) {
	        	rst.previous();
		        out.println("<table><tr><th>Name</th><th>Color</th><th>Price</th></tr>");
	            while (rst.next()) {
	                out.println("<tr><td>" + rst.getString("name") + "</td>" + "<td>" +
	                		rst.getString("color") + "</td><td>" + rst.getBigDecimal("price") + "</td></tr>");
            }
            
            out.println("</table>");
	        }
	        else
	        	out.println("None found");
            stmt.close();        
          
            conn.closeConnection();
	        
        }catch(SQLException se) {
        		out.println("Database access error");
        	   se.printStackTrace();
        }catch(ClassNotFoundException cnf) {
        		out.println("Some other thing when wrong");
        	   cnf.printStackTrace();
        }catch(NumberFormatException nfe) {
        	out.println("<center><div><h2>Error</h2>");
        	out.println("Invalid Input");

        }
        out.println("</div></center>");
        
        out.println("</body></html>");
	}
	

}