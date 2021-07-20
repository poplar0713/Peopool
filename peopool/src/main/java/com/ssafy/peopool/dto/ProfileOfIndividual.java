package com.ssafy.peopool.dto;

public class ProfileOfIndividual {
	int ind_index; 	// 회원 인덱스
	String ind_resume;	// 회원 이력서
	String ind_video;	// 회원 PR동영상
	String ind_photo;	// 회원 사진
	
	public ProfileOfIndividual() {
	}

	public int getInd_index() {
		return ind_index;
	}

	public void setInd_index(int ind_index) {
		this.ind_index = ind_index;
	}

	public String getInd_resume() {
		return ind_resume;
	}

	public void setInd_resume(String ind_resume) {
		this.ind_resume = ind_resume;
	}

	public String getInd_video() {
		return ind_video;
	}

	public void setInd_video(String ind_video) {
		this.ind_video = ind_video;
	}

	public String getInd_photo() {
		return ind_photo;
	}

	public void setInd_photo(String ind_photo) {
		this.ind_photo = ind_photo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProfileOfIndividual [ind_index=").append(ind_index).append(", ind_resume=").append(ind_resume)
				.append(", ind_video=").append(ind_video).append(", ind_photo=").append(ind_photo).append("]");
		return builder.toString();
	}
	
	
}
