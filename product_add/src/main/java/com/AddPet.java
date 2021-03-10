package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.Pet;

/**
 * Servlet implementation class AddPet
 */
public class AddPet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get parameters inputed on previous page
		String name = request.getParameter("name");
		String color = request.getParameter("color");
		BigDecimal price = new BigDecimal(request.getParameter("price"));
		
		//create factory with hibernate utility class
		SessionFactory factory = HibernateUtil.getSessionFactory();
        
		//start session and transaction
        Session session = factory.openSession();
        session.getTransaction().begin();
        //create pet
        Pet pet = new Pet();
        pet.setName(name);
        pet.setColor(color);
        pet.setPrice(price);
        session.save(pet);
        //update pets database
        session.getTransaction().commit();
        
        PrintWriter out = response.getWriter();
        session.close();
        RequestDispatcher rd = request.getRequestDispatcher("addpet.jsp");
        rd.include(request, response);
        //pet was added
		out.print("<label style= 'position: fixed;\r\n"
				+ "    top: 55%;\r\n"
				+ "    left: 55%;\r\n"
				+ "    margin-top: -100px;\r\n"
				+ "    margin-left: -200px;'><span style ='color:green'>Pet Added Successfully</span></center></label>");
        
	}

}
