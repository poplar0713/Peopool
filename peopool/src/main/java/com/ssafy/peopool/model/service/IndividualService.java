package com.ssafy.peopool.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.peopool.model.Individual;

public interface IndividualService {
	// 개인 회원 전체 조회
		List<Individual> getAllUser() throws SQLException;

		// 개인 로그인
		Individual loginCheckIndividual(String id) throws SQLException;

		// 개인 회원정보조회 ( 이름, 생년월일, 성별, 번호, 이메일)
		Individual getIndividual(int index) throws SQLException;

		// 개인 회원 등록
		boolean registerIndividual(Individual individual) throws SQLException;

		// 개인 회원 정보 수정
		boolean modifyIndividual(Individual individual) throws SQLException;

		// 개인회원 탈퇴
		boolean deleteIndividual(int index) throws SQLException;

		// 아이디 찾기
		Individual findIndividualID(Individual individual) throws SQLException;

		// 비밀번호 찾기
		Individual findIndividualPW(Individual individual) throws SQLException;

}
