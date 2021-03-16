package com.hcl.data.dao;

import java.util.List;

import org.hibernate.Query;
import org.mindrot.jbcrypt.BCrypt;

import com.hcl.data.dao.interfaces.UserDao;
import com.hcl.data.entities.User;

public class UserHibernateDao extends AbstractDao<User,Long> implements UserDao {
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByUserName(String userName) {
		Query q = getSession().createQuery("select u from User u"
				+ " where u.username = :username");
		q.setParameter("username", userName);
		return q.list();
	}
	
	public User createUser(String username, String firstName, String lastName, String password) {
		User user = new User();
		user.setUsername(username);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt(12)));
		return user;
	}
	
	public boolean checkPassword(String password1, String password2) {
		return BCrypt.checkpw(password1, password2);
		
	}

}
