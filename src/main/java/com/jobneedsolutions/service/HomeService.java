package com.jobneedsolutions.service;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jobneedsolutions.entity.City;
import com.jobneedsolutions.entity.JobCategory;
import com.jobneedsolutions.entity.State;
import com.jobneedsolutions.repository.CityRepository;
import com.jobneedsolutions.repository.JobCategoryRepository;
import com.jobneedsolutions.repository.StateRepository;

@Service
public class HomeService {

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private JobCategoryRepository jobCategoryRepository;
	
	public ResponseEntity<String> addState(State state) {

		try {
			State existingState = stateRepository.findByStateName(state.getStateName());

			if (existingState != null) {
				throw new RuntimeException("state already exists with name " + state.getStateName());
			}
			System.out.println("sss" + state);
			stateRepository.save(state);
			return new ResponseEntity<String>("state added successfully", HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<String> addCity(City city) {
		try {
			City existingCity = cityRepository.findByCityName(city.getCityName());

			if (existingCity != null) {
				throw new RuntimeException("city already exists with name " + city.getCityName());
			}

			System.out.println("city" + city);

			cityRepository.save(city);
			return new ResponseEntity<String>("city added successfully", HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	public ResponseEntity<?> getCitiesByStateId(int stateId) {

		List<Map<String, Object>> cities = stateRepository.findCitiesByStateId(stateId);

		System.out.println("cities>>" + cities);

		return new ResponseEntity<List<?>>(cities, HttpStatus.OK);
	}

	public ResponseEntity<?> getAllStatesAndCities() {
		try {
			List<State> states = stateRepository.findAll();

			return new ResponseEntity<List<?>>(states, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<String> addJobCategories(List<String> jobCategories) {
	
		for (String category : jobCategories) {
			JobCategory jobCategory = new JobCategory();
			jobCategory.setCategory(category);
			
			jobCategoryRepository.save(jobCategory);
			
		}
		
		return new ResponseEntity<String>("Categories added Successfully.",HttpStatus.OK);
	}

	public ResponseEntity<?> findAllJobCategories() {
		List<JobCategory> jobCategories  = jobCategoryRepository.findAll();
		return new ResponseEntity<List<JobCategory>>(jobCategories,HttpStatus.OK);
	}
}
