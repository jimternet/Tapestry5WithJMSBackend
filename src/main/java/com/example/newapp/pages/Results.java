package com.example.newapp.pages;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.EventLink;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.springframework.context.ApplicationContext;

import com.example.newapp.services.ReturnedMessages;
import com.example.newapp.services.SimpleMessage;

/**
 * Start page of application newapp.
 */
public class Results
{

	
	@Property
	private List<SimpleMessage> messages;
	
	private Log log = LogFactory.getLog(getClass());

	
	List<SimpleMessage> onPassivate() {
        log.info("Calling onPassivate");
        

		return messages;
	}

	void onActivate() {
        log.info("Calling onActivate");

		messages = ReturnedMessages.getList();

	}



}
