package com.pigdroid.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class JobApplicationListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobApplicationListener.class);

    @Autowired
    QueuePollingComponent queuePollingComponent;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		queuePollingComponent.doPoll();
		LOGGER.info("Startup event done!");
	}

}
