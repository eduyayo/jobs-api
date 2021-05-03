package com.pigdroid.job.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pigdroid.job.model.entity.JobExecution;
import com.pigdroid.job.service.JobExecutionService;

@RestController
@RequestMapping("/execution")
//TODO abstract query controllers like the pagination services
public class JobExecutionQueryController {

	@Autowired
	private JobExecutionService service;

	@GetMapping("/")
	public ResponseEntity<Iterable<JobExecution>> getAll() {
		return ResponseEntity.of(Optional.ofNullable(service.findAll()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<JobExecution> get(@PathVariable("id") Long id) {
		return ResponseEntity.of(service.findById(id));
	}

}
