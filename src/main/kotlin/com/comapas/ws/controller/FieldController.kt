package com.comapas.ws.controller

import com.comapas.ws.dto.response.Response
import com.comapas.ws.service.CriterionFieldService
import com.comapas.ws.util.exception.ServiceException
import org.apache.logging.log4j.LogManager
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
@RequestMapping(value = ["/field"])
class FieldController(private val fieldService: CriterionFieldService) {

    companion object {
        private val log = LogManager.getLogger(FieldController::class)
    }

    @GetMapping(value = ["/findAll"], produces = ["application/json"])
    fun findAll(): ResponseEntity<*>? {
        return try {
            ResponseEntity(Response(HttpStatus.OK.value(), fieldService.findAll()), HttpStatus.OK)
        } catch (e: ServiceException) {
            log.error("Error en obtenerDatos: $e")
            ResponseEntity(Response(e), HttpStatus.OK)
        }
    }
}
