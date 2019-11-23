package com.airplane.serviceIpm;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airplane.dao.CustomerDao;
import com.airplane.dao.EmployeeDao;
import com.airplane.dao.LoginDao;
import com.airplane.po.Customerinfo;
import com.airplane.po.Employeeinfo;
import com.airplane.po.Logininfo;
import com.airplane.service.EmployeeServise;
import com.airplane.service.LoginServise;

@Service
@Transactional
public class EmployeeSeriveimpl implements EmployeeServise {

	@Autowired
	private EmployeeDao employeedao;
	@Autowired
	private LoginServise loserives;
	@Autowired
	private LoginDao lodao;
	
	
	
	@Autowired
	private CustomerDao cdao;
	@Override
	public int saveEmployee(Employeeinfo employee) {
		// TODO Auto-generated method stub
		Employeeinfo employee2=employeedao.findByNo(employee.getNo().toString().trim());
		if(employee2==null){
			employeedao.save(employee);
			return 1;
		}else{
			return 0;
		}
	}


	@Override
	public int delectEmployee(int id) {
		// TODO Auto-generated method stub
			
			Employeeinfo findOne = employeedao.findOne(id);
			
			if(lodao.findByName(findOne.getNo().toString())!=null){
				Logininfo findByName = lodao.findByName(findOne.getNo().toString());
				loserives.delectLogin(findByName.getId());
			}
			employeedao.delete(id);
			return 1;
		
	}
	
	@Override
	public int updateEmployee(Employeeinfo employee) {
		// TODO Auto-generated method stub
		Employeeinfo employee2=employeedao.findByNo(employee.getNo().toString().trim());
		if(employee2!=null){
			employeedao.save(employee);
			return 1;
		}else{
			return 0;
		}
	}


	@Override
	public List<Employeeinfo> selectEmployee(String department) {
		if(department==null){
			List<Employeeinfo> list = employeedao.findAll();
			return list;
		}else{
			Specification<Employeeinfo> spec=new Specification<Employeeinfo>() {
				
				@Override
				public Predicate toPredicate(Root<Employeeinfo> arg0, CriteriaQuery<?> arg1, CriteriaBuilder arg2) {
					// TODO Auto-generated method stub
					
					return  arg2.equal(arg0.get("department"), department); 
				}
			};
			List<Employeeinfo> list = employeedao.findAll(spec);
			return list;
		}
		
	}


	@Override
	public Map<String, String> selectNmme() {
		// TODO Auto-generated method stub
		Map<String, String> count = new HashMap<>();
		List<Customerinfo> findAll = cdao.findAll();
		for(Customerinfo info : findAll){
			count.put(info.getName(), info.getNo());
		}
		return count;
	}


	@Override
	public List<Employeeinfo> selectAll() {
		// TODO Auto-generated method stub
		List<Employeeinfo> findAll = employeedao.findAll();
		return findAll;
	}


	@Override
	public String findDepartmentByNo(String no) {
		// TODO Auto-generated method stub
		Employeeinfo findByNo = employeedao.findByNo(no);
		if(findByNo!=null){
			String department = findByNo.getDepartment().toString().trim();
			return department;
		}else{
			return null;
		}
		
	}

}
