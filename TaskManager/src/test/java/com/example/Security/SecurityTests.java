
package com.example.Security;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;

import com.example.TaskManager.entities.User;
import com.example.TaskManager.exceptions.UserNotFoundException;
import com.example.TaskManager.repositories.UserRepository;
import com.example.TaskManager.services.UserService;

import org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;





@DataJpaTest
public class SecurityTests {
	
	Logger logger =  LoggerFactory.getLogger(SecurityTests.class);
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;
    
    
	@TestConfiguration
    static class UserServiceImplTestContextConfiguration {
		@Bean
		public UserService userService() {
			return new UserService();
		}
	
	}

    @Autowired
    private UserService us = new UserServiceImplTestContextConfiguration().userService();


    @Test
    public void whenFindByName_thenReturnUser() {
         //given

        User dummyUser = new User();
        dummyUser.setUserName("idiotsandwich");
        dummyUser.setFirstName("Dummy");
        dummyUser.setLastName("McGee");
        dummyUser.setEmail("test@test.com");
        dummyUser.setPassword("password");
        entityManager.persist(dummyUser);
        entityManager.flush();

         //when
        User found = userRepository.findByEmail(dummyUser.getEmail());

         //then

        assertEquals(found.getEmail(), dummyUser.getEmail());
    }
    
    
    @Test
    public void whenFindByEmailReturnsNull_thenThrowException() {
    	UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
    		us.GetUserByEmail("anyone@anything.com");
    	});
    }
    
    //Test update function from UserRepository
    @Test 
    public void InsertExistingId() {
    	
        User dummyUser = new User();
        dummyUser.setUserName("idiotsandwich");
        dummyUser.setFirstName("Dummy");
        dummyUser.setLastName("McGee");
        dummyUser.setEmail("test@test.com");
        dummyUser.setPassword("password");
        userRepository.save(dummyUser);
        entityManager.detach(dummyUser);
        
        User dummerUser = new User();
        dummerUser.setId(1);
        dummerUser.setUserName("asseater420");
        dummerUser.setEmail("something@gmail.com");
        dummerUser.setFirstName("Idiot");
        dummerUser.setLastName("Pants");
        dummerUser.setPassword("absolutelyzoinked");
        
        userRepository.save(dummerUser);
        assertNotEquals(us.GetUserById(1).getUserName(), dummyUser.getUserName());
        userRepository.deleteAll();
    }
    

    //Test update in UserService
    @Test 
    public void InsertExistingIdUserService() {
        User dummyUser = new User();
        dummyUser.setUserName("idiotsandwich");
        dummyUser.setFirstName("Dummy");
        dummyUser.setLastName("McGee");
        dummyUser.setEmail("test@test.com");
        dummyUser.setPassword("password");
        
        User dummerUser = new User();
        dummerUser.setId(2);
        dummerUser.setEmail("something@gmail.com");
        dummerUser.setUserName("Idiot");
        dummyUser.setFirstName("Dumbo");
        dummyUser.setLastName("Macfarlane");
        dummerUser.setPassword("absolutelyzoinked");
        
        us.UpdateUser(dummerUser);
        us.GetAllUsers().forEach(x->logger.info("HERE"+x.toString()));
        assertNotEquals(us.GetUserById(2).getUserName(), dummyUser.getUserName());

    }
    
        //test if exception is thrown when inserting null user into database with userRepository
        @Test
        public void whenInsertingNull_thenThrowException() {
        	User dummyUser = new User();
        	assertThrows(ConstraintViolationException.class, () -> {
        		userRepository.save(dummyUser);});
        	
        }
        
        
        
//         test return when using find on null set
        @Test
        public void whenNoUsers_thenReturnEmptyIterable() {

        	//userRepository.findAll().forEach(x-> logger.info("yerp"+x.toString()));
            assertEquals(((Collection<?>) us.GetAllUsers()).size(), 0);
        }
        
        //test when id not found
        @Test
        public void whenExceptionThrown_thenAssertionSucceeds() {
            UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
                us.GetUserById(97);
            });

            String expectedMessage = "User 97 not in database";
	            logger.info(exception.getMessage());
	            String actualMessage = exception.getMessage();
	
	            assertTrue(actualMessage.contains(expectedMessage));
	        }
        
        



    




}
