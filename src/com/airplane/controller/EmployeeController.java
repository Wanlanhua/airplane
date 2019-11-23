package com.airplane.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.airplane.po.Employeeinfo;
import com.airplane.service.Customerservise;
import com.airplane.service.EmployeeServise;

import net.coobird.thumbnailator.Thumbnails;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeServise employeeServise;
	@Autowired
	private Customerservise customerservise;
	
	//查询所有员工  没有条件
	@RequestMapping("selectEmployeeinfo")
	public @ResponseBody HashMap<String, Object> selectEmployeeinfo(String name,HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		if(employeeServise.findDepartmentByNo(name)==null){
			List<Employeeinfo> list=employeeServise.selectAll();
			HashMap<String, Object> result = new HashMap<>();
			result.put("data", list);
			return result;
		}else{
			String department=employeeServise.findDepartmentByNo(name);
			List<Employeeinfo> list=employeeServise.selectEmployee(department);
			HashMap<String, Object> result = new HashMap<>();
			result.put("data", list);
			return result;
		}
		
		
	}
	//删除员工根据id
	@RequestMapping("deleteEmployeeinfo")
	public @ResponseBody String deleteEmployeeinfo(int id,HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		int count=employeeServise.delectEmployee(id);
		return count>=1?"1":"0";
	}
	//根据id修改员工  有图片上传图片没有图片路数据库图片路径不变
	@RequestMapping("updateEmployeeinfo")
	public @ResponseBody String updateEmployeeinfo(Employeeinfo employeeinfo, HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		MultipartHttpServletRequest req =(MultipartHttpServletRequest)request;
		MultipartFile multipartFile =  req.getFile("file"); //对应前端页面的name值
		if(multipartFile!=null){
			String filePath=request.getSession().getServletContext().getRealPath("/image/");
			String name = UUID.randomUUID().toString().replaceAll("-", "");
			File dir = new File(filePath);
			if (!dir.exists()) {
				 dir.mkdir();
				 }              
			//生成一个新的文件名fileName
			String picName =name + "."+ "jpg";
			File file1  =  new File(filePath,picName);
			employeeinfo.setPath("/image/"+picName);
			int count=employeeServise.updateEmployee(employeeinfo);
			if(count>=1){
				multipartFile.transferTo(file1); 
				Thumbnails.of(filePath+picName) 
		        .size(400, 500)
		        .outputQuality(0.5f) 
		        .toFile(filePath+picName);
				return "1";
			}else{
				return "0";
			}
			
		}else{
			int count=employeeServise.updateEmployee(employeeinfo);
			return count>=1?"1":"0";
		}
		
	}
	
	
	//查询员工添加页的下拉框
	@RequestMapping("selectName")
	public @ResponseBody HashMap<String, Object> selectName(HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
		HashMap<String, Object> result = new HashMap<>();
		HashMap<String, Object> result2 = new HashMap<>();
		Map<String, String> selectNmme = employeeServise.selectNmme();
		if(selectNmme==null){
			result.put("status", "0");
			result2.put("data", result);
			return result2;
		}else{
			result.put("result", selectNmme);
			result.put("status", "1");
			result2.put("data", result);
			return result2;
		}
		
		
	}
	
	//添加员工  上传图片  名字为  file
	@RequestMapping("insertEmployeeinfo")
	public @ResponseBody String insertEmployeeinfo(Employeeinfo employeeinfo, HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		MultipartHttpServletRequest req =(MultipartHttpServletRequest)request;
		MultipartFile multipartFile =  req.getFile("file"); //对应前端页面的name值
		String filePath=request.getSession().getServletContext().getRealPath("/image/");
		String name = UUID.randomUUID().toString().replaceAll("-", "");
		File dir = new File(filePath);
		if (!dir.exists()) {
			 dir.mkdir();
			 }              
		//生成一个新的文件名fileName
		String picName =name + "."+ "jpg";
		File file1  =  new File(filePath,picName);
		employeeinfo.setPath("/image/"+picName);
		int count=employeeServise.saveEmployee(employeeinfo);
		if(count>=1){
			multipartFile.transferTo(file1); 
			return "1";
		}else{
			return "0";
		}
		
	}

}
