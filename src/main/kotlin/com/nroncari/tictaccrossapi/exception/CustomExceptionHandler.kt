package com.nroncari.tictaccrossapi.exception

import com.nroncari.tictaccrossapi.controller.dto.StandardError
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class CustomExceptionHandler {

    @ExceptionHandler(NumberPlayersException::class)
    fun handleInvalidGameException(e: NumberPlayersException): ResponseEntity<StandardError> {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(StandardError(e.message!!))
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(e: NotFoundException): ResponseEntity<StandardError> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StandardError(e.message!!))
    }
}