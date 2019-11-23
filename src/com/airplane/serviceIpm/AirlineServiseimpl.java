package com.airplane.serviceIpm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airplane.dao.AirlineDao;
import com.airplane.po.Airline;
import com.airplane.service.AirlineServise;

@Service
public class AirlineServiseimpl implements AirlineServise{

	@Autowired
	private AirlineDao airdao;
	
	@Override
	public List<Airline> selectAirline() {
		List<Airline> airline = airdao.findAll();
		return airline;
	}

	@Override
	public int saveAirline(Airline airline) {
		Airline save = airdao.save(airline);
		if(save!=null){
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public int delectAirline(int id) {
		airdao.delete(id);
		Airline save = airdao.findOne(id);
		if(save==null){
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public int updateAirline(Airline airline) {
			Airline save = airdao.save(airline);
			if(save!=null){
				return 1;
			}else{
				return 0;
			}
	}
		
}

