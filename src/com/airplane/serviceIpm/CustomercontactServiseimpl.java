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
import com.airplane.po.Customercontactinfo;
import com.airplane.po.Customerinfo;
import com.airplane.service.CustomercontactServise;

@Service
@Transactional
public class CustomercontactServiseimpl implements CustomercontactServise {
	
	@Autowired
	private CustomercontactDao customercontactDao;
	@Autowired
	private CustomerDao customerdao;
	
	/*
	 * 添加部门联系人*/
	@Override
	public int addCustomercontact(Customercontactinfo customercontact) {
		// TODO Auto-generated method stub
		Customercontactinfo customercontact2=customercontactDao.findByName(customercontact.getName().toString().trim());
		if(customercontact2==null){
			customercontactDao.save(customercontact);
			return 1;
		}else{
			return 0;
		}
		
	}
	
	/*删除部门联系人*/
	@Override
	public int delectCustomercontact(int id){
		customercontactDao.delete(id);
		return 1;
	}
	
	/*修改部门联系人*/
	@Override
	public  int updateCustomercontact(Customercontactinfo customercontact){
		Customercontactinfo customercontact2=customercontactDao.findByName(customercontact.getName().toString().trim());
		if(customercontact2!=null){
			customercontactDao.save(customercontact);
			return 1;
		}else{
			return 0;
		}
	}

	//
	@Override
	public List<Customercontactinfo> select(String name) {
		if(name==null){
			List<Customercontactinfo> list1=customercontactDao.findAll();
			return list1;
		}else{
			Customerinfo customer=customerdao.findByName(name);
			if(customer==null){
				return null;
			}else{
				String no=customer.getNo();
				Specification<Customercontactinfo> spec=new Specification<Customercontactinfo>() {
					
					@Override
					public Predicate toPredicate(Root<Customercontactinfo> arg0, CriteriaQuery<?> arg1, CriteriaBuilder arg2) {
						// TODO Auto-generated method stub
						return  arg2.equal(arg0.get("no"),no); 
					}
				};
				
				List<Customercontactinfo> list= this.customercontactDao.findAll(spec);
				return list;
			}
		}
	}

	
	@Override
	public List<Customerinfo> selectNo() {
		// TODO Auto-generated method stub
		List<Customerinfo> list=customerdao.findAll();
		return list;
	}
	
	
}
