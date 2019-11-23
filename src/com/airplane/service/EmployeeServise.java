package com.airplane.service;


import java.util.List;
import java.util.Map;

import com.airplane.po.Employeeinfo;

public interface EmployeeServise {
	
	//添加员工
	public int saveEmployee(Employeeinfo employee);
	//删除员工
	public int delectEmployee(int id);
	//修改员工
	public int updateEmployee(Employeeinfo employee);
	//查询员工列表
	public List<Employeeinfo> selectEmployee(String department);
	
	//查询所有
	public List<Employeeinfo> selectAll();
	//
	public Map<String, String> selectNmme();
	//根据no查询部门编号
	public String findDepartmentByNo(String no);
}
