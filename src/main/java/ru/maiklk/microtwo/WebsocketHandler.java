package ru.maiklk.microtwo;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebsocketHandler extends TextWebSocketHandler {


    private final List<WebSocketSession> sessions = new ArrayList<>();

    public void sendToAll(String message) throws IOException {
        for (WebSocketSession session: this.sessions) {
            session.sendMessage(new TextMessage(message));
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        sessions.add(session);
        System.out.printf("%s connected", session.getRemoteAddress().getHostName());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        sessions.remove(session);
    }
}
