package com.ssafy.peopool.model.repo;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ssafy.peopool.model.Classification;

@Repository
public interface ClassificationRepo {
	
	// 분류별 등록된 기업 목록
	List<Map<Object, Object>> getEnt(String name);
	
	// 기업별 등록된 분류 목록
	List<Map<Object, Object>> getClass(int index);
	
	// 태그 등록
	int registerClass(Classification classification);

	// 태그 삭제
	int deleteClass(int index);

}
