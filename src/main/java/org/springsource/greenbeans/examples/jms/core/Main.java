package org.springsource.greenbeans.examples.jms.core;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springsource.greenbeans.examples.Customer;
import org.springsource.greenbeans.examples.jms.jmstemplate.RawJmsTemplatePollingMessageConsumer;

public class Main {

  static void runJmsTemplateExample() throws Throwable {

/*    ClassPathXmlApplicationContext classPathXmlApplicationContext 
    = new ClassPathXmlApplicationContext("/jms-jmstemplate-consumer.xml");
    classPathXmlApplicationContext.start();

    Producer producer = classPathXmlApplicationContext.getBean(Producer.class);
    producer.sendCustomerUpdate(new Customer(1, "John", "Doe", "jd@email.com"));

    RawJmsTemplatePollingMessageConsumer consumer = classPathXmlApplicationContext.getBean(RawJmsTemplatePollingMessageConsumer.class);
    consumer.receiveAndProcessCustomerUpdates();
    */
	  
	  
  }

  static void runMessageListenerContainerExample() throws Throwable {
    ClassPathXmlApplicationContext classPathXmlApplicationContext 
    = new ClassPathXmlApplicationContext("jms-message-listener-container-consumer.xml");
    classPathXmlApplicationContext.start();
//
//    Producer producer = classPathXmlApplicationContext.getBean(Producer.class);
//    producer.sendCustomerUpdate(new Customer(1, "Jane", "Doe", "janeDoe@email.com"));
  }

  public static void main(String args[]) throws Throwable {
 // runJmsTemplateExample();
  runMessageListenerContainerExample();
  }
}
