package com.airplane.service;

import java.util.List;

import com.airplane.po.Menu1info;
import com.airplane.view.UserView;

public interface PowerServise {
	
	
	//修改权限信息
	public int updatePower(String no , String[] m2no);
	//查询权限列表
	public List<UserView> selectPower(String department);
	
	public List<Menu1info> listMenu();

}
