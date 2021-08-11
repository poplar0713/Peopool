package com.ssafy.peopool.model;

public class Classification {
	int cla_index;
	int ent_index;
	int list_index;
	
	public Classification() {
		
	}
	
	public int getCla_index() {
		return cla_index;
	}
	
	public void setCla_index(int cla_index) {
		this.cla_index = cla_index;
	}
	
	public int getEnt_index() {
		return ent_index;
	}
	
	public void setEnt_index(int ent_index) {
		this.ent_index = ent_index;
	}
	
	public int getList_index() {
		return list_index;
	}
	
	public void setList_index(int list_index) {
		this.list_index = list_index;
	}
	
	@Override
	public String toString() {
		return "Classification [cla_index=" + cla_index + ", ent_index=" + ent_index + ", list_index=" + list_index
				+ "]";
	}
	
	

}
