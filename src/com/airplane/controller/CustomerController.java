package com.airplane.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airplane.po.Customerinfo;
import com.airplane.service.Customerservise;

@Controller
public class CustomerController {
	
	@Autowired
	private Customerservise customerservise;
	
	//删除部门根据id
	@RequestMapping("deleteCustomerinfo")
	public @ResponseBody String deleteCustomerinfo(int id,HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		int count=customerservise.deleteCustomerinfo(id);
		return count>=1?"1":"0";
	}
	//查询部门  没有条件
	@RequestMapping("selectCustomerinfo")
	public @ResponseBody HashMap<String, Object> select(String name,HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		if(customerservise.select(name)==null){
			return null;
		}else{
			List<Customerinfo> pages=customerservise.select(name);
			HashMap<String, Object> result = new HashMap<>();
			result.put("data", pages);
			return result;
		}
		
	}
	//修改部门  根据id
	@RequestMapping("updateCustomerinfo")
	public @ResponseBody String UpdateCustomerinfo(Customerinfo customerinfo,HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
		int count = customerservise.updateCustomerinfo(customerinfo);
		return count>=1?"1":"0";
	}
	//添加部门
	@RequestMapping("insertCustomerinfo")
	@ResponseBody
	public String insertCustomerinfo(Customerinfo customerinfo,HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
		int count = customerservise.addCustomerinfo(customerinfo);
		return count>=1?"1":"0";
	}
	

}
