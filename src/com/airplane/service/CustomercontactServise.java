package com.airplane.service;

import java.util.List;

import com.airplane.po.Customercontactinfo;
import com.airplane.po.Customerinfo;


public interface CustomercontactServise {
	
	public int addCustomercontact(Customercontactinfo customercontact);

	int delectCustomercontact(int id);

	int updateCustomercontact(Customercontactinfo customercontact);
	
	public List<Customercontactinfo> select(String name);
	
	public List<Customerinfo> selectNo();
	
	 
}
