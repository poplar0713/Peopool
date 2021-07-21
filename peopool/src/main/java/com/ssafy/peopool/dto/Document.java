package com.ssafy.peopool.dto;

// 제출 서류
public class Document {
	int doc_index;	// 서류 인덱스
	int ind_index;	// 회원 인덱스
	int rec_index;	// 공고 인덱스
	String doc_filepath;	// 파일 경로
	
	public Document() {
	}

	public int getDoc_index() {
		return doc_index;
	}

	public void setDoc_index(int doc_index) {
		this.doc_index = doc_index;
	}

	public int getInd_index() {
		return ind_index;
	}

	public void setInd_index(int ind_index) {
		this.ind_index = ind_index;
	}

	public int getRec_index() {
		return rec_index;
	}

	public void setRec_index(int rec_index) {
		this.rec_index = rec_index;
	}

	public String getDoc_filepath() {
		return doc_filepath;
	}

	public void setDoc_filepath(String doc_filepath) {
		this.doc_filepath = doc_filepath;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Document [doc_index=").append(doc_index).append(", ind_index=").append(ind_index)
				.append(", rec_index=").append(rec_index).append(", doc_filepath=").append(doc_filepath).append("]");
		return builder.toString();
	}
}
