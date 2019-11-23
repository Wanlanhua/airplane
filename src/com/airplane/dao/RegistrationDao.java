package com.airplane.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.airplane.po.Registration;

public interface RegistrationDao extends JpaRepository<Registration, Integer> ,JpaSpecificationExecutor<Registration>{
	
	List<Registration> findByNo(String no);

}
