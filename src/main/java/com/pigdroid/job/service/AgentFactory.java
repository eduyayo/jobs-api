package com.pigdroid.job.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import com.pigdroid.job.model.TaskContext;
import com.pigdroid.job.model.entity.JobExecution;
import com.pigdroid.job.service.agent.AbstractAgent;
import com.pigdroid.job.service.agent.MethodRunnerAgent;

@Component
public class AgentFactory implements ApplicationEventPublisherAware {

	private ApplicationEventPublisher publisher;

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	AbstractAgent createAgent(
			JobExecution execution) {
//		switch (execution.getJobTemplate().getAgentType()) {
		// TODO check the agentType and switch
//		SystemCommandAgent
//		MethodRunnerAgent
//		}

		return new MethodRunnerAgent((evt) -> publisher.publishEvent(evt), new TaskContext() {

			@Override
			public String getProperty(String key) {
				//TODO precache a map
				return execution.getJobTemplate().getProperties()
						.stream().filter(each -> each.getName().equals(key))
						.map(each -> each.getValue()).findFirst().orElse(null);
			}

			@Override
			public Long getId() {
				return execution.getId();
			}

		}) {};
	}

}
