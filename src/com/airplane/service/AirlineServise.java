package com.airplane.service;

import java.util.List;

import com.airplane.po.Airline;

public interface AirlineServise {
	
	//查询
	List<Airline> selectAirline();
	//添加
	int saveAirline(Airline airline);
	//删除
	int delectAirline(int id);
	//修改
	int updateAirline(Airline airline);
}
