package com.ssafy.peopool.model.repo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ssafy.peopool.model.FolCard;
import com.ssafy.peopool.model.Follow;


@Repository
public interface FollowRepo {

	// 팔로잉 추가
	int registerFollow(Follow follow) throws SQLException;

	// 팔로우 삭제
	int deleteFollow(Follow follow) throws SQLException;
	
	// 회원탈퇴시 팔로우 기록 삭제
	int deleteFollower(Follow follow) throws SQLException;
	
	// 회원탈퇴시 팔로우 기록 삭제
	int deleteFollowing(Follow follow) throws SQLException;

	// 팔로우 숫자 조회
	int getFollowerCount(String index, String type) throws SQLException;
	
	
	int getFollowingCount(String index, String type) throws SQLException;

	// 팔로워 조회
	List<FolCard> getFollowing(String index, String type) throws SQLException;

	// 팔로잉 조회
	List<FolCard> getFollower(String index, String type) throws SQLException;
	
	// 팔로우 체크
	Follow getCheck(Follow follow) throws SQLException;
}
