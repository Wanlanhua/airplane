package com.airplane.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airplane.po.Airline;
public interface AirlineDao extends JpaRepository<Airline, Integer>{
	
 
	Airline findByName(String name);
}

	 
