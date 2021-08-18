package com.ssafy.peopool.model.repo;

import org.springframework.stereotype.Repository;

import com.ssafy.peopool.model.SocketSession;

@Repository
public interface SocketSessionRepo {
	
	int updateWebSocketSession(SocketSession websocketsession);
	
	SocketSession getSocketSessionID(int ind_index);
}
