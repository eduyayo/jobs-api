package com.pigdroid.job.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import com.pigdroid.job.model.JobExecutionStatus;
import com.pigdroid.job.model.entity.JobExecution;
import com.pigdroid.job.model.event.JobStatusEvent;
import com.pigdroid.job.repository.JobExecutionRepository;
import com.pigdroid.job.service.base.AbstractPagingService;

@Service
//TODO should the event handling grow, move to a segregated component
public class JobExecutionService extends AbstractPagingService<JobExecution, Long>  implements ApplicationListener<JobStatusEvent> {

	@Autowired
	protected JobExecutionService(JobExecutionRepository jobRepository) {
		super(jobRepository);
	}

	@Override
	public void onApplicationEvent(JobStatusEvent event) {
		Long sourceId = event.getSourceId();
		JobExecution execution = findById(sourceId).get();
		if (event.getNewStatus() == JobExecutionStatus.RUNNING) {
			execution.setStartDate(new Date());
			execution.setStartTime(new Date());
		}
		execution.setStatus(event.getNewStatus());
		save(execution);
	}

}
