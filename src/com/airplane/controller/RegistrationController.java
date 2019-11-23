package com.airplane.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.airplane.po.Registration;
import com.airplane.service.Customerservise;
import com.airplane.service.EmployeeServise;
import com.airplane.service.ReGistrationServiseOther;
import com.airplane.service.RegistrationServise;
import com.airplane.util.FillExcelDataWithTemplate;
import com.airplane.util.ResponseUtil;
import com.airplane.util.Test;
import com.airplane.util.UpLoadUtil;

@Controller
public class RegistrationController {
	
	@Autowired
	private RegistrationServise registrationServise;
	@Autowired
	private Customerservise customerservise;
	@Autowired
	private EmployeeServise employeeServise;
	@Autowired
	private ReGistrationServiseOther ServiseOther;
	
	//查询当前员工没有完成的一单
	@RequestMapping("selectRegistrationNo")
	public @ResponseBody HashMap<String,Object> selectRegistrationNo(String no,HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
		List<Registration> selectByNo = registrationServise.selectByNo(no);
		HashMap<String, Object> result = new HashMap<>();
		HashMap<String, Object> result2 = new HashMap<>();
		if(selectByNo.size()>0){
			result.put("status", "未完成");
			result.put("result", selectByNo);
			result2.put("data", result);
			return result2;
		}else{
			result.put("status", "签名完成");
			result.put("result", selectByNo);
			result2.put("data", result);
			return result2;
		}
	}
//	//查询所有单子
//	@RequestMapping("selectAll")
//	public @ResponseBody HashMap<String, Object> selectAll(String name,HttpServletResponse response){
//		response.addHeader("Access-Control-Allow-Origin", "*");
//		String department=customerservise.selectCustomerinfoByName(name); 
//		List<Registration> selectRegistration = registrationServise.selectRegistration(department);
//		HashMap<String, Object> result = new HashMap<>();
//		result.put("data", selectRegistration);
//		return result;
//	}
	//删除一条记录
	@RequestMapping("deleteById")
	public @ResponseBody String delectById(int id,HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
		int count = registrationServise.deleteRegistration(id);
		return count>=1?"1":"0";
	}
	//更新除图片之外的数据
	@RequestMapping("UpdateRegistration")
	public @ResponseBody String update(Registration registration,String ktime,String jtime,String ctime, HttpServletResponse response) throws ParseException{
		 response.addHeader("Access-Control-Allow-Origin", "*");
		 Date da=null;
		 //转换类型
		 SimpleDateFormat format2= new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		 if(!(ktime.trim().equals(""))){
			 registration.setStarttime(da=format2.parse(ktime.toString()));
 		 }
		 if(!(jtime.trim().equals(""))){
			 registration.setStoptime(da=format2.parse(jtime.toString()));
			 long nd = 1000 * 24 * 60 * 60;
		     long nh = 1000 * 60 * 60;
		     long nm = 1000 * 60;
		     long a=registration.getStoptime().getTime()-registration.getStarttime().getTime();
	         registration.setElapsedtime(String.valueOf(a% nd % nh / nm));
		 }
		 if(!(ctime.trim().equals(""))){
			 registration.setCreatedate(da=format2.parse(ctime.toString()));
		 }
		 int count = registrationServise.updateRegistration(registration);
		 return count>=1?"1":"0";
	}
	
