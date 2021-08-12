package com.ssafy.peopool.model;

public class ClassList {
	
	int list_index;
	String list_name;
	
	public ClassList() {
		
	}

	public int getList_index() {
		return list_index;
	}

	public void setList_index(int list_index) {
		this.list_index = list_index;
	}

	public String getList_name() {
		return list_name;
	}

	public void setList_name(String list_name) {
		this.list_name = list_name;
	}

	@Override
	public String toString() {
		return "ClassList [list_index=" + list_index + ", list_name=" + list_name + "]";
	}
	
	

}
