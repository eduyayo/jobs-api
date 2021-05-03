package com.pigdroid.job.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pigdroid.job.model.entity.JobTemplate;
import com.pigdroid.job.service.JobTemplateService;

@RestController
@RequestMapping("/template")
//TODO abstract query controllers like the pagination services
public class JobTemplateQueryController {

	@Autowired
	private JobTemplateService jobTemplateService;

	@GetMapping("/")
	public ResponseEntity<Iterable<JobTemplate>> getAll() {
		return ResponseEntity.of(Optional.ofNullable(jobTemplateService.findAll()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<JobTemplate> get(@PathVariable("id") Long id) {
		return ResponseEntity.of(jobTemplateService.findById(id));
	}

}
