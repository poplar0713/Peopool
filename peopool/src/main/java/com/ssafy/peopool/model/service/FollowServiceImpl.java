package com.ssafy.peopool.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.peopool.dto.Follow;
import com.ssafy.peopool.model.repo.FollowRepo;

@Service
public class FollowServiceImpl implements FollowService {
	
	@Autowired
	FollowRepo followRepo;

	@Override
	public void registerFollow(int follower, int following) throws SQLException {
		// TODO Auto-generated method stub
		followRepo.registerFollow(follower, following);
	}

	@Override
	public void deleteFollow(int follower, int following) throws SQLException {
		// TODO Auto-generated method stub
		followRepo.deleteFollow(follower, following);
	}

	@Override
	public int getFollowCount(int index) throws SQLException {
		// TODO Auto-generated method stub
		return followRepo.getFollowCount(index);
	}

	@Override
	public List<Follow> getFollowing(int index) throws SQLException {
		// TODO Auto-generated method stub
		return followRepo.getFollowing(index);
	}

	@Override
	public List<Follow> getFollower(int index) throws SQLException {
		// TODO Auto-generated method stub
		return followRepo.getFollower(index);
	}

	

}
