package spring.jh.myapp.websocket.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import spring.jh.myapp.websocket.model.ChatRepository;
import spring.jh.myapp.websocket.model.ChattingMessage;
import spring.jh.myapp.websocket.model.ChattingRoom;

public class MultiChatRoomHandler extends TextWebSocketHandler{
	private static final Logger logger = LoggerFactory.getLogger(MultiChatRoomHandler.class);
	
	@Autowired
	private ChatRepository chatRepository;
	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message)
				throws Exception{
		logger.info("메시지 전송 = {} : {}", session, message.getPayload());
		ChattingMessage msg = objectMapper.readValue(message.getPayload(), ChattingMessage.class);
		System.out.println(msg);
		ChattingRoom room = chatRepository.selectRoom(msg.getRoomId());
		room.handlerMessage(session, msg);
	}
}
