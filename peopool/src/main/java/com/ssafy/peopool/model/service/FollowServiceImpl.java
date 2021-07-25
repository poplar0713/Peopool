package com.ssafy.peopool.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import com.ssafy.peopool.model.Follow;
=======
import com.ssafy.peopool.dto.Follow;
>>>>>>> f5baafcce50f0d76a6263aedec03b359aa8d270d
import com.ssafy.peopool.model.repo.FollowRepo;

@Service
public class FollowServiceImpl implements FollowService {
	
	@Autowired
	FollowRepo followRepo;

	@Override
	public boolean registerFollow(Follow follow) throws SQLException {
		// TODO Auto-generated method stub
		return followRepo.registerFollow(follow) == 1;
	}

	@Override
	public boolean deleteFollow(Follow follow) throws SQLException {
		// TODO Auto-generated method stub
		return followRepo.deleteFollow(follow) == 1;
	}

	@Override
	public int getFollowerCount(Map<String, String> map) throws SQLException {
		// TODO Auto-generated method stub
		return followRepo.getFollowerCount(map);
	}
	
	@Override
	public int getFollowingCount(Map<String, String> map) throws SQLException {
		// TODO Auto-generated method stub
		return followRepo.getFollowingCount(map);
	}


	@Override
	public List<Follow> getFollowing(Map<String, String> map) throws SQLException {
		// TODO Auto-generated method stub
		return followRepo.getFollowing(map);
	}

	@Override
	public List<Follow> getFollower(Map<String, String> map) throws SQLException {
		// TODO Auto-generated method stub
		return followRepo.getFollower(map);
	}

	

}
