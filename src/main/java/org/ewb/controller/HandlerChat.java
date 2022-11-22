package org.ewb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class HandlerChat extends TextWebSocketHandler {
	// (<"chat_url", 방ID>, <"session", 세션>) - (<"chat_url", 방ID>, <"session", 세션>) - (<"chat_url", 방ID>, <"session", 세션>) 형태 
	private List<Map<String, Object>> sessionList = new ArrayList<Map<String, Object>>();
	
	// 클라이언트가 서버로 메세지 전송 처리
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

		super.handleTextMessage(session, message);
        
		// JSON --> Map으로 변환
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, String> mapReceive = objectMapper.readValue(message.getPayload(), Map.class);

		switch (mapReceive.get("cmd")) {
		
		// CLIENT 입장
		case "CMD_ENTER":
			// 세션 리스트에 저장
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("chat_url", mapReceive.get("chat_url"));
			map.put("session", session);
			sessionList.add(map);
			int j = 0;
			// 같은 채팅방에 입장 메세지 전송
			for (int i = 0; i < sessionList.size(); i++) {
				Map<String, Object> mapSessionList = sessionList.get(i);
				String chat_url = (String) mapSessionList.get("chat_url");
				if(chat_url.equals(mapReceive.get("chat_url"))) {
					j++;
				}
			}
			for (int i = 0; i < sessionList.size(); i++) {
				Map<String, Object> mapSessionList = sessionList.get(i);
				String chat_url = (String) mapSessionList.get("chat_url");
				WebSocketSession sess = (WebSocketSession) mapSessionList.get("session");
				
				if(chat_url.equals(mapReceive.get("chat_url"))) {
					Map<String, String> mapToSend = new HashMap<String, String>();
					mapToSend.put("chat_url", chat_url);
					mapToSend.put("cmd", "CMD_ENTER");
					mapToSend.put("id", mapReceive.get("id"));
					mapToSend.put("msg", mapReceive.get("id") +  "님이 입장 했습니다.");
					mapToSend.put("count", j+"");
					String jsonStr = objectMapper.writeValueAsString(mapToSend);
					sess.sendMessage(new TextMessage(jsonStr));
					
				}
			}
			break;
			
		// CLIENT 메세지
		case "CMD_MSG_SEND":
			// 같은 채팅방에 메세지 전송
			for (int i = 0; i < sessionList.size(); i++) {
				Map<String, Object> mapSessionList = sessionList.get(i);
				String chat_url = (String) mapSessionList.get("chat_url");
				WebSocketSession sess = (WebSocketSession) mapSessionList.get("session");

				if (chat_url.equals(mapReceive.get("chat_url"))) {
					Map<String, String> mapToSend = new HashMap<String, String>();
					mapToSend.put("chat_url", chat_url);
					mapToSend.put("cmd", "CMD_MSG_SEND");
					mapToSend.put("id", mapReceive.get("id"));
					mapToSend.put("msg", mapReceive.get("msg"));
					
					String jsonStr = objectMapper.writeValueAsString(mapToSend);
					sess.sendMessage(new TextMessage(jsonStr));
					
				}
			}
			break;
		}
	}

	// 클라이언트가 연결을 끊음 처리
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

		super.afterConnectionClosed(session, status);
        
		ObjectMapper objectMapper = new ObjectMapper();
		String now_chat_url = "";
		
		
		// 사용자 세션을 리스트에서 제거
		for (int i = 0; i < sessionList.size(); i++) {
			Map<String, Object> map = sessionList.get(i);
			String chat_url = (String) map.get("chat_url");
			WebSocketSession sess = (WebSocketSession) map.get("session");
			
			if(session.equals(sess)) {
				now_chat_url = chat_url;
				sessionList.remove(map);
				break;
			}	
		}
		
		// 같은 채팅방에 퇴장 메세지 전송 
		for (int i = 0; i < sessionList.size(); i++) {
			Map<String, Object> mapSessionList = sessionList.get(i);
			String chat_url = (String) mapSessionList.get("chat_url");
			WebSocketSession sess = (WebSocketSession) mapSessionList.get("session");
			if (chat_url.equals(now_chat_url)) {
				Map<String, String> mapToSend = new HashMap<String, String>();
				mapToSend.put("chat_url", chat_url);
				mapToSend.put("cmd", "CMD_EXIT");
				mapToSend.put("msg", session.getId() + "님이 퇴장 했습니다.");

				String jsonStr = objectMapper.writeValueAsString(mapToSend);
				sess.sendMessage(new TextMessage(jsonStr));
			}
		}
	}
}