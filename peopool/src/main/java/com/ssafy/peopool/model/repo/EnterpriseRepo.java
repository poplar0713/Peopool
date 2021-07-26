package com.ssafy.peopool.model.repo;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.peopool.model.Enterprise;

@Repository
public interface EnterpriseRepo {
	// 기업 회원 전체 조회
	List<Enterprise> getAllEnterprise() throws SQLException;

	// 기업로그인(아이디 / 비밀번호 조회)
	Enterprise loginCheckEnterprise(String id) throws SQLException;

	// 기업 회원 정보 조회 ( 이름, 연락처, 이메일 )
	Enterprise getEnterprise(int index) throws SQLException;

	// 기업 회원 등록
	int registerEnterprise(Enterprise enterprise) throws SQLException;

	// 기업 회원 정보 수정
	int modifyEnterprise(Enterprise enterprise) throws SQLException;

	// 기업 회원 탈퇴
	int deleteEnterprise(int index) throws SQLException;

	// 기업 아이디 찾기
	Enterprise findEnterpriseID(String name, String email) throws SQLException;

	// 기업 비밀번호 찾기
	Enterprise findEnterprisePW(String id, String email) throws SQLException;
}
