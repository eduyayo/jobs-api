package com.pigdroid.job.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.scheduling.TaskScheduler;

import com.pigdroid.job.model.entity.JobExecution;
import com.pigdroid.job.model.entity.JobTemplate;
import com.pigdroid.job.repository.JobTemplateRepository;
import com.pigdroid.job.service.agent.AbstractAgent;
import com.pigdroid.job.util.MockitoTest;

public class JobTemplateServiceTest extends MockitoTest {
	
    @InjectMocks
    JobTemplateService fixture;

	@Mock
	JobExecutionService jobExecutionService;

	@Mock(answer = Answers.RETURNS_MOCKS)
	AgentFactory agentFactory;

	@Mock
	private TaskScheduler taskScheduler;
	
	@Mock
	private JobTemplateRepository jobRepository;
	
	@BeforeEach
	public void setup() {
		when(jobRepository.findById(Mockito.anyLong())).thenReturn(getMockTemplate());
		when(jobExecutionService.save(Mockito.any(JobExecution.class))).thenReturn(getMockExecution());
	}

	private JobExecution getMockExecution() {
		JobExecution value = JobExecution.builder()
				.id(123L)
				.jobTemplate(getMockTemplate().get())
				.scheduledDate(new Date())
				.scheduledTime(new Date())
				.build();
		return value;
	}

	private Optional<JobTemplate> getMockTemplate() {
		JobTemplate value = JobTemplate.builder()
				.id(456L)
				.build();
		return Optional.of(value);
	}

	@Test
	public void testMockitoIsConfiguredProperly() {
		assertNotNull(fixture);
	}

	@Test
	public void testTaskGetCreatedThenScheduled() {
		Long id = 1L;
		assertNotNull(fixture.createExecution(id));
		InOrder verifier = inOrder(jobExecutionService, taskScheduler);
		verifier.verify(jobExecutionService, times(1)).save(Mockito.any(JobExecution.class));
		verifier.verify(taskScheduler, times(1)).schedule(Mockito.any(AbstractAgent.class), Mockito.any(Date.class));
	}

}
