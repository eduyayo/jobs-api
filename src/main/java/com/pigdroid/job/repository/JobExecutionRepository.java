package com.pigdroid.job.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pigdroid.job.model.entity.JobExecution;

@Repository
public interface JobExecutionRepository extends PagingAndSortingRepository<JobExecution, Long> {

}
