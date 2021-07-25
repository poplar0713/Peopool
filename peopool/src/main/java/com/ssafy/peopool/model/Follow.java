package com.ssafy.peopool.model;

// 팔로우
public class Follow {
	int fol_index; // 팔로우인덱스
	int follower; // 기업인덱스
	int following; // 회원인덱스
	boolean fol_type; // 팔로워 타입

	public Follow() {
	}

	public int getFol_index() {
		return fol_index;
	}

	public void setFol_index(int fol_index) {
		this.fol_index = fol_index;
	}

	public boolean isFol_type() {
		return fol_type;
	}

	public void setFol_type(boolean fol_type) {
		this.fol_type = fol_type;
	}

	public int getFollower() {
		return follower;
	}

	public void setFollower(int follower) {
		this.follower = follower;
	}

	public int getFollowing() {
		return following;
	}

	public void setFollowing(int following) {
		this.following = following;
	}

	@Override
	public String toString() {
		return "Follow [fol_index=" + fol_index + ", follower=" + follower + ", following=" + following + ", fol_type="
				+ fol_type + "]";
	}

	
	
}
