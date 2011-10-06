package org.springsource.greenbeans.examples.jms.core;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

@Configuration
public class JmsConfiguration {

  @Value("${broker.url}")
  private String brokerUrl = "tcp://localhost:61616";

  @Bean
  public ConnectionFactory connectionFactory() {
    ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
    activeMQConnectionFactory.setBrokerURL(this.brokerUrl);
    return new CachingConnectionFactory(activeMQConnectionFactory);
  }

  @Bean
  public JmsTransactionManager jmsTransactionManager() {
    return new JmsTransactionManager(this.connectionFactory());
  }

  @Bean
  public JmsTemplate jmsTemplate() {
    return new JmsTemplate(this.connectionFactory());
  }

}
