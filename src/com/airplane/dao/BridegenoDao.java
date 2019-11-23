package com.airplane.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airplane.po.Bridgeno;

public interface BridegenoDao extends JpaRepository<Bridgeno, Integer>{
	
	Bridgeno findByNo(String name);

}
