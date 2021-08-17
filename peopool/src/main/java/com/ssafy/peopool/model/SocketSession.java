package com.ssafy.peopool.model;

public class SocketSession {
	int ind_index;
	String sessionid;

	public SocketSession() {
	}

	public int getInd_index() {
		return ind_index;
	}

	public void setInd_index(int ind_index) {
		this.ind_index = ind_index;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WebSocketSession [ind_index=").append(ind_index).append(", sessionid=").append(sessionid)
				.append("]");
		return builder.toString();
	}

}
