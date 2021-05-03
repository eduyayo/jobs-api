package com.pigdroid.job.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import com.pigdroid.job.model.entity.JobExecution;
import com.pigdroid.job.model.entity.JobTemplate;
import com.pigdroid.job.repository.JobTemplateRepository;
import com.pigdroid.job.service.agent.AbstractAgent;
import com.pigdroid.job.service.base.AbstractPagingService;
import com.pigdroid.util.DateUtils;

@Service
public class JobTemplateService extends AbstractPagingService<JobTemplate, Long> {

	@Autowired
	private JobExecutionService jobExecutionService;

	@Autowired
	private AgentFactory agentFactory;

	@Autowired
	private TaskScheduler taskScheduler;

	@Autowired
	protected JobTemplateService(JobTemplateRepository jobRepository) {
		super(jobRepository);
	}

	public JobExecution createExecution(Long jobTemplateId) {
		return createExecution(jobTemplateId, new Date());
	}

	public JobExecution createExecution(Long jobTemplateId, Date scheduleTime) {
		JobTemplate template = findById(jobTemplateId).orElse(null);
		JobExecution execution = JobExecution.builder()
				.jobTemplate(template)
				.scheduledDate(scheduleTime)
				.scheduledTime(scheduleTime)
				.build();
		JobExecution saved = jobExecutionService.save(execution);
		this.doSchedule(saved);
		return saved;
	}

	private void doSchedule(JobExecution execution) {
		AbstractAgent agent = this.agentFactory.createAgent(execution);
		this.taskScheduler.schedule(agent, DateUtils.getDate(execution.getScheduledDate(), execution.getScheduledTime()));
	}

}
