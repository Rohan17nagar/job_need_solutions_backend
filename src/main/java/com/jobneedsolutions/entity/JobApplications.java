package com.jobneedsolutions.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class JobApplications {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int jobApplicationId;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
	@JsonBackReference(value = "user-jobpost")
    private UserEntity user;
    
    @ManyToOne
    @JoinColumn(name = "job_post_id")
    @JsonBackReference(value = "jobpost-application")
    private JobPost jobPost;
}
