package com.ssafy.peopool.model.repo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.peopool.model.CareerList;

@Repository
public interface CareerListRepo {
	// 태그목록조회
		List<CareerList> getCareerList();
}
