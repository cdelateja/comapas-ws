package com.comapas.ws.controller

import com.comapas.ws.dto.request.FieldReq
import com.comapas.ws.dto.response.Response
import com.comapas.ws.service.FieldService
import org.apache.logging.log4j.LogManager
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping(value = ["/field"])
class FieldController(private val fieldService: FieldService) {

    companion object {
        private val log = LogManager.getLogger(FieldController::class)
    }

    @GetMapping(value = ["/findAll"], produces = ["application/json"])
    fun findAll(): ResponseEntity<*> {
        return Response.ok(fieldService.findAll())
    }

    @GetMapping(value = ["/types"], produces = ["application/json"])
    fun getTypes(): ResponseEntity<*> {
        return Response.ok(fieldService.getTypes())
    }

    @PostMapping(value = ["/save"], consumes = ["application/json"], produces = ["application/json"])
    fun save(@RequestBody request: FieldReq): ResponseEntity<*> {
        return Response.ok(fieldService.createField(request))
    }

    @GetMapping(value = ["/fieldsInfo"], produces = ["application/json"])
    fun getFieldsInfo(): ResponseEntity<*> {
        return Response.ok(fieldService.getFieldsInfo())
    }
}
