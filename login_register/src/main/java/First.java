

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.mindrot.jbcrypt.BCrypt;

import com.hcl.HibernateUtil;
import com.hcl.data.dao.UserHibernateDao;
import com.hcl.data.dao.interfaces.UserDao;
import com.hcl.data.entities.User;

/**
 * Servlet implementation class First
 */
public class First extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public First() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("hello");
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		UserDao userDao = new UserHibernateDao(); 
		userDao.setSession(session);
			//session.createSQLQuery("truncate table users").executeUpdate();
			User user1 = userDao.createUser("weraru_1", "Tyler", "Nelson", "Weraru_1");
			User user2 = userDao.createUser("billybob", "William", "Robertson", "Isawasquirrel1");
			User user3 = userDao.createUser("smartbrunette", "Cecelia", "Edwards", "Nightmarenightmare23");
			session.save(user1);
			session.save(user2);
			session.save(user3);
		session.getTransaction().commit();
		session.close();
		request.getRequestDispatcher("index.jsp").include(request, response);  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
