package com.airplane.serviceIpm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airplane.dao.EmployeeDao;
import com.airplane.dao.Menu1Dao;
import com.airplane.dao.PowerDao;
import com.airplane.po.Employeeinfo;
import com.airplane.po.Menu1info;
import com.airplane.po.Powerinfo;
import com.airplane.service.EmployeeServise;
import com.airplane.service.PowerServise;
import com.airplane.view.UserView;
@Service
public class PowerServiseimpl implements PowerServise{
	
	@Autowired
	private PowerDao powerdao;
	@Autowired
	private Menu1Dao menu1Dao;
	@Autowired
	private EmployeeServise employeeservise;
	

	@Override
	@Transactional
	public int updatePower(String no , String[] m2no) {
		//删除之前的
		powerdao.deleteByNo(no);
		// 重新添加;
		for (int i = 0; i < m2no.length; i++) {
	            Powerinfo power = new Powerinfo();
	            power.setNo(no);
	            power.setM2no(m2no[i]);
	            powerdao.save(power);
	        }
		return 1;
	}

	@Override
	public List<UserView> selectPower(String department) {
		// TODO Auto-generated method stub
		List<Employeeinfo> list;
		if(department==null||department.equals("")){
			list=employeeservise.selectAll();
		}else{
			list= employeeservise.selectEmployee(department);
		}
		List<Menu1info> menu1info = menu1Dao.findAll();
		Map<String, Menu1info> menus = new HashMap<>();
		for(Menu1info menu : menu1info) {
			menus.put(menu.getNo(), menu);
		}
		List<UserView> userViews = new ArrayList<>();
		for(int i=0; i<list.size(); i++) {
			Employeeinfo employee = list.get(i);
			String no = employee.getNo();
			List<Powerinfo> power = powerdao.findByNo(no);
			List<Menu1info> menu = new ArrayList<>();
			for(int j=0; j<power.size(); j++) {
				Menu1info menu1info2 = menus.get(power.get(j).getM2no());
				menu.add(menu1info2);
			}
			UserView userView = UserView.employeeTransformUserView(employee, menu);
			userViews.add(userView);
		}
		return userViews;
	}

	@Override
	public List<Menu1info> listMenu() {
		return menu1Dao.findAll();
	}

};
