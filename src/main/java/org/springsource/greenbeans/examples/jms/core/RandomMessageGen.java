package org.springsource.greenbeans.examples.jms.core;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springsource.greenbeans.examples.Customer;

public class RandomMessageGen {
	  static ClassPathXmlApplicationContext classPathXmlApplicationContext ;
	  static SecureRandom random = new SecureRandom();


	
	  public static void main(String args[]) throws Throwable {
		  
		  classPathXmlApplicationContext 
		    = new ClassPathXmlApplicationContext("/jms-jmstemplate-consumer.xml");
		    classPathXmlApplicationContext.start();
		    

		    
//		    genPersonMessages(random);
		    genTextMessages(1000);
		    
		    	
		    

		  
		  
	  }
	  
		private static void genTextMessages(int numberOfMessages) throws Exception {
			for (int i = 1; i< numberOfMessages;i++){
				
				genTextMessage();
				
				
				
			}

			
		}


	private static void genTextMessage() throws Exception {
		
			genMessage(new BigInteger(35, random).toString(32));
			
		
	}

	private static void genPersonMessages(SecureRandom random) throws Exception {
		for (int i = 1; i< 10000000;i++){
			String firstName = null;
			String lastName = null;
			firstName =  new BigInteger(35, random).toString(32);
			lastName =  new BigInteger(50, random).toString(32);


 
			genMessage(i,firstName,lastName);
			
			
		}
	}
	  
	  public static void genMessage(int id, String firstName, String lastName) throws Exception{
		    Producer producer = classPathXmlApplicationContext.getBean(Producer.class);
		    producer.sendCustomerUpdate(new Customer(id, firstName, lastName, firstName + lastName + "@email.com"));
	  }
	  
	  public static void genMessage(String body) throws Exception{
		    Producer producer = classPathXmlApplicationContext.getBean(Producer.class);
		    producer.sendTextMessage(body);
	  }

}
