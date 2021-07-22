package com.ssafy.peopool.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.peopool.dto.Enterprise;
import com.ssafy.peopool.model.repo.EnterpriseRepo;

@Service
public class EnterpriseServiceImpl implements EnterpriseService{
	
	@Autowired
	EnterpriseRepo enterpriseRepo;

	@Override
	public List<Enterprise> getEnterprise() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enterprise loginCheckEnterprise(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enterprise getEnterprise(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerEnterprise(Enterprise enterprise) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyEnterprise(Enterprise enterprise) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEnterprise(int index) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String findEnterpriseID(String name, String email) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findEnterprisePW(String id, String email) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
