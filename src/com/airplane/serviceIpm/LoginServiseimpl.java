package com.airplane.serviceIpm;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airplane.dao.EmployeeDao;
import com.airplane.dao.LoginDao;
import com.airplane.dao.Menu1Dao;
import com.airplane.dao.PowerDao;
import com.airplane.po.Employeeinfo;
import com.airplane.po.Logininfo;
import com.airplane.po.Menu1info;
import com.airplane.po.Powerinfo;
import com.airplane.service.EmployeeServise;
import com.airplane.service.LoginServise;
@Service
@Transactional
public class LoginServiseimpl implements LoginServise{

	@Autowired
	private LoginDao logindao;
	@Autowired
	private PowerDao powerdao;
	@Autowired
	private Menu1Dao menu1dao;
	@Autowired
	private EmployeeDao employeedao;
	@Autowired
	private EmployeeServise employeeservise;
	
	
	//添加用户信息
	@Override
	public int saveLogin(Logininfo login) {
		// TODO Auto-generated method stub
		String name=login.getName();
		Logininfo login2=logindao.findByName(name);
		if(login2==null){
			logindao.save(login);
			return 1; 
		}else{
			return 0;
		}
		
	}

	//删除用户信息
	@Override
	public int delectLogin(int id) {
		// TODO Auto-generated method stub
		Logininfo login=logindao.findOne(id);
		if(login==null){
			return 0;
		}else{
			String name=login.getName().toString().trim();
			List<Powerinfo> list=powerdao.findByNo(name);
			logindao.delete(id);
			for(Powerinfo power:list){
				powerdao.delete(power.getId());
			}
			return 1;
		}
	}

	//修改用户信息
	@Override
	public int updateLogin(Logininfo login) {
		if(login.getId()==null){
			return 0;
		}else{
			logindao.save(login);
			return 1;
		}
		
	}

	//查询用户信息
	@Override
	public List<Logininfo> selectLogin(String department){
		List<Logininfo> logininfo=logindao.findAll();
		List<Employeeinfo> departmentList;
		if(department==null){
			departmentList=employeeservise.selectAll();
		}else{
			departmentList=employeeservise.selectEmployee(department);
		}
		
		List<Logininfo> find=new ArrayList<Logininfo>();
		for(int i=0;i<departmentList.size();i++){
			for(int j=0;j<logininfo.size();j++){
				if(departmentList.get(i).getNo().trim().equals(logininfo.get(j).getName().trim())){
					find.add(logininfo.get(j));
				}
			}
		}
		return find;
	}
	
	//查询添加下拉框
	@Override
	public List<String> select(String department){
		List<String> list2 = new ArrayList<String>();
		if(department==null){
			List<Employeeinfo> list=employeeservise.selectAll();
			for (Employeeinfo employee : list){ 
				String no = employee.getNo().toString().trim();
				Logininfo logininfo=logindao.findByName(no);
				if(logininfo==null){
					list2.add(no);
				}
			}
			return list2;
		}else{
			List<Employeeinfo> departmentList=employeeservise.selectEmployee(department);
			for (Employeeinfo employee : departmentList){ 
				String no = employee.getNo().toString().trim();
				Logininfo logininfo=logindao.findByName(no);
				if(logininfo==null){
					list2.add(no);
				}
			}
			return list2;
		}
		
		
		
		
	}
	
	//登陆
	@Override
	public List<String> login(String name,String password){
		Logininfo logininfo = logindao.findByNameAndPassword(name, password);
		if(logininfo==null){
			return null;
		}else{
			List<Powerinfo> findByNo = powerdao.findByNo(name);
			List<String> list2 = new ArrayList<String>();
			for(Powerinfo power : findByNo){
				list2.add(power.getM2no().toString().trim());
			}
			return list2;
		}

	}
	
	//查询菜单
	@Override
	public List<String> menus(String name){
		List<Powerinfo> findByNo = powerdao.findByNo(name);
		List<String> list2 = new ArrayList<String>();
		for(Powerinfo power : findByNo){
			list2.add(power.getM2no().toString().trim());
		}
		return list2;
		
	}

}
