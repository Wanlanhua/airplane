package com.airplane.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airplane.po.Bridgeno;
import com.airplane.service.BridgenoServise;

@Controller
public class BridegenoContorller {
	

	@Autowired
	private BridgenoServise briservise;
	
	//查询桥位号无条件
	@RequestMapping("selectBridgeno")
	public @ResponseBody HashMap<String, Object> selectBridgeno(HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
		List<Bridgeno> selectBridgeno = briservise.selectBridgeno();
		HashMap<String, Object> result = new HashMap<>();
		result.put("data", selectBridgeno);
		return result;
	}
	@RequestMapping("insertBridgeno")
	public @ResponseBody String insertBridgeno(Bridgeno Bridgeno,HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
		int count = briservise.saveBridgeno(Bridgeno);
		return count>=1?"1":"0";
	}
	
	@RequestMapping("updateBridgeno")
	public @ResponseBody String updateBridgeno(Bridgeno Bridgeno,HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
		int count = briservise.updateBridgeno(Bridgeno);
		return count>=1?"1":"0";
	}
	@RequestMapping("deleteBridgeno")
	public @ResponseBody String deleteBridgeno(int id,HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
		int count = briservise.delectBridgeno(id);
		return count>=1?"1":"0";
	}
	
}
