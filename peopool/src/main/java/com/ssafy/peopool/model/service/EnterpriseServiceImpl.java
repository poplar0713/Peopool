package com.ssafy.peopool.model.service;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.peopool.model.Enterprise;
import com.ssafy.peopool.model.repo.EnterpriseRepo;

@Service
@MapperScan(basePackages = {"com.ssafy.peopool.model.repo"})
public class EnterpriseServiceImpl implements EnterpriseService{
	
	@Autowired
	EnterpriseRepo enterpriseRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	

	@Override
	public List<Enterprise> getAllEnterprise() throws SQLException {
		// TODO Auto-generated method stub
		return enterpriseRepo.getAllEnterprise();
	}

	@Override
	public Enterprise getEnterprise(int index) throws SQLException {
		// TODO Auto-generated method stub
		return enterpriseRepo.getEnterprise(index);
	}

	@Override
	public boolean registerEnterprise(Enterprise enterprise) throws SQLException {
		// TODO Auto-generated method stub
		enterprise.setEnt_password(passwordEncoder.encode(enterprise.getEnt_password()));
		return enterpriseRepo.registerEnterprise(enterprise) == 1;
	}

	@Override
	public boolean modifyEnterprise(Enterprise enterprise) throws SQLException {
		// TODO Auto-generated method stub
		enterprise.setEnt_password(passwordEncoder.encode(enterprise.getEnt_password()));
		return enterpriseRepo.modifyEnterprise(enterprise) == 1;
	}

	@Override
	public boolean deleteEnterprise(int index) throws SQLException {
		// TODO Auto-generated method stub
		return enterpriseRepo.deleteEnterprise(index) == 1;
	}

	@Override
	public Enterprise findEnterpriseID(String name, String email) throws SQLException {
		// TODO Auto-generated method stub
		return enterpriseRepo.findEnterpriseID(name, email);
	}

	@Override
	public Enterprise findEnterprisePW(String id, String email) throws SQLException {
		// TODO Auto-generated method stub
		return enterpriseRepo.findEnterprisePW(id, email);
	}

	@Override
	public Enterprise getUserId(String id) throws SQLException {
		// TODO Auto-generated method stub
		return enterpriseRepo.getUserId(id);
	}

}
