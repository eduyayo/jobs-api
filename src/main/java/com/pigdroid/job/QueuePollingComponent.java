package com.pigdroid.job;

import java.util.concurrent.PriorityBlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.pigdroid.job.service.agent.AbstractAgent;

@Component
public class QueuePollingComponent {

	@Autowired
	private PriorityBlockingQueue<AbstractAgent> priorityBlockingQueue;
	private volatile boolean running;

	@Async
	public void doPoll() {
		while(this.running) {
			AbstractAgent agent = next();
			if (agent != null) {
				//schedule
			}
		}
	}

	private AbstractAgent next() {
		try {
			return priorityBlockingQueue.take();
		} catch (InterruptedException e) {
			running = false;
		}
		return null;
	}

}
