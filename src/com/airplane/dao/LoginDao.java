package com.airplane.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.airplane.po.Logininfo;

public interface LoginDao extends JpaRepository<Logininfo,Integer>,JpaSpecificationExecutor<Logininfo>{
	
	Logininfo findByName(String name);
	Logininfo findByNameAndPassword(String name,String password);
}
