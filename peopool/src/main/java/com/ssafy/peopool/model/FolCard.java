package com.ssafy.peopool.model;

public class FolCard {
	int fol_index; // 팔로우인덱스
	int follower; // 팔로우 당한 인덱스
	int following; // 팔로우 한 인덱스
	int fol_type; // 팔로잉 타입
	String name; // 이름
	
	public FolCard() {
		
	}

	public int getFol_index() {
		return fol_index;
	}

	public void setFol_index(int fol_index) {
		this.fol_index = fol_index;
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

	public int getFol_type() {
		return fol_type;
	}

	public void setFol_type(int fol_type) {
		this.fol_type = fol_type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "FolCard [fol_index=" + fol_index + ", follower=" + follower + ", following=" + following + ", fol_type="
				+ fol_type + ", name=" + name + "]";
	}
	
	
}
