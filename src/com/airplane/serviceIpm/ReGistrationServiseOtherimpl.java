package com.airplane.serviceIpm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airplane.dao.RegistrationDao;
import com.airplane.po.Employeeinfo;
import com.airplane.po.Menu1info;
import com.airplane.po.Registration;
import com.airplane.service.Customerservise;
import com.airplane.service.EmployeeServise;
import com.airplane.service.ReGistrationServiseOther;
@Service
public class ReGistrationServiseOtherimpl implements ReGistrationServiseOther {

	@Autowired
	private RegistrationDao dao;
	@Autowired
	private EmployeeServise emservise;
	
	
	@Override
	public int UpdateRegistration(Registration registration) {
		// TODO Auto-generated method stub
		//根据id修改
		Registration save=dao.save(registration);
		if(save!=null){
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public List<Registration> selectone(String no) {
		// TODO Auto-generated method stub
		List<Registration> list=dao.findByNo(no);
		return list;
	}

	@Override
	public Map<String, String> selectother(String department) {
		// TODO Auto-generated method stubs
		Map<String, String> count = new HashMap<>();
		if(department!=null){
			List<Employeeinfo> list=emservise.selectEmployee(department);
			count = new HashMap<>();
			for(Employeeinfo info:list){
				count.put(info.getName(), info.getNo());
			}
			return count;
		}else{
			List<Employeeinfo> list=emservise.selectAll();
			count = new HashMap<>();
			for(Employeeinfo info:list){
				count.put(info.getName(), info.getNo());
			}
			return count;
		}
		
	}

	@Override
	public List<Registration> findById(int id) {
		// TODO Auto-generated method stub
		Registration findOne = dao.findOne(id);
		List<Registration> list=new ArrayList<>();
		list.add(findOne);
		return list;
	}

}
