package com.hcl.filters;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.hcl.HibernateUtil;
import com.hcl.data.dao.UserHibernateDao;
import com.hcl.data.dao.interfaces.UserDao;
import com.hcl.data.entities.User;

/**
 * Servlet Filter implementation class SignupFilter
 */
public class SignupFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SignupFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String signUpValid = "Please enter a username and password";
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		

		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			UserDao userDao = new UserHibernateDao(); 
			userDao.setSession(session);
			List<User> foundUsers = userDao.findByUserName(username);
			if(foundUsers.size() != 0) {
				signUpValid = "User registered already, please log in";
				request.setAttribute("errormessage", signUpValid);
				request.getRequestDispatcher("Register.jsp").include(request, response);
			}
			else if (!username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$")) {
				signUpValid = "Username must be 8-20 characters long";
				request.setAttribute("errormessage", signUpValid);
				request.getRequestDispatcher("Register.jsp").include(request, response);
			}
			else if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,20}$")) {
				signUpValid = "Password must be at least 8 characters long and"
						+ " contain at least one capital letter, one lowercase letter, and one number";
				request.setAttribute("errormessage", signUpValid);
				request.getRequestDispatcher("Register.jsp").include(request, response);
			}
			else if (!firstName.matches("[A-Z]{1}[a-zA-Z]{2,19}") || !lastName.matches("[A-Z]{1}[a-zA-Z]{2,19}")) {
				signUpValid = "Name is too long, too short, contains invalid characters, or doesn't contain a capital letter";
				request.setAttribute("errormessage", signUpValid);
				request.getRequestDispatcher("Register.jsp").include(request, response);
			}
			else {
				User newUser = userDao.createUser(username, firstName, lastName, password);
				userDao.save(newUser);
				request.setAttribute("user", newUser);
				chain.doFilter(request, response);
			}
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
