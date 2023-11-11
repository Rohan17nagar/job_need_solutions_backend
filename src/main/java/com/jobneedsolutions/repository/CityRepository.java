package com.jobneedsolutions.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jobneedsolutions.entity.City;

public interface CityRepository extends JpaRepository<City, Integer> {

	City findByCityName(String cityName);

	
	@Query(nativeQuery = true,value = "Select c.city_name from city c where state_id =:stateId")
	List<Map<String,Object>> findByStateId(@Param("stateId") int stateId);

}
