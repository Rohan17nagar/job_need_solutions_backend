package com.jobneedsolutions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobneedsolutions.entity.City;
import com.jobneedsolutions.entity.JobCategory;
import com.jobneedsolutions.entity.State;
import com.jobneedsolutions.service.HomeService;

@RestController
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private HomeService homeService;

	/**
	 * Method:: POST add a new state
	 * 
	 * @param state
	 * @return
	 */
	@PostMapping("/addState")
	public ResponseEntity<String> addState(@RequestBody State state) {
		return homeService.addState(state);
	}

	/**
	 * Method::POST Adding a new city
	 * 
	 * @param city
	 * @return
	 */
	@PostMapping("/addCity")
	public ResponseEntity<String> addCity(@RequestBody City city) {
		return homeService.addCity(city);
	}

	/**
	 * Method::GET Get all the cities in a state by using state id
	 * 
	 * @param stateId
	 * @return List of cities with stateId's
	 */
	@GetMapping("/getCitiesByStateId/{stateId}")
	public ResponseEntity<?> getCitiesByStateId(@PathVariable int stateId) {
		return homeService.getCitiesByStateId(stateId);
	}

	/**
	 * Method::GET Get all the states and cities in those states
	 * 
	 * @return List of states with cities
	 */
	@GetMapping("/getAllCitiesAndStates")
	public ResponseEntity<?> getAllStatesAndCities() {
		return homeService.getAllStatesAndCities();
	}
	/**
	 * Method :: Post
	 * 
	 * Add multiple job categories
	 * @param jobCategories
	 * @return
	 */
	@PostMapping("/addJobCategories")
	public ResponseEntity<String> addJobCategories(@RequestBody List<String> jobCategories){
		return homeService.addJobCategories(jobCategories);
	}
	
	/**
	 * Method:: find all job categories
	 * 
	 * @return
	 */
	@GetMapping("/findAllJobCategories")
	public ResponseEntity<?> findAllJobCategories(){
		return homeService.findAllJobCategories();
	}

}
