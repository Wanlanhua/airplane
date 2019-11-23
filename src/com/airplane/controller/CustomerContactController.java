package com.airplane.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airplane.po.Customercontactinfo;
import com.airplane.po.Customerinfo;
import com.airplane.service.CustomercontactServise;

@Controller
public class CustomerContactController {
	
	@Autowired
	private CustomercontactServise ccservise;
	
	//添加部门联系人
	@RequestMapping("addCustomercontactinfo")
	@ResponseBody
	public String addCustomercontact(Customercontactinfo customercontact,HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
		int count=ccservise.addCustomercontact(customercontact);
		return count>=1?"1":"0";
		
	}

	//删除部门联系人根据id
	@RequestMapping("delectCustomercontactinfo")
	@ResponseBody
	public String delect(int id,HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
		int count=ccservise.delectCustomercontact(id);
		return count>=1?"1":"0";
		
	}
	
	//修改部门联系人  根据id
	@RequestMapping("updateCustomercontactinfo")
	@ResponseBody
	public String updateCustomercontact(Customercontactinfo customercontact,HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
		int count=ccservise.updateCustomercontact(customercontact);
		return count>=1?"1":"0";
		
	}
	//查询部门联系人 name查重
	@RequestMapping("selectCustomercontactinfo")
	@ResponseBody
	public HashMap<String, Object> selectCustomercontactinfo(String name,HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
		if(ccservise.select(name)==null){
			return null;
		}else{
			List<Customercontactinfo> list=ccservise.select(name);
			HashMap<String, Object> result = new HashMap<>();
			result.put("data", list);
			return result;
		}
		
	}
	
	//查询部门 -->部门联系人添加页 部门下拉框
	@RequestMapping("selectNo")
	@ResponseBody
	public HashMap<String, Object> selectNo(HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
		List<Customerinfo> list=ccservise.selectNo();
		HashMap<String, Object> result = new HashMap<>();
		result.put("data", list);
		return result;
		
		
	}
	
	
}
