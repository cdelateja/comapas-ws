package com.comapas.ws.controller

import com.comapas.ws.dto.response.Response
import com.comapas.ws.util.exception.ServiceException
import com.comapas.ws.util.exception.UnauthorizedException
import org.apache.logging.log4j.LogManager
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.util.NestedServletException


@ControllerAdvice
class ControllerExceptionHandler {

    companion object {
        private val log = LogManager.getLogger(ControllerExceptionHandler::class)
    }

    @ExceptionHandler(ServiceException::class)
    fun exceptionHandler(e: ServiceException): ResponseEntity<*> {
        log.error("ServiceException handler: $e")
        return ResponseEntity(Response(e), HttpStatus.OK)
    }

    @ExceptionHandler(UnauthorizedException::class)
    fun exceptionHandler(e: UnauthorizedException): ResponseEntity<*> {
        log.error("UnauthorizedException handler: $e")
        return ResponseEntity(Response(e), HttpStatus.UNAUTHORIZED)
    }

}
