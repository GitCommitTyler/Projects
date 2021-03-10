package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import entities.Pet;
import com.HibernateUtil;



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
        	 SessionFactory factory = HibernateUtil.getSessionFactory();
             
             Session session = factory.openSession();
             List<Pet> list = session.createQuery("from Pet", Pet.class).list();
             
             session.close();
        
        	PrintWriter out = response.getWriter();
        	out.println("<head><link rel=\"stylesheet\" href=\"styles.css\">\r\n"
        			+ "</head>");
            out.println("<html><body>");
            out.println("<center><div><h2>Pets</h2>");
   
            out.println("<table><tr><th>Name</th><th>Color</th><th>Price</th></tr>");
            
            
            for(Pet p: list) {
                out.println("<tr><td>" + p.getName() + "</td>" + "<td>" +
                		p.getColor() + "</td><td>" + p.getPrice() + "</td></tr>");
            }
            
            out.println("</table>");
            out.println("</div></center>");
            out.println("</body></html>");
        
        } catch (Exception e) {
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
        	SessionFactory factory = HibernateUtil.getSessionFactory();
            
            Session session = factory.openSession();
            List<Pet> list = session.createQuery("from Pet P where P.id="+id, Pet.class).list();
            
            session.close();
    		
        	out.println("<center><div><h2>Pet Found</h2>");
        	

	        if(list.size()>0) {
		        out.println("<table><tr><th>Name</th><th>Color</th><th>Price</th></tr>");
	            for(Pet p: list) {
	                out.println("<tr><td>" + p.getName() + "</td>" + "<td>" +
	                		p.getColor() + "</td><td>" + p.getPrice() + "</td></tr>");
	            }
            
            out.println("</table>");
	        }
	        else
	        	out.println("None found");
	        
        }catch(NumberFormatException nfe) {
        	out.println("<center><div><h2>Error</h2>");
        	out.println("Invalid Input");

        }
        out.println("</div></center>");
        
        out.println("</body></html>");
	}
	

}