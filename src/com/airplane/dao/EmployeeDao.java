package com.airplane.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.airplane.po.Employeeinfo;


public interface EmployeeDao extends JpaRepository<Employeeinfo, Integer>,JpaSpecificationExecutor<Employeeinfo> {
	
	Employeeinfo findByNo(String no);
	//List<Employeeinfo> findByEdepartment(String no);

}
