package com.pigdroid.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pigdroid.job.JobApplicationListener;

public class TestJobs {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobApplicationListener.class);

	private TestJobs() {

	}

	public static void doShortJob() {
		LOGGER.info("running a short job...");
		LOGGER.info("...");
		LOGGER.info("done!");

	}

	public static void doLongLastingJob() throws InterruptedException {
		LOGGER.info("running a long job...");
		for (int i = 0; i < 10; i++) {
			Thread.sleep(1_000L);
			LOGGER.info("... ... ...");
		}
		LOGGER.info("done!");
	}

	public static void doErrorJob() {
		LOGGER.info("crashing a job...");
		throw new NullPointerException();
	}

}
