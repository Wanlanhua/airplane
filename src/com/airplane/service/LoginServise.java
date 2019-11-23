package com.airplane.service;

import java.util.List;

import com.airplane.po.Logininfo;

public interface LoginServise {
	
		//添加用户
		public int saveLogin(Logininfo login);
		//删除用户
		public int delectLogin(int id);
		//修改用户
		public int updateLogin(Logininfo login);
		//查询用户列表
		public List<Logininfo> selectLogin(String name);
		//添加匹配
		public List<String> select(String department);
		//登陆
		public List<String> login(String name,String password);
		//查询菜单
		public List<String> menus(String name);
}
