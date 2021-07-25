package com.ssafy.peopool.model.service;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.peopool.model.Enterprise;
import com.ssafy.peopool.model.repo.EnterpriseRepo;

@Service
@MapperScan(basePackages = {"com.ssafy.peopool.model.repo"})
public class EnterpriseServiceImpl implements EnterpriseService{
	
	@Autowired
	EnterpriseRepo enterpriseRepo;
	

	@Override
	public List<Enterprise> getEnterprise() throws SQLException {
		// TODO Auto-generated method stub
		return enterpriseRepo.getEnterprise();
	}

	@Override
	public Enterprise loginCheckEnterprise(String id) throws SQLException {
		// TODO Auto-generated method stub
		return enterpriseRepo.loginCheckEnterprise(id);
	}

	@Override
	public Enterprise getEnterprise(String id) throws SQLException {
		// TODO Auto-generated method stub
		return enterpriseRepo.getEnterprise(id);
	}

	@Override
	public void registerEnterprise(Enterprise enterprise) throws SQLException {
		// TODO Auto-generated method stub
		registerEnterprise(enterprise);
	}

	@Override
	public void modifyEnterprise(Enterprise enterprise) throws SQLException {
		// TODO Auto-generated method stub
		modifyEnterprise(enterprise);
	}

	@Override
	public void deleteEnterprise(int index) throws SQLException {
		// TODO Auto-generated method stub
		deleteEnterprise(index);
	}

	@Override
	public String findEnterpriseID(String name, String email) throws SQLException {
		// TODO Auto-generated method stub
		return findEnterpriseID(name, email);
	}

	@Override
	public String findEnterprisePW(String id, String email) throws SQLException {
		// TODO Auto-generated method stub
		return findEnterprisePW(id, email);
	}

}
