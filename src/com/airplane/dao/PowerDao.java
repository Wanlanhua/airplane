package com.airplane.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.airplane.po.Powerinfo;

public interface PowerDao extends JpaRepository<Powerinfo, Integer>,JpaSpecificationExecutor<Powerinfo>{
	
	List<Powerinfo> findByNo(String name);
	
	@Modifying
    @Query(value = "delete from Powerinfo where no =?",nativeQuery = true)
    void deleteByNo(String no);
	
	
}
