package com.ssafy.peopool.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.peopool.dto.Individual;
import com.ssafy.peopool.model.repo.IndividualRepo;

@Service
public class IndividualServiceImpl implements IndividualService {

	@Autowired
	IndividualRepo individualRepo;
	
	
	@Override
	public List<Individual> getAllUser() throws SQLException {
		// TODO Auto-generated method stub
		return individualRepo.getAllUser();
	}

	@Override
	public Individual loginCheckIndividual(String id) throws SQLException {
		// TODO Auto-generated method stub
		return individualRepo.loginCheckIndividual(id);
	}

	@Override
	public Individual getIndividual(int index) throws SQLException {
		// TODO Auto-generated method stub
		return individualRepo.getIndividual(index);
	}

	@Override
	public void registerIndividual(Individual individual) throws SQLException {
		// TODO Auto-generated method stub
		individualRepo.registerIndividual(individual);
	}

	@Override
	public void modifyIndividual(Individual individual) throws SQLException {
		// TODO Auto-generated method stub
		individualRepo.modifyIndividual(individual);
	}

	@Override
	public void deleteIndividual(int index) throws SQLException {
		// TODO Auto-generated method stub
		individualRepo.deleteIndividual(index);
	}

	@Override
	public String findIndividualID(String name, String phone) throws SQLException {
		// TODO Auto-generated method stub
		return findIndividualID(name, phone);
	}

	@Override
	public String findIndividualPW(String id, String phone) throws SQLException {
		// TODO Auto-generated method stub
		return findIndividualPW(id, phone);
	}

}
