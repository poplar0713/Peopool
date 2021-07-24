package com.ssafy.peopool.model.repo;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.peopool.dto.Enterprise;
import com.ssafy.peopool.dto.Follow;
import com.ssafy.peopool.dto.Individual;

@Repository
public interface FollowRepo {

	// 팔로잉 추가
	void registerFoloow(int foloower, int following) throws SQLException;

	// 팔로우 삭제
	void deleteFollow(int follwer, int following) throws SQLException;

	// 팔로우 숫자 조회
	Follow getFollowCount(int index) throws SQLException;

	// 팔로워 조회
	List<Follow> getFollowing(int index) throws SQLException;

	// 팔로잉 조회
	List<Individual> getFollower(int index) throws SQLException;
}
