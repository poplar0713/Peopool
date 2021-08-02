package com.ssafy.peopool.model.repo;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface HashtagRepo {

	// 사용자 별 태그 목록
	HashMap<Integer, String> tagByUser();

	// 태그 별 사용자 수
	HashMap<String, Integer> countByTag();

	// 태그 등록
	int registerHashtag(int tag_inex, int ind_index);

	// 태그 삭제
	int deletedHashtag(int index);

}
