package com.airplane.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airplane.po.Employeeinfo;
import com.airplane.po.Logininfo;
import com.airplane.service.Customerservise;
import com.airplane.service.EmployeeServise;
import com.airplane.service.LoginServise;
@Controller
public class LoginController {
	
	@Autowired
	private LoginServise loginServise;
	@Autowired
	private Customerservise customerservise;
	@Autowired
	private EmployeeServise employeeServise;
	
	//删除登陆号
	@RequestMapping("deleteLogininfo")
	public @ResponseBody String deleteLogininfo(int id,HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		int count=loginServise.delectLogin(id);
		return count>=1?"1":"0";
	}
	//添加登陆号   只添加是员工的登录号
	@RequestMapping("insertLogininfo")
	public @ResponseBody String insertLogininfo(Logininfo login,HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		login.getName();
		int count=loginServise.saveLogin(login);
		return count>=1?"1":"0";
	}
	
	//跟新登陆号  根据id
	@RequestMapping("updateLogininfo")
	public @ResponseBody String updateLogininfo(Logininfo login,HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		int count=loginServise.updateLogin(login);
		return count>=1?"1":"0";
	}
	//查询所有的登陆号
	@RequestMapping("selectLogininfo")
	public @ResponseBody HashMap<String, Object> selectLogininfo(String name,HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		if(employeeServise.findDepartmentByNo(name)==null){
			List<Logininfo> pages=loginServise.selectLogin(null);
			HashMap<String, Object> result = new HashMap<>();
			result.put("data", pages);
			return result;
		}else{
			String department=employeeServise.findDepartmentByNo(name);
			List<Logininfo> pages=loginServise.selectLogin(department);
			HashMap<String, Object> result = new HashMap<>();
			result.put("data", pages);
			return result;
		}
		
	}
	//查询没有登陆号的员工-->添加登录号页的 登录号下拉框
	@RequestMapping("select")
	public @ResponseBody HashMap<String, Object> selest(HttpServletResponse response,String name) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		HashMap<String, Object> result = new HashMap<>();
		HashMap<String, Object> result2 = new HashMap<>();
		if(employeeServise.findDepartmentByNo(name)==null){
			List<String> count=loginServise.select(null);
			if(count==null){
				result.put("status", "0");
				result2.put("data", result);
				return result2;
			}else{
				result.put("result", count);
				result.put("status", "1");
				result2.put("data", result);
				return result2;
			}
		}else{
			String department=employeeServise.findDepartmentByNo(name);
			List<String> count=loginServise.select(department);
			if(count==null){
				result.put("status", "0");
				result2.put("data", result);
				return result2;
			}else{
				result.put("result", count);
				result.put("status", "1");
				result2.put("data", result);
				return result2;
			}
		}
		
		
		
	}

	//登陆
	@RequestMapping("Login")
	public @ResponseBody HashMap<String, Object> login(Logininfo logininfo,HttpServletResponse response,HttpSession Session) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		List<String> count=loginServise.login(logininfo.getName().toString().trim(),logininfo.getPassword().toString().trim());
		HashMap<String, Object> result = new HashMap<>();
		HashMap<String, Object> result2 = new HashMap<>();
		if(count==null){
			result.put("status", "0");
			result2.put("data", result);
			return result2;
		}else{
			Session.setAttribute("name", logininfo.getName().toString());
			result.put("status", "1");
			result.put("result", count);
			result2.put("data", result);
			return result2;
		}
	} 
	
	//查询此时登陆账号拥有的菜单
	@RequestMapping("selectmenus")
	public @ResponseBody HashMap<String, Object> selectmenus(String name,HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		List<String> count=loginServise.menus(name);
		HashMap<String, Object> result = new HashMap<>();
		result.put("data", count);
		return result;
	}
	
	
	
}
