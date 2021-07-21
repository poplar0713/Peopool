package com.ssafy.peopool.model.repo;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.peopool.dto.Individual;

@Repository
public interface IndividualRepo {
	// 개인 회원 전체 조회
	List<Individual> getAllUser() throws SQLException;
	
	// 개인 로그인
	Individual loginCheckIndividual(String id) throws SQLException;
	
	// 개인 회원정보조회 ( 이름, 생년월일, 성별, 번호, 이메일)
	Individual getIndividual(int index) throws SQLException;
	
	// 개인 회원 등록
	void registerIndividual(Individual individual) throws SQLException;
	
	// 개인 회원 정보 수정
	void modifyIndividual(Individual individual) throws SQLException;
	
	// 개인회원 탈퇴
	void deleteIndividual(int index) throws SQLException;
	
	// 아이디 찾기
	String findIndividualID(String name, String phone) throws SQLException;
	
	// 비밀번호 찾기
	String findIndividualPW(String id, String phone) throws SQLException;
}
