package com.pigdroid.job.service.agent;

import java.util.function.Consumer;

import javax.naming.OperationNotSupportedException;

import com.pigdroid.job.model.TaskContext;
import com.pigdroid.job.model.event.JobStatusEvent;

public class SystemCommandAgent extends AbstractAgent {

	protected SystemCommandAgent(Consumer<JobStatusEvent> eventConsumer, TaskContext context) {
		super(eventConsumer, context);
	}

	@Override
	protected void onDoTask() throws Throwable {
		//TODO
		throw new OperationNotSupportedException("Not Implemented Yet!");
	}

}
