package com.airplane.service;

import java.util.Date;
import java.util.List;

import com.airplane.po.Registration;

public interface RegistrationServise {
	
	//添加
	public int saveRegistration(Registration registration);
	//删除
	public int deleteRegistration(int id);
	//查询
	public List<Registration> selectRegistration(Registration registration,Date start ,Date stop);
	//修改
	public int updateRegistration(Registration registration);
	//修改（根据no）
	public List<Registration> selectByNo(String no);
	//按条件查询
	public List<Registration> selectByConditions(Registration registration,Date start ,Date stop,String department);
}
