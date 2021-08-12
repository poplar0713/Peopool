package com.ssafy.peopool.model.repo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.peopool.model.ClassList;

@Repository
public interface ClassListRepo {
	
	// 분류 목록 조회
	List<ClassList> getClassList();
	
	// 단어가 포함된 분류 목록 조회
	List<ClassList> getName(String name);

}
