package com.pigdroid.job.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
public class JobTemplate {

	@Id
    @GeneratedValue
    @Getter
    @EqualsAndHashCode.Include
    private Long id;

	@Getter @Setter
	private String name;

	@Getter @Setter
	@OneToMany(cascade = CascadeType.ALL)
	private List<JobTemplateProperty> properties;

	//TODO enum?
	@Getter @Setter
	@Builder.Default
	private String agentType = "MethodRunner";


}
