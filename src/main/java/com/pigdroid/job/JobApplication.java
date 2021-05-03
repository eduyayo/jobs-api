package com.pigdroid.job;

import java.util.concurrent.PriorityBlockingQueue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.pigdroid.job.service.agent.AbstractAgent;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class JobApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobApplication.class, args);
	}

	@Bean
	public PriorityBlockingQueue<AbstractAgent> priorityQueue() {
		return new PriorityBlockingQueue<AbstractAgent>();

	}

}
