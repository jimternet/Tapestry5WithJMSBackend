package org.springsource.greenbeans.examples.jms.core;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springsource.greenbeans.examples.Customer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Component
public class Producer {

  @Value("${jms.customer.destination}")
  private String customerDestination = "customers";

  @Autowired
  private JmsTemplate jmsTemplate;

  @Autowired
  private CustomerMessageConverter customerMessageConverter;

  private Log log = LogFactory.getLog(getClass());

  @Transactional
  public void sendCustomerUpdate(final Customer customer) throws Exception {
    this.jmsTemplate.send(this.customerDestination, new MessageCreator() {
//      @Override
      public Message createMessage(Session session) throws JMSException {
        log.info("Sending customer data " + ToStringBuilder.reflectionToString(customer));
        return customerMessageConverter.fromCustomer(session, customer);
      }
    });
  }
  

  @Transactional
  public void sendTextMessage(final String body) throws Exception {
    this.jmsTemplate.send(this.customerDestination, new MessageCreator() {
//      @Override
      public Message createMessage(Session session) throws JMSException {
        log.info("Sending message : " + body);
        log.info("Session to String is  : " + session.toString());

        return customerMessageConverter.fromBody(session, body);
      }
    });
  }
}
