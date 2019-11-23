package com.airplane.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.airplane.po.Menu1info;

public interface Menu1Dao extends JpaRepository<Menu1info,Integer>,JpaSpecificationExecutor<Menu1info>{

}
