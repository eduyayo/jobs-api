package com.pigdroid.job.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pigdroid.job.model.entity.JobTemplate;

@Repository
public interface JobTemplateRepository extends PagingAndSortingRepository<JobTemplate, Long> {

}
