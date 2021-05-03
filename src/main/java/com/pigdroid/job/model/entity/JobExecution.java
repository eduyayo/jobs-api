package com.pigdroid.job.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.pigdroid.job.model.JobExecutionStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class JobExecution {

	@Id
    @GeneratedValue
    @Getter
    @EqualsAndHashCode.Include
    private Long id;

	@ManyToOne
	@Getter @Setter
	private JobTemplate jobTemplate;

	@Temporal(TemporalType.DATE)
	@Getter @Setter
	private java.util.Date scheduledDate;

	@Temporal(TemporalType.TIME)
	@Getter @Setter
	private java.util.Date scheduledTime;

	@Temporal(TemporalType.DATE)
	@Getter @Setter
	private java.util.Date startDate;

	@Temporal(TemporalType.TIME)
	@Getter @Setter
	private java.util.Date startTime;

	@Getter @Setter
	private JobExecutionStatus status;

}
