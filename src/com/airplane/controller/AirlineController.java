package com.airplane.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airplane.po.Airline;
import com.airplane.service.AirlineServise;

@Controller
public class AirlineController {

	@Autowired
	private AirlineServise airservise;
	//查询初始化航空公司无条件
	@RequestMapping("selectAirline")
	public @ResponseBody HashMap<String, Object> selectAirline(HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
		List<Airline> selectAirline = airservise.selectAirline();
		HashMap<String, Object> result = new HashMap<>();
		result.put("data", selectAirline);
		return result;
	}
	//添加航空公司
	@RequestMapping("insertAirline")
	public @ResponseBody String insertAirline(Airline airline,HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
		int count = airservise.saveAirline(airline);
		return count>=1?"1":"0";
	}
	//更新航空公司根据id
	@RequestMapping("updateAirline")
	public @ResponseBody String updateAirline(Airline airline,HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
		int count = airservise.updateAirline(airline);
		return count>=1?"1":"0";
	}
	//删除航空公司根据id
	@RequestMapping("deleteAirline")
	public @ResponseBody String deleteAirline(int id,HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
		int count = airservise.delectAirline(id);
		return count>=1?"1":"0";
	}
	
}
