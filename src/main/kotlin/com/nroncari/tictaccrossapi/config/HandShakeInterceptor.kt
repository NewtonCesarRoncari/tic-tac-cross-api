package com.nroncari.tictaccrossapi.config

import lombok.extern.slf4j.Slf4j
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor

@Slf4j
class HandshakeInterceptor : HttpSessionHandshakeInterceptor() {
    /**
     * Before websocket handshake
     * You can put some data into `attributes` here, and get it in WebSocketHandler's session
     *
     *
     * WebSocket 握手前 —— 可以设置数据到 attributes 中，并在 WebSocketHandler 的 session 中获取
     */
    @Throws(Exception::class)
    override fun beforeHandshake(
        request: ServerHttpRequest,
        response: ServerHttpResponse,
        wsHandler: WebSocketHandler,
        attributes: Map<String, Any>
    ): Boolean {
        println("HandshakeInterceptor: beforeHandshake")
        println("Attributes: $attributes")
        return super.beforeHandshake(request, response, wsHandler, attributes)
    }

    /**
     * After websocket handshake
     */
    override fun afterHandshake(
        request: ServerHttpRequest,
        response: ServerHttpResponse,
        wsHandler: WebSocketHandler,
        ex: Exception?
    ) {
        println("HandshakeInterceptor: afterHandshake")
        super.afterHandshake(request, response, wsHandler, ex)
    }
}