package com.nroncari.tictaccrossapi.config

import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor

@Slf4j
class HandshakeInterceptor : HttpSessionHandshakeInterceptor() {

    private val log = LoggerFactory.getLogger(javaClass)

    @Throws(Exception::class)
    override fun beforeHandshake(
        request: ServerHttpRequest,
        response: ServerHttpResponse,
        wsHandler: WebSocketHandler,
        attributes: Map<String, Any>
    ): Boolean {
        log.info("beforeHandshake")
        return super.beforeHandshake(request, response, wsHandler, attributes)
    }

    override fun afterHandshake(
        request: ServerHttpRequest,
        response: ServerHttpResponse,
        wsHandler: WebSocketHandler,
        ex: Exception?
    ) {
        log.info("afterHandshake")
        super.afterHandshake(request, response, wsHandler, ex)
    }
}