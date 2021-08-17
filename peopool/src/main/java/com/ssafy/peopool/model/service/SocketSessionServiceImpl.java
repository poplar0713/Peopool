package com.ssafy.peopool.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.peopool.model.SocketSession;
import com.ssafy.peopool.model.repo.SocketSessionRepo;

@Service
public class SocketSessionServiceImpl implements SocketSessionService {
	
	private static final Logger logger = LoggerFactory.getLogger( SocketSessionServiceImpl.class);
	
	@Autowired
	SocketSessionRepo socketSessionRepo;
	
	@Override
	public int updateWebSocketSession(SocketSession websocketsession) {
		return socketSessionRepo.updateWebSocketSession(websocketsession);
	}

	@Override
	public SocketSession getSocketSessionID(int ind_index) {
		
		return socketSessionRepo.getSocketSessionID(ind_index);
	}
	
	
}
