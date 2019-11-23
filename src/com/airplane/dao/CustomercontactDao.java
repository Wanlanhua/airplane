package com.airplane.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.airplane.po.Customercontactinfo;

public interface CustomercontactDao extends JpaSpecificationExecutor<Customercontactinfo>, JpaRepository<Customercontactinfo, Integer>{
	
	Customercontactinfo findByName(String name);
	//List<Customercontactinfo> findByNo(String no);
}
