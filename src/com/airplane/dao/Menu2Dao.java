package com.airplane.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.airplane.po.Menu2info;


public interface Menu2Dao extends JpaRepository<Menu2info,Integer>,JpaSpecificationExecutor<Menu2info>{

}
