package com.jobneedsolutions.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class JobPostReponseDto {

	@JsonProperty("jobId")
	private int jobId;

	@JsonProperty("jobTitle")
	private String jobTitle;

	@JsonProperty("jobDescription")
	private String jobDescription;

	@JsonProperty("location")
	private String location;

	@JsonProperty("salaryRange")
	private String salaryRange;

	@JsonProperty("experienceRange")
	private String experienceRange;

	@JsonProperty("applicationDeadline")
	private Date applicationDeadline;

	@JsonProperty("openings")
	private int openings;

	@JsonProperty("skillsRequired")
	private List<String> skillsRequired;

	@JsonProperty("companyName")
	private String companyName;

	@JsonProperty("jobApplicationCount")
	private long jobApplicationCount;

	@JsonProperty("educationRequired")
	private String educationRequired;

	@JsonProperty("employementType")
	private String employementType;

	public JobPostReponseDto(int jobId, String jobTitle) {
		super();
		this.jobId = jobId;
		this.jobTitle = jobTitle;
	}

}
