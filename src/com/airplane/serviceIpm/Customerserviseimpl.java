package com.airplane.serviceIpm;


import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airplane.dao.CustomerDao;
import com.airplane.dao.CustomercontactDao;
import com.airplane.dao.EmployeeDao;
import com.airplane.po.Customercontactinfo;
import com.airplane.po.Customerinfo;
import com.airplane.po.Employeeinfo;
import com.airplane.service.Customerservise;


@Service
@Transactional
public class Customerserviseimpl  implements Customerservise {

	@Autowired
	private CustomerDao customerdao;
	@Autowired
	private CustomercontactDao customercontactDao;
	@Autowired
	private EmployeeDao employeedao;
	/*
	 * 添加部门*/
	
	@Override
	public int addCustomerinfo(Customerinfo customerinfo) {
		// TODO Auto-generated method stub
		Customerinfo customerinfo3=customerdao.findByNo(customerinfo.getNo().toString().trim());
		if(customerinfo3==null){
//			Date date = new Date();
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			sdf.format(date);
//			customerinfo.setCreateDate(date);
			Customerinfo customerinfo2=customerdao.save(customerinfo);
			if(customerinfo2.getId()!=null){
				return 1;
			}else{
				return 0;
			}
		}else{
			return 0;
		}
	}

	
	
	/*
	 * 删除部门*/

	@Override
	public int deleteCustomerinfo(int id) {
		// TODO Auto-generated method stub
		if(id!=0){
			//Customerinfo findOne = customerdao.findOne(id);
			//List<Customercontactinfo> findByNo = customercontactDao.findByNo(findOne.getNo().toString());
//			for(Customercontactinfo info: findByNo){
//				customercontactDao.delete(info.getId());
//			}
//			List<Employeeinfo> findByEdepartment = employeedao.findByEdepartment(findOne.getNo().toString());
//			for(Employeeinfo info:findByEdepartment){
//				employeedao.delete(info.getId());
//			}
			customerdao.delete(id);
			return 1;
		}else{
			return 0;
		}
			
	}

	
	/*
	 * 修改部门*/
	@Override
	public int updateCustomerinfo(Customerinfo customerinfo) {
		Customerinfo customerinfo3=customerdao.findByNo(customerinfo.getNo().toString().trim());
		if(customerinfo3!=null){
			customerdao.save(customerinfo);
			return 1;
		}else{
			return 0;
		}
	}

	//查询部门
	@Override
	public List<Customerinfo> select(String name) {
		if(name==null){
			List<Customerinfo> pages=customerdao.findAll();
			return pages;
		}else{
			Customerinfo customer=customerdao.findByName(name);
			if(customer==null){
				return null;
			}else{
				Specification<Customerinfo> spec=new Specification<Customerinfo>() {
					
					@Override
					public Predicate toPredicate(Root<Customerinfo> arg0, CriteriaQuery<?> arg1, CriteriaBuilder arg2) {
						// TODO Auto-generated method stub
						return  arg2.equal(arg0.get("name"), name); 
					}
				};
				List<Customerinfo> pages=customerdao.findAll(spec);
				return pages;
			}
			
		}
		
	}



	@Override
	public String selectCustomerinfoByName(String name) {
		// TODO Auto-generated method stub
		Customerinfo cu=customerdao.findByName(name);
		String no = cu.getNo();
		return no;
	}
	
	

}
