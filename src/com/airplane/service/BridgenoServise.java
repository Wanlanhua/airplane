package com.airplane.service;

import java.util.List;

import com.airplane.po.Bridgeno;

public interface BridgenoServise {
	//查询
		List<Bridgeno> selectBridgeno();
		//添加
		int saveBridgeno(Bridgeno bridgeno);
		//删除
		int delectBridgeno(int id);
		//修改
		int updateBridgeno(Bridgeno bridgeno);

}
