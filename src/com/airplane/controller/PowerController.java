package com.airplane.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airplane.service.Customerservise;
import com.airplane.service.EmployeeServise;
import com.airplane.service.PowerServise;
import com.airplane.view.UserView;

@Controller
public class PowerController {
	
	@Autowired
	public PowerServise powerservise;
	@Autowired
	private Customerservise customerservise;
	@Autowired
	private EmployeeServise employeeServise;
	//查询权限数据
	@RequestMapping("selectPower")
	public @ResponseBody HashMap<String, Object> selectPower(String name,HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		if(employeeServise.findDepartmentByNo(name)==null){
			List<UserView> selectPower = powerservise.selectPower(null);
			HashMap<String, Object> result = new HashMap<>();
			result.put("data", selectPower);
			return result;
		}else{
			String department=employeeServise.findDepartmentByNo(name);
			List<UserView> selectPower = powerservise.selectPower(department);
			HashMap<String, Object> result = new HashMap<>();
			result.put("data", selectPower);
			return result;
		}
		
	} 
	//更新权限
	@RequestMapping("updatePower")
	public @ResponseBody String updatePower(String no,String[] m2no,HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
		if(m2no!=null){
			int count = powerservise.updatePower(no, m2no);
			return count>=1?"1":"0";
		}else{
			return "0";
		}
	}
	//查询现有所有一级菜单
	@RequestMapping("menus")
	@ResponseBody
	public Map<String, Object> menus(HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("data", powerservise.listMenu());
		return res;
	}
	

}
