package com.pigdroid.job.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

public abstract class MockitoTest {
	
	private AutoCloseable closeable;

	@BeforeEach
	public void setupMock() {
        this.closeable = MockitoAnnotations.openMocks(this);
	}

	@AfterEach
	public void tearDownMock() throws Exception {
        closeable.close();
	}	
	
}
