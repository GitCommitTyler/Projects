package com.hcl;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

//import com.hcl.data.entities.User;

public class HibernateUtil {
	private static SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() {
		try {
			Configuration config = new Configuration();
			config.configure();
			return config.buildSessionFactory(new StandardServiceRegistryBuilder()
					.applySettings(config.getProperties()).build());
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
