package com.pigdroid.job.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString

@Entity
public class JobTemplateProperty {

	@Id
    @GeneratedValue
    @Getter
    private Long id;

	@ManyToOne
	private JobTemplate jobTemplate;

	@Getter @Setter
	private String name;

	@Getter @Setter
	private String value;

}