	//上传三次图片，第二次接受第一张土图片的路径，第三次接收第一张和第第二张的路径
	@RequestMapping("insertRegistration")
	public @ResponseBody String insertRegistration(String step,String ctime,String ktime,String jtime,MultipartFile file1,MultipartFile file2,MultipartFile file3, Registration registration, HttpServletRequest request,HttpServletResponse response) 
			throws IllegalStateException, IOException, ParseException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		java.util.Date date = new java.util.Date();          // 获取一个Date对象
        Timestamp timeStamp = new Timestamp(date.getTime());     //   将日期时间转换为数据库中的timestamp类型
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        SimpleDateFormat format2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        Date da=null;
        int count = 0;
		switch (step) {
		case "1":
            registration.setStarttime(timeStamp);
			String filePath = UpLoadUtil.saveFile(file1, request.getSession().getServletContext().getRealPath("/"));
			registration.setAlternated("0");
			registration.setAlternateb(filePath);
			count = registrationServise.saveRegistration(registration);
			return count>=1?"1":"0";
		case "2":
			String filePath3 = UpLoadUtil.saveFile(file3, request.getSession().getServletContext().getRealPath("/"));
			da=format2.parse(ktime);
	        registration.setStarttime(da);
			registration.setCreatedate(timeStamp);
			registration.setSignature(filePath3);
			registration.setAlternated("1");
			count = registrationServise.updateRegistration(registration);
			return count>=1?"1":"0";

		case "3":
			String filePath2 = UpLoadUtil.saveFile(file2, request.getSession().getServletContext().getRealPath("/"));
            da=format2.parse(ktime);
            registration.setStarttime(da);
            registration.setStoptime(timeStamp);
            long a=registration.getStoptime().getTime()-registration.getStarttime().getTime();
            registration.setElapsedtime(String.valueOf(a% nd % nh / nm));
            registration.setAlternatec(filePath2);
            registration.setAlternated("2");
            count = registrationServise.updateRegistration(registration);
            return count>=1?"1":"0";
		default:
			return "0";
		}
		
	} 
	
	
	//根据条件查询
	@RequestMapping("selectByConditions")
	public @ResponseBody HashMap<String, Object> selectByConditions(Registration registration,String ktime ,String jtime,HttpServletResponse response,String name)throws ParseException{
			response.addHeader("Access-Control-Allow-Origin", "*");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date start=null;
			Date stop=null;
			if(!("".equals(ktime))&&"".equals(jtime)&&ktime==null&ktime==null){
				start=sdf.parse(ktime.toString());
				stop=sdf.parse(jtime.toString());
			}
			if(employeeServise.findDepartmentByNo(name)==null){
				List<Registration> selectAll=registrationServise.selectRegistration(registration, start, stop);
				HashMap<String, Object> result = new HashMap<>();
				result.put("data", selectAll);
				return result;
			}else{
				String department=employeeServise.findDepartmentByNo(name);
				List<Registration> selectRegistration = registrationServise.selectByConditions(registration, start, stop,department);
				HashMap<String, Object> result = new HashMap<>();
				result.put("data", selectRegistration);
				return result;
			}
	}
	
	//导出表
	@RequestMapping("exceluploud")
	public @ResponseBody String  excel(Registration registration,HttpServletRequest request,String ktime ,String jtime,HttpServletResponse response,String name)throws Exception{
		response.addHeader("Access-Control-Allow-Origin", "*");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date start=null;
		Date stop=null;
		String webPath=request.getServletContext().getRealPath("/");
		if(!("".equals(ktime))&&"".equals(jtime)&&ktime==null&ktime==null){
			start=sdf.parse(ktime.toString());
			stop=sdf.parse(jtime.toString());
		}
		if(employeeServise.findDepartmentByNo(name)==null){
			List<Registration> selectAll=registrationServise.selectRegistration(registration, start, stop);
			Workbook wb = FillExcelDataWithTemplate.fillExcelDataWithTemplate(selectAll, webPath+"work\\registration.xls");
			ResponseUtil.export(response,wb,"registration.xls");
			System.out.println(selectAll.size()+":1");
			return "1";
		}else{
			String department=employeeServise.findDepartmentByNo(name);
			List<Registration> selectAll=registrationServise.selectByConditions(registration, start, stop,department);
			Workbook wb = FillExcelDataWithTemplate.fillExcelDataWithTemplate(selectAll, webPath+"work\\registration.xls");
			ResponseUtil.export(response,wb,"registration.xls");
			System.out.println(selectAll.size()+":2");
			return "1";
		}
		
	}
	
	//查询同部门人
	@RequestMapping("selectother")
	@ResponseBody
	public Map<String, String> selectother(String name,HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
		if(employeeServise.findDepartmentByNo(name)==null){
			Map<String, String> selectother=ServiseOther.selectother(null);
			return selectother;
		}else{
			String department=employeeServise.findDepartmentByNo(name);
			Map<String, String> selectother =ServiseOther.selectother(department);
			return selectother;
		}
		
	}
	
	//转出功能接口(根据id修改)
	@RequestMapping("outregistration")
	@ResponseBody
	public String outregistration(Registration registration,String ktime,String jtime,String ctime,HttpServletResponse response) throws ParseException{
		response.addHeader("Access-Control-Allow-Origin", "*");
		 Date da=null;
		 //转换类型
		 SimpleDateFormat format2= new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		 if(!(ktime.toString().trim().equals(""))){
			 registration.setStarttime(da=format2.parse(ktime.toString()));
		 }
		 if(!(jtime.toString().trim().equals(""))){
			 da=format2.parse(jtime);
			 registration.setStoptime(da);
		 }
		 if(!(ctime.toString().trim().equals(""))){
			 registration.setCreatedate(da=format2.parse(ctime));
		 }
		int count = ServiseOther.UpdateRegistration(registration);
		return count>=1?"1":"0";
		
	}
	
	//查询本项人单
	@RequestMapping("selectone")
	@ResponseBody
	public HashMap<String, Object> selectone(String name,HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
		List<Registration> selectone = ServiseOther.selectone(name);
		HashMap<String, Object> result = new HashMap<>();
		result.put("data", selectone);
		return result;
		
	}
	
	//根据id查询
	@RequestMapping("selectById")
	@ResponseBody
	public HashMap<String, Object> selectById(int id,HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
		List<Registration> findById = ServiseOther.findById(id);
		HashMap<String, Object> result = new HashMap<>();
		result.put("data", findById);
		return result;
		
	}
	

}
