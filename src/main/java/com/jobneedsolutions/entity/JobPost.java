package com.jobneedsolutions.entity;


import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class JobPost {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int jobId;
	private String jobTitle;
	private String jobDescription;
	private String location;
	private String salaryRange;
	private String experienceRange;
	private Date applicationDeadline;
	private int openings;
	private List<String> skillsRequired;
	private String educationRequired;
	private String employementType;
	
//	@OneToOne
//	private JobCategory jobCategory;
	
	@ManyToOne
	@JoinColumn(name = "employerId")
	@JsonBackReference
	private Employer employer;	
	
	
	@OneToMany(mappedBy = "jobPost", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference(value = "jobpost-application")
	private List<JobApplications> jobApplications;
	
}
