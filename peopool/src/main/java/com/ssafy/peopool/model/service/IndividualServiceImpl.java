package com.ssafy.peopool.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.peopool.model.Individual;
import com.ssafy.peopool.model.repo.IndividualRepo;

@Service
public class IndividualServiceImpl implements IndividualService {

	@Autowired
	IndividualRepo individualRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@Override
	public List<Individual> getAllUser() throws SQLException {
		// TODO Auto-generated method stub
		return individualRepo.getAllUser();
	}

	@Override
	public Individual getIndividual(int index) throws SQLException {
		// TODO Auto-generated method stub
		return individualRepo.getIndividual(index);
	}

	@Override
	public boolean registerIndividual(Individual individual) throws SQLException {
		// TODO Auto-generated method stub
		individual.setInd_password(passwordEncoder.encode(individual.getInd_password()));
		return individualRepo.registerIndividual(individual) == 1;
	}

	@Override
	public boolean modifyIndividual(Individual individual) throws SQLException {
		// TODO Auto-generated method stub
		individual.setInd_password(passwordEncoder.encode(individual.getInd_password()));
		return individualRepo.modifyIndividual(individual) == 1;
	}

	@Override
	public boolean deleteIndividual(int index) throws SQLException {
		// TODO Auto-generated method stub
		return individualRepo.deleteIndividual(index) == 1;
	}

	@Override
	public Individual findIndividualID(String name, String phone) throws SQLException {
		// TODO Auto-generated method stub
		return individualRepo.findIndividualID(name, phone);
	}

	@Override
	public Individual findIndividualPW(String id, String phone) throws SQLException {
		// TODO Auto-generated method stub
		return individualRepo.findIndividualPW(id, phone);
	}

	@Override
	public Individual getUserId(String id) throws SQLException {
		// TODO Auto-generated method stub
		return individualRepo.getUserId(id);
	}

}
