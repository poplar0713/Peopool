package com.ssafy.peopool.model.repo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ssafy.peopool.model.Follow;


@Repository
public interface FollowRepo {

	// 팔로잉 추가
	int registerFollow(Follow follow) throws SQLException;

	// 팔로우 삭제
	int deleteFollow(Follow follow) throws SQLException;

	// 팔로우 숫자 조회
	int getFollowerCount(Map<String, String> map) throws SQLException;
	
	
	int getFollowingCount(Map<String, String> map) throws SQLException;

	// 팔로워 조회
	List<Follow> getFollowing(Map<String, String> map) throws SQLException;

	// 팔로잉 조회
	List<Follow> getFollower(Map<String, String> map) throws SQLException;
}
