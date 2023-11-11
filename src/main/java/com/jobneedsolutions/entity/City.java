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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cityId;
	private String cityName;

	@ManyToOne
	@JoinColumn(name = "stateId")
	@JsonBackReference
	private State state;
}
