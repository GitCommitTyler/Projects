package com.example.Authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DatabaseTests {
	
	Logger logger = LoggerFactory.getLogger(DatabaseTests.class);
	
	//MySql testing I guess
	@Test
	public void selectById_thenReturnValueWithThatId() {
		 try { 
			  Class.forName("com.mysql.cj.jdbc.Driver"); 
			  Connection con = DriverManager.getConnection("jdbc:mysql://localhost/db_example",
			  "root","weraru_1"); 
			  Statement stmt = con.createStatement(); 
			  stmt.executeUpdate("delete from user where name = 'Jerry'");
			  PreparedStatement ps = con.prepareStatement("insert into user (email, name, password) values(?,?,?)");
			  ps.setString(1, "gobbledygook@spoop.com");
			  ps.setString(2, "Jerry"); 
			  ps.setString(3, "wordsthatarehardtoguess"); 
			  ps.executeUpdate(); 
			  
			  ResultSet rs = stmt.executeQuery("select * from user where name='Jerry'");
			  
			  rs.next();
			   
				  logger.info("id: "+ rs.getInt(1)+ " email: "+
						  rs.getString(2) + " name: "+rs.getString(3)
			  );
			  
			  assertEquals(rs.getString(3), "Jerry");
			  con.close(); 
		 }
	  
	   catch (Exception e) { 
		  //TODO: handle exception
	  e.printStackTrace(); 
	  }
	}
	
}
