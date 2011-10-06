package org.springsource.greenbeans.examples.jms.jmstemplate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springsource.greenbeans.examples.Customer;

import javax.jms.MapMessage;
import javax.jms.Message;

@Component
public class RawJmsTemplatePollingMessageConsumer {


  @Autowired
  protected volatile JmsTemplate jmsTemplate;

  @Value("${jms.customer.destination}")
  private String destination;

  private Log log = LogFactory.getLog(getClass());

  @Transactional
  public void receiveAndProcessCustomerUpdates() throws Exception {
    Message message = this.jmsTemplate.receive(this.destination);
    if (message instanceof MapMessage) {

      MapMessage mapMessage = (MapMessage) message ;
      String firstName = mapMessage.getString("firstName");
      String lastName = mapMessage.getString("lastName");
      String email = mapMessage.getString("email");
      Long id = mapMessage.getLong("id");
      Customer customer = new Customer(id,firstName ,lastName ,email );
      log.info("receiving customer message: " + customer);
    }
  }
}
