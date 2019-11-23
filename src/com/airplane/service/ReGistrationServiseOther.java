package com.airplane.service;

import java.util.List;
import java.util.Map;

import com.airplane.po.Registration;

public interface ReGistrationServiseOther {

	//转出维修记录
	public int UpdateRegistration(Registration registration);
	
	//查询登陆人的所有单
	public List<Registration> selectone(String no);
	
	//查询本项目部的所有人
	public Map<String, String>  selectother(String name);
	
	
	//根据id修改
	public List<Registration> findById(int id);
}
