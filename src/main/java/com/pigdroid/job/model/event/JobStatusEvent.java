package com.pigdroid.job.model.event;

import org.springframework.context.ApplicationEvent;

import com.pigdroid.job.model.JobExecutionStatus;

public class JobStatusEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private JobExecutionStatus newStatus;

	private Object what = null;

	public JobStatusEvent(Long sourceId, JobExecutionStatus newStatus) {
		super(sourceId);
		this.newStatus = newStatus;
	}

	public JobStatusEvent(Long sourceId, JobExecutionStatus newStatus, Object what) {
		super(sourceId);
		this.newStatus = newStatus;
		this.what = what;
	}

	public Object getWhat() {
		return what;
	}

	public Long getSourceId() {
		return (Long) getSource();
	}

	public JobExecutionStatus getNewStatus() {
		return newStatus;
	}

}
