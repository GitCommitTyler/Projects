package filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet Filter implementation class AddFilter
 */
public class AddFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AddFilter() {
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
		String name = request.getParameter("name");
		String color = request.getParameter("color");
		String price = String.valueOf(request.getParameter("price"));
		//If any fields are null or don't match the proper format, return with a message saying "Invalid Input"
		if(name == null || !name.matches("[a-zA-Z -]{2,}") || color == null || !color.matches("[a-zA-Z -]{1,}") || price == null || !price.matches("[0-9]+[.]{0,1}[0-9]{0,2}")) {
			PrintWriter out = response.getWriter();
			request.getRequestDispatcher("addpet.jsp").include(request, response);
			out.println("<label style='position: fixed;\r\n"
					+ "    top: 55%;\r\n"
					+ "    left: 55%;\r\n"
					+ "    margin-top: -100px;\r\n"
					+ "    margin-left: -200px;'><SPAN style= 'color: blue '>Invalid Input</SPAN></label>");
		}
		//otherwise 
		else {
			request.setAttribute("name", name);
			request.setAttribute("color", color);
			request.setAttribute("price", price);
			chain.doFilter(request, response);
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
