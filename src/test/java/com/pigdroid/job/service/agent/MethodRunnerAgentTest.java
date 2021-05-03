package com.pigdroid.job.service.agent;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.function.Consumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.pigdroid.job.model.TaskContext;
import com.pigdroid.job.model.event.JobStatusEvent;
import com.pigdroid.job.util.MockitoTest;

public class MethodRunnerAgentTest extends MockitoTest {
	
	private static boolean done;

	public static void methodToInvoke() {
		done = true;
	}
	
	@BeforeEach
	public void setup() {
		done = false;
	}

	@Test
	public void testCanRunMethodsAndPostEvents() {
		TaskContext context = Mockito.mock(TaskContext.class);
		Mockito.when(context.getProperty("className")).thenReturn(getClass().getName());
		Mockito.when(context.getProperty("methodName")).thenReturn("methodToInvoke");
		Consumer<JobStatusEvent> consumer = Mockito.mock(Consumer.class);
		MethodRunnerAgent fixture = new MethodRunnerAgent(consumer, context);

		Mockito.verify(consumer, Mockito.times(1)).accept(Mockito.any());
		assertFalse(done);
		
		fixture.run();
		
		assertTrue(done);
		Mockito.verify(consumer, Mockito.times(3)).accept(Mockito.any());
	}
	
	@Test
	public void testDontCrashEvenWhenNullPointerThrown() {
		TaskContext context = Mockito.mock(TaskContext.class);
		@SuppressWarnings("unchecked")
		Consumer<JobStatusEvent> consumer = Mockito.mock(Consumer.class);
		MethodRunnerAgent fixture = new MethodRunnerAgent(consumer, context);

		Mockito.verify(consumer, Mockito.times(1)).accept(Mockito.any());
		assertFalse(done);
		
		fixture.run();
		
		assertFalse(done);
		ArgumentCaptor<JobStatusEvent> captor = ArgumentCaptor.forClass(JobStatusEvent.class);
		Mockito.verify(consumer, Mockito.times(3)).accept(captor.capture());
		
		List<JobStatusEvent> values = captor.getAllValues();
		JobStatusEvent errorEvent = values.get(values.size() - 1);
		
		assertNotNull(errorEvent);
		assertEquals(NullPointerException.class, errorEvent.getWhat().getClass());
		
	}
	
}
