package com.comapas.ws.controller

import com.comapas.ws.dto.request.ConfigReq
import com.comapas.ws.dto.response.Response
import com.comapas.ws.service.ConfigService
import org.apache.logging.log4j.LogManager
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping(value = ["/config"])
class ConfigController(private val configService: ConfigService) {

    companion object {
        private val log = LogManager.getLogger(ConfigController::class)
    }

    @GetMapping(value = ["/find"], produces = ["application/json"])
    fun findAll(@RequestParam name: String): ResponseEntity<*> {
        return ResponseEntity(Response(HttpStatus.OK.value(), configService.findByName(name)), HttpStatus.OK)
    }


    @PostMapping(value = ["/save"], consumes = ["application/json"], produces = ["application/json"])
    fun save(@RequestBody request: ConfigReq): ResponseEntity<*> {
        return ResponseEntity(
            Response(HttpStatus.OK.value(), configService.createConfig(request)), HttpStatus.OK
        )
    }
}
