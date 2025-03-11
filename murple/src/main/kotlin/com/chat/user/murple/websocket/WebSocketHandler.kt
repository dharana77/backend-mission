package com.chat.user.murple.websocket

import com.chat.user.murple.dto.chat.OutMatchUsers
import com.chat.user.murple.repository.MemberRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.concurrent.ConcurrentHashMap


@Component
class WebSocketHandler(private val objectMapper: ObjectMapper): TextWebSocketHandler(){

    private val sessions = ConcurrentHashMap<WebSocketSession, Long>()

    @Autowired
    lateinit var memberRepository: MemberRepository

    private val usedValues = ConcurrentHashMap.newKeySet<Long>()

    override fun afterConnectionEstablished(session: WebSocketSession) {
        // 로그인 대신 임의의 중복되지 않는 1-10 id 값을 갖도록 설정
        synchronized(usedValues) {
            val availableValues = (1..10).toSet() - usedValues
            if (availableValues.isNotEmpty()) {
                val assignedValue = availableValues.random()
                usedValues.add(assignedValue.toLong())
                sessions[session] = assignedValue.toLong()
                println("세션 값 할당: $assignedValue")
                println("새로운 WebSocket 연결: ${session.id}")
            } else {
                session.sendMessage(TextMessage("모든 값이 사용 중입니다!"))
                session.close()
            }
        }
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val payload = message.payload
        println("메시지 수신: $payload")

        val targetMembername = extractUsernameFromMessage(payload)

        if (targetMembername != null) {
            val members = memberRepository.findTop5ByNameOrderByCreatedAtAsc(targetMembername)

            if (members.isNotEmpty()) {
                // 보낸 사람에게만 전달
                val response = OutMatchUsers(
                    message = "Matching users for '$targetMembername': ${members.map { it.id }}",
                    matchedUserIds = members.map { it.id }
                )
                session.sendMessage(TextMessage(objectMapper.writeValueAsString(response)))
            }
        }
        else {
            // 연결된 모든 클라이언트에게 메시지 전송
            sessions.forEach {
                if (it.key.isOpen) {
                    it.key.sendMessage(TextMessage("Echo: $payload"))
                }
            }
        }
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: org.springframework.web.socket.CloseStatus) {
        sessions.remove(session)
        println("WebSocket 연결 종료: ${session.id}")
    }

    // 메시지에서 사용자 이름 추출
    fun extractUsernameFromMessage(message: String): String? {
        val regex = Regex("@(\\w+)")
        return regex.find(message)?.groupValues?.get(1)
    }
}
