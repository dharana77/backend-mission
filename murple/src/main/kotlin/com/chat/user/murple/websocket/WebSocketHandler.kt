package com.chat.user.murple.websocket

import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import org.springframework.stereotype.Component
import java.util.concurrent.CopyOnWriteArrayList


@Component
class WebSocketHandler: TextWebSocketHandler(){

    private val sessions = CopyOnWriteArrayList<WebSocketSession>()

    override fun afterConnectionEstablished(session: WebSocketSession) {
        sessions.add(session)
        println("새로운 WebSocket 연결: ${session.id}")
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val payload = message.payload
        println("메시지 수신: $payload")

        // 연결된 모든 클라이언트에게 메시지 전송
        sessions.forEach {
            if (it.isOpen) {
                it.sendMessage(TextMessage("Echo: $payload"))
            }
        }
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: org.springframework.web.socket.CloseStatus) {
        sessions.remove(session)
        println("WebSocket 연결 종료: ${session.id}")
    }
}
