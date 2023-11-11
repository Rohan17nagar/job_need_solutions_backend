package com.jobneedsolutions.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jobneedsolutions.entity.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

	State findByStateName(String stateName);

	@Query(nativeQuery = true, value = "SELECT c.city_id AS cityId,c.city_name AS cityName, c.state_id AS stateId "
			+ "FROM city c JOIN state s ON s.state_id = c.state_id "
			+ "WHERE s.state_id = :stateId")
	List<Map<String, Object>> findCitiesByStateId(@Param("stateId") int stateId);
	
	
}
