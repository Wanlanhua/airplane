package com.airplane.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.airplane.po.Customerinfo;

public interface CustomerDao extends JpaSpecificationExecutor<Customerinfo>,JpaRepository<Customerinfo, Integer>{
	
	Customerinfo findByNo(String no);

	Customerinfo findByName(String name);
//	@Modifying
//	@Query(value="delete from Customerinfo where no = ?",nativeQuery=true)
//	int delect(String no);

}
