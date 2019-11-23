package com.airplane.service;


import java.util.List;

import com.airplane.po.Customerinfo;


public interface Customerservise {
	
	public int addCustomerinfo(Customerinfo customerinfo);
	
	public int deleteCustomerinfo(int id);
	
	public int updateCustomerinfo(Customerinfo customerinfo);

	public List<Customerinfo> select(String name);
	
//	根据员工编号查询所属项目部
	public String selectCustomerinfoByName(String name);
}
