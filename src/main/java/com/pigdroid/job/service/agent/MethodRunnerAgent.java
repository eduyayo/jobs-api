package com.pigdroid.job.service.agent;

import java.lang.reflect.Method;
import java.util.function.Consumer;

import com.pigdroid.job.model.TaskContext;
import com.pigdroid.job.model.event.JobStatusEvent;

public class MethodRunnerAgent extends AbstractAgent {

	protected MethodRunnerAgent(Consumer<JobStatusEvent> eventConsumer, TaskContext context) {
		super(eventConsumer, context);
	}

	@Override
	protected void onDoTask() throws Throwable {
		TaskContext context = getContext();

		String className = context.getProperty("className");
		String methodName = context.getProperty("methodName");

		Class<?> clazz = Class.forName(className);

		Method method = clazz.getMethod(methodName, (Class<?>[]) null);
		method.invoke(null);
	}

}
