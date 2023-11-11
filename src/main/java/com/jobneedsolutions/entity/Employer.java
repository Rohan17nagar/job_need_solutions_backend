package com.jobneedsolutions.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Employer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int employerId;
	private String companyName;
	private String companyDescription;
	private String location;
	private String contactInfo;
	
	
	@OneToMany(mappedBy = "employer",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<JobPost> jobPost;
	
}
