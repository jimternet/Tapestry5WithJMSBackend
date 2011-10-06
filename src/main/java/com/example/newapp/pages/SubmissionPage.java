package com.example.newapp.pages;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.springframework.context.ApplicationContext;
import org.springsource.greenbeans.examples.jms.core.Producer;

/**
 * Start page of application newapp.
 */
public class SubmissionPage {

	@Inject
	private ApplicationContext springContext;

	@Property
	private String message;

	@InjectPage
	private Results resultsPage;




	Object onSuccess() {
		Producer producer = springContext.getBean(Producer.class);

		try {
			producer.sendTextMessage(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		resultsPage.set(message);
		return resultsPage;
	}

}
