package com.pigdroid.job.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pigdroid.job.model.entity.JobExecution;
import com.pigdroid.job.model.entity.JobTemplate;
import com.pigdroid.job.service.JobTemplateService;

@RestController
@RequestMapping("/template")
public class JobTemplateCommandController {

	@Autowired
	private JobTemplateService jobTemplateService;

	@PostMapping(value = "/", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<JobTemplate> create(@RequestBody JobTemplate template) {
		JobTemplate saved = jobTemplateService.save(template);
		return ResponseEntity.of(Optional.ofNullable(saved));
	}

	/**
	 * Runs inmediately.
	 * @param id
	 * @return
	 */
	@PostMapping(value = "/{id}/run")
	public ResponseEntity<JobExecution> run(@PathVariable("id") Long id) {
		JobExecution created = jobTemplateService.createExecution(id);
		return ResponseEntity.of(Optional.ofNullable(created));
	}

	/**
	 * Runs scheduled.
	 * @param id
	 * @return
	 */
	@PostMapping(value = "/{id}/schedule")
	public ResponseEntity<JobExecution> schedule(@PathVariable("id") Long id, @RequestParam("dateTime") @DateTimeFormat(pattern = "yyyy-MM-ddHH:mm:ss") Date date) {
		JobExecution created = jobTemplateService.createExecution(id, date);
		return ResponseEntity.of(Optional.ofNullable(created));
	}

}
