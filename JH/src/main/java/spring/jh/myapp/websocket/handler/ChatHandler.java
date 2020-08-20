package spring.jh.myapp.websocket.handler;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatHandler extends TextWebSocketHandler {

	private List<WebSocketSession> sessionList = new ArrayList<>();
	
	private static final Logger logger = 
			LoggerFactory.getLogger(ChatHandler.class);
	
	// 채팅방 접속
	@Override
	public void afterConnectionEstablished(WebSocketSession session)
				throws Exception{
		sessionList.add(session);
		logger.info("{} 연결됨",session.getId());
		logger.info("채팅방 입장 : {}", session.getId());
	}
	
	// 메시지 공유
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message)
				throws Exception{
		logger.info("{} 로부터 {}를 받음", session.getId(), message.getPayload());
		for(WebSocketSession s : sessionList) {
			s.sendMessage(new TextMessage(session.getId() + ":" + message.getPayload()));
		}
	}
	
	// 채팅방 나가기
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status)
				throws Exception{
		sessionList.remove(session);
		logger.info("{} 연결 끊음", session.getId());
		logger.info("채팅방 퇴장 : {}", session.getId());
	}
}
