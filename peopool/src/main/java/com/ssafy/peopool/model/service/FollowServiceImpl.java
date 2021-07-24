package com.ssafy.peopool.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.peopool.dto.Enterprise;
import com.ssafy.peopool.dto.Follow;
import com.ssafy.peopool.dto.Individual;
import com.ssafy.peopool.model.repo.FollowRepo;

@Service
public class FollowServiceImpl implements FollowService {
	
	@Autowired
	FollowRepo followRepo;

	@Override
	public void registerFoloow(int foloower, int following) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFollow(int follwer, int following) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Follow getFollowCount(int index) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Individual> getFollowing(int index) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Individual> getFollower(int index) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
