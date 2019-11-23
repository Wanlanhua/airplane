package com.airplane.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.airplane.po.Employeeinfo;
import com.airplane.po.Menu1info;

public class UserView implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String no;
	
	private ArrayList<Menu1info> menus;
	
	public static UserView employeeTransformUserView(Employeeinfo employeeinfo, List<Menu1info> menu) {
		UserView userView = new UserView();
		userView.setNo(employeeinfo.getNo());
		userView.setName(employeeinfo.getName());
		ArrayList<Menu1info> menus = userView.getMenus();
		menus = (ArrayList<Menu1info>) menu;
		userView.setMenus(menus);
		return userView;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public ArrayList<Menu1info> getMenus() {
		return menus;
	}

	public void setMenus(ArrayList<Menu1info> menus) {
		this.menus = menus;
	}
	
	
}
