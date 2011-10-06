package org.springsource.greenbeans.examples.jms.messagelistenercontainer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springsource.greenbeans.examples.Customer;

import com.example.newapp.services.ReturnedMessages;
import com.example.newapp.services.SimpleMessage;

import java.util.Map;
import java.util.Random;

@Component
public class MessageListenerContainerConsumer {
	
    Random generator = new Random( 19580427 );


	private Log log = LogFactory.getLog(getClass());

	public void receiveMessage(Map<String, Object> message) throws Exception {

		log.info(message.getClass().toString() + "Is the class of the meesage");

		String firstName = (String) message.get("firstName");
		String lastName = (String) message.get("lastName");
		String email = (String) message.get("email");
		Long id = (Long) message.get("id");
		Customer customer = new Customer(id, firstName, lastName, email);
		log.info("receiving customer message: " + customer);
	}
	
	public void receiveMessage(String message) throws Exception {

//		log.info(message.getClass().toString() + "Is the class of the meesage");


		log.info("receiving customer message: " + message);
		SimpleMessage simpleMsg = new SimpleMessage();
		simpleMsg.setMessage(message);
		ReturnedMessages.getList().add(simpleMsg);
		
		log.info("\n\n\n first message is  " + ReturnedMessages.getList().get(0));

		
		log.info("\n\n\n MESSAGE LIST SIZE IS " + ReturnedMessages.getList().size());

//		insertTimer();
		log.info("done 'processing' message");

	}
	
	private void insertTimer() throws InterruptedException{

		
		
		Random rn = new Random();
		int minimum = 1000;
		int maximum = 10000;
		int n = maximum - minimum + 1;
		int i = rn.nextInt() % n;
		int randomNum = minimum + i;
		long randLong = randomNum;
		randLong = Math.abs(randLong);
		log.info("sleeping for " + randLong);
		Thread.sleep(randLong);
		
		
		
	}
}
