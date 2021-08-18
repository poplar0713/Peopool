package com.ssafy.peopool.webrtc;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.JsonObject;

@Component
public class Usermap {
	Map<String, WebSocketSession> users = new ConcurrentHashMap<String, WebSocketSession>();
	private static final Logger log = LoggerFactory.getLogger(Usermap.class);
	
	public void usersadd(String idx,WebSocketSession session) {
		if(!users.containsKey(idx)) {
			users.put(idx,session);
		}
		log.info(users.toString());
	}
	
	public void getsession(int idx) {
		log.info("map get sseion-------");
		Iterator<String> keys = users.keySet().iterator();
        while (keys.hasNext()){
            String key = keys.next();
            log.info("KEY : " +key+" value:"+ users.get(key)); // Key2 , Key1, Key4, Key3, Key5
        }


		String index = Integer.toString(idx);
		log.info("map get session------{}",users.containsKey(index));
		JsonObject response = new JsonObject();
		response.addProperty("user", idx);
		response.addProperty("new", "new");
		if(users.containsKey(index)) {
			WebSocketSession session = users.get(index);
			log.info("session:----{}",session);
			try {
				log.info("responseee -------{}",session);
				session.sendMessage(new TextMessage(response.toString()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
