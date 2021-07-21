package com.ssafy.peopool.dto;

// 팔로우
public class Follow {
	int fol_index; // 팔로우인덱스
	int ent_index; // 기업인덱스
	int ind_index; // 회원인덱스
	boolean fol_type; // 팔로워 타입

	public Follow() {
	}

	public int getFol_index() {
		return fol_index;
	}

	public void setFol_index(int fol_index) {
		this.fol_index = fol_index;
	}

	public int getEnt_index() {
		return ent_index;
	}

	public void setEnt_index(int ent_index) {
		this.ent_index = ent_index;
	}

	public int getInd_index() {
		return ind_index;
	}

	public void setInd_index(int ind_index) {
		this.ind_index = ind_index;
	}
	
	public boolean isFol_type() {
		return fol_type;
	}

	public void setFol_type(boolean fol_type) {
		this.fol_type = fol_type;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Follow [fol_index=").append(fol_index).append(", ent_index=").append(ent_index)
				.append(", ind_index=").append(ind_index).append(", fol_type=").append(fol_type).append("]");
		return builder.toString();
	}
	
}
