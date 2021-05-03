package com.pigdroid.job.service.agent;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pigdroid.job.model.JobExecutionStatus;
import com.pigdroid.job.model.TaskContext;
import com.pigdroid.job.model.event.JobStatusEvent;

public abstract class AbstractAgent implements Runnable, Comparable<AbstractAgent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractAgent.class);

	private Consumer<JobStatusEvent> eventConsumer;
	private TaskContext context;

	protected AbstractAgent(Consumer<JobStatusEvent> eventConsumer, TaskContext context) {
		this.eventConsumer = eventConsumer;
		this.context = context;
		onCreated();
	}

	@Override
	public final void run() {
		onBefore();
		try {
			onDoTask();
			onSuccess();
		} catch (Throwable t) {
			onError(t);
		}
		onDone();
	}

	protected abstract void onDoTask() throws Throwable;

	protected TaskContext getContext() {
		return this.context;
	}

	protected void onCreated() {
		eventConsumer.accept(new JobStatusEvent(this.context.getId(), JobExecutionStatus.QUEUED));
	}


	protected void onDone() {
		LOGGER.info("Task {} done.", getContext().getId());
	}

	protected void onError(Throwable error) {
		eventConsumer.accept(new JobStatusEvent(this.context.getId(), JobExecutionStatus.FAILED, error));

	}

	protected void onSuccess() {
		eventConsumer.accept(new JobStatusEvent(this.context.getId(), JobExecutionStatus.SUCCESS));

	}

	protected void onBefore() {
		LOGGER.info("Starting task {}.", getContext().getId());
		eventConsumer.accept(new JobStatusEvent(this.context.getId(), JobExecutionStatus.RUNNING));
	}

	@Override
	public int compareTo(AbstractAgent o) {
		return 0;
	}

}
