package com.ssafy.peopool.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.peopool.dto.Enterprise;
import com.ssafy.peopool.dto.Follow;
import com.ssafy.peopool.dto.Individual;

public class FollowServiceImpl implements FollowService {

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
	public List<Individual> getEFollowing(int index) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Individual> getEFollower(int index) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Enterprise> getIFollowing(int index) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Enterprise> getIFollower(int index) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
