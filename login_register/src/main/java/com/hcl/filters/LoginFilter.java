package com.hcl.filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import com.hcl.HibernateUtil;
import com.hcl.data.dao.UserHibernateDao;
import com.hcl.data.dao.interfaces.UserDao;
import com.hcl.data.entities.User;
import org.mindrot.jbcrypt.*;

@WebFilter(servletNames = "Login")
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
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

	
			String loginValid = "Please enter a username and password";
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if(username == "" && password == "") {
				request.setAttribute("errormessage", loginValid);
				request.getRequestDispatcher("index.jsp").include(request, response);
			}
			else if(username == "") {
				loginValid = "Please enter a username";
				request.setAttribute("errormessage", loginValid);
				request.getRequestDispatcher("index.jsp").include(request, response);
			}
			else if(password == "") {
				loginValid = "Please enter a password";
				request.setAttribute("errormessage", loginValid);
				request.getRequestDispatcher("index.jsp").include(request, response);
			}
			else {
				Session session = HibernateUtil.getSessionFactory().openSession();
				session.beginTransaction();
				UserDao userDao = new UserHibernateDao(); 
				userDao.setSession(session);
				List<User> foundUsers = userDao.findByUserName(username);
				if(foundUsers.size() == 0) {
					loginValid = "User not found, please register";
					request.setAttribute("errormessage", loginValid);
					request.getRequestDispatcher("index.jsp").include(request, response);
				}
				else if (!userDao.checkPassword(password,foundUsers.get(0).getPassword())) {
					loginValid = "Password is incorrect";
					request.setAttribute("errormessage", loginValid);
					request.getRequestDispatcher("index.jsp").include(request, response);
				}
				else if (userDao.checkPassword(password, foundUsers.get(0).getPassword())) {
					request.setAttribute("user", foundUsers.get(0));
					chain.doFilter(request, response);
				}
				session.getTransaction().commit();
				session.close();
				
				
			
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
