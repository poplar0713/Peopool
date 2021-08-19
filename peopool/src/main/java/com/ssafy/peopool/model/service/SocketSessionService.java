package com.ssafy.peopool.model.service;

import org.springframework.stereotype.Repository;

import com.ssafy.peopool.model.SocketSession;

@Repository
public interface SocketSessionService {
	
	// 웹소켓 세션 아이디 업데이트
	int updateWebSocketSession(SocketSession websocketsession);
	
	// 웹소켓 세션 아이디 찾기
	SocketSession getSocketSessionID(int ind_index);
}
