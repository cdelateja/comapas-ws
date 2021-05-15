package com.comapas.ws.controller

import com.comapas.ws.dto.request.CriterionFieldReq
import com.comapas.ws.dto.request.CriterionReq
import com.comapas.ws.dto.response.Response
import com.comapas.ws.service.CriterionService
import org.apache.logging.log4j.LogManager
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping(value = ["/criterion"])
class CriterionController(private val criterionService: CriterionService) {

    companion object {
        private val log = LogManager.getLogger(CriterionController::class)
    }

    @GetMapping(value = ["/findAll"], produces = ["application/json"])
    fun findAll(): ResponseEntity<*> {
        return ResponseEntity(Response(HttpStatus.OK.value(), criterionService.findAll()), HttpStatus.OK)
    }

    @PostMapping(value = ["/save"], consumes = ["application/json"], produces = ["application/json"])
    fun save(@RequestBody request: CriterionReq): ResponseEntity<*> {
        return ResponseEntity(
            Response(HttpStatus.OK.value(), criterionService.createCriterion(request)), HttpStatus.OK
        )
    }

    @PostMapping(value = ["/addFields"], consumes = ["application/json"], produces = ["application/json"])
    fun addFields(@RequestBody request: CriterionFieldReq): ResponseEntity<*> {
        return ResponseEntity(
            Response(HttpStatus.OK.value(), criterionService.addFields(request)), HttpStatus.OK
        )
    }
}
