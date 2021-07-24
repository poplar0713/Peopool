package com.ssafy.peopool.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.peopool.dto.Enterprise;
import com.ssafy.peopool.dto.Follow;
import com.ssafy.peopool.dto.Individual;

public interface FollowService {
	

	// 팔로잉 추가
	void registerFollow(int foloower, int following) throws SQLException;

	// 팔로우 삭제
	void deleteFollow(int follower, int following) throws SQLException;

	// 팔로우 숫자 조회
	int getFollowCount(int index) throws SQLException;

	// 팔로워 조회
	List<Follow> getFollowing(int index) throws SQLException;

	// 팔로잉 조회
	List<Follow> getFollower(int index) throws SQLException;

}
