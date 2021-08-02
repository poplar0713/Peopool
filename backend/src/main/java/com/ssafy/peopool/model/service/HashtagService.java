package com.ssafy.peopool.model.service;

import java.sql.SQLException;
import java.util.HashMap;

public interface HashtagService {

	// 사용자 별 태그 목록
	HashMap<Integer, String> tagByUser();

	// 태그 별 사용자 수
	HashMap<String, Integer> countByTag();

	// 태그 등록
	boolean registerHashtag(int tag_inex, int ind_index);

	// 태그 삭제
	boolean deletedHashtag(int index);

}
