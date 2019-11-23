package com.airplane.serviceIpm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airplane.dao.BridegenoDao;
import com.airplane.po.Bridgeno;
import com.airplane.service.BridgenoServise;

@Service
public class BridgenoServiseinpl implements BridgenoServise {
	@Autowired
	private BridegenoDao bridao;
	@Override
	public List<Bridgeno> selectBridgeno() {
		List<Bridgeno> Bridgeno = bridao.findAll();
		return Bridgeno;
	}

	@Override
	public int saveBridgeno(Bridgeno Bridgeno) {
		Bridgeno save = bridao.save(Bridgeno);
		if(save!=null){
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public int delectBridgeno(int id) {
		bridao.delete(id);
		Bridgeno save = bridao.findOne(id);
		if(save==null){
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public int updateBridgeno(Bridgeno Bridgeno) {
			Bridgeno save = bridao.save(Bridgeno);
			if(save!=null){
				return 1;
			}else{
				return 0;
			}
	}

}
