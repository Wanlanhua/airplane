package com.airplane.serviceIpm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.airplane.dao.RegistrationDao;
import com.airplane.po.Employeeinfo;
import com.airplane.po.Registration;
import com.airplane.service.EmployeeServise;
import com.airplane.service.RegistrationServise;

@Service
public class RegistrationServiseimpl implements RegistrationServise {
	
	@Autowired
	private RegistrationDao dao;
	@Autowired
	private EmployeeServise employeeservise;

	
	@Override
	public int saveRegistration(Registration registration) {
//		Specification<Registration> spec = new Specification<Registration>() {
//
//			@Override
//			public Predicate toPredicate(Root<Registration> root, CriteriaQuery<?> qurey, CriteriaBuilder cd) {
//				// TODO Auto-generated method stub
//				Predicate c=cd.and(cd.equal(root.get("no"), registration.getNo().toString()),cd.equal(root.get("alternated"),0));
//				Predicate b=cd.and(cd.equal(root.get("no"),  registration.getNo().toString()),cd.equal(root.get("alternated"),1));
//				return cd.or(c,b);
//			}
//		};
//		List<Registration> findAll = dao.findAll(spec);
	
			Registration save = dao.save(registration);
			if(save!=null){
				return 1;
			}else{
				return 0;
			}
	}

	// 删除一单
	@Override
	public int deleteRegistration(int id) {
		dao.delete(id);
		return 1;
	}

	//查询所有
	@Override
	public List<Registration> selectRegistration(Registration registration,Date start ,Date stop) {
		Specification<Registration> spec = new Specification<Registration>() {
			@Override
			public Predicate toPredicate(Root<Registration> root, CriteriaQuery<?> qurey, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				List<Predicate> list=new ArrayList<>();
				 if(registration.getLocationnumber()!=null && !"".equals(registration.getLocationnumber())){
	                 list.add(cb.equal(root.get("locationnumber"),registration.getLocationnumber().toString()));
	              }
				 if(registration.getAirline()!=null && !"".equals(registration.getAirline())){
	                 list.add(cb.equal(root.get("airline"),registration.getAirline()));
	             }
				 if(registration.getNo()!=null && !"".equals(registration.getNo())){
	                 list.add(cb.equal(root.get("no"),registration.getNo()));
	             }
				 if(registration.getContent()!=null && !"".equals(registration.getContent())){
	                 list.add(cb.equal(root.get("content"),registration.getContent()));
	             }
				 if(start!=null&&stop!=null){
					 list.add(cb.between(root.get("createdate"), start, stop));
				 }

				Predicate[] arr = new Predicate[list.size()];
				return cb.and(list.toArray(arr));
			}
		};
		List<Registration> findAll = dao.findAll(spec);
		return findAll;
	}

	//更新选中的一单
	@Override
	public int updateRegistration(Registration registration) {
		Registration save = dao.save(registration);
		if(save!=null){
			return 1;
		}else{
			return 0;
		}
	}

	//通过No查询
	@Override
	public List<Registration> selectByNo(String no) {
		// TODO Auto-generated method stub
		//查询该员工状态为维修中的单子
		Specification<Registration> spec = new Specification<Registration>() {

			@Override
			public Predicate toPredicate(Root<Registration> root, CriteriaQuery<?> qurey, CriteriaBuilder cd) {
				// TODO Auto-generated method stub
				Predicate c=cd.and(cd.equal(root.get("no"), no),cd.equal(root.get("alternated"),0));
				Predicate b=cd.and(cd.equal(root.get("no"), no),cd.equal(root.get("alternated"),1));
				return cd.or(c,b);
			}
		};
		List<Registration> findAll = dao.findAll(spec);
		return findAll;
	}

	@Override
	public List<Registration> selectByConditions(Registration registration,Date start ,Date stop,String department) {
		// TODO Auto-generated method stub
		Specification<Registration> spec = new Specification<Registration>() {
			@Override
			public Predicate toPredicate(Root<Registration> root, CriteriaQuery<?> qurey, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				List<Predicate> list=new ArrayList<>();
				 if(registration.getLocationnumber()!=null && !"".equals(registration.getLocationnumber())){
	                 list.add(cb.equal(root.get("locationnumber"),registration.getLocationnumber().toString()));
	              }
				 if(registration.getAirline()!=null && !"".equals(registration.getAirline())){
	                 list.add(cb.equal(root.get("airline"),registration.getAirline()));
	             }
				 if(registration.getNo()!=null && !"".equals(registration.getNo())){
	                 list.add(cb.equal(root.get("no"),registration.getNo()));
	             }
				 if(registration.getContent()!=null && !"".equals(registration.getContent())){
	                 list.add(cb.equal(root.get("content"),registration.getContent()));
	             }
				 if(start!=null&&stop!=null){
					 list.add(cb.between(root.get("createdate"), start, stop));
				 }

				Predicate[] arr = new Predicate[list.size()];
				return cb.and(list.toArray(arr));
			}
		};
		List<Registration> findAll = dao.findAll(spec);
		List<Employeeinfo> departmentList=employeeservise.selectEmployee(department);
		List<Registration> find=new ArrayList<Registration>();
		for(int i=0;i<departmentList.size();i++){
			for(int j=0;j<findAll.size();j++){
				if(departmentList.get(i).getNo().trim().equals(findAll.get(j).getNo().trim())){
					find.add(findAll.get(j));
					
				}
			}
		}
		return find;
	}
	
	
	

}
