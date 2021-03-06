package com.ssafy.peopool.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.peopool.model.Classification;

public interface ClassificationService {
	
	List<Map<Object, Object>> getEnt(String name);
	
	List<Map<Object, Object>> getClass(int index);
	
	List<Map<Object, Object>> getCase(List<Integer> list);
	
	boolean registerClass(Classification classification);
	
	boolean deleteClass(int index);

}
