package com.comapas.ws.controller

import com.comapas.ws.dto.request.TestReq
import com.comapas.ws.dto.response.Response
import com.comapas.ws.service.InstituteService
import org.apache.logging.log4j.LogManager
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping(value = ["/institute"])
class InstituteController(
    private val instituteService: InstituteService
) {

    companion object {
        private val log = LogManager.getLogger(InstituteController::class)
    }

    @GetMapping(value = ["/find"], produces = ["application/json"])
    fun find(@RequestParam idInstitute: Long): ResponseEntity<*> {
        return Response.ok(instituteService.findById(idInstitute))
    }

    @PostMapping(value = ["/test"], consumes = ["application/json"], produces = ["application/json"])
    fun test(@RequestBody request: TestReq): ResponseEntity<*> {
        return Response.ok(instituteService.save(request))
    }

    @GetMapping(value = ["/institutesInfo"], produces = ["application/json"])
    fun getInstitutesInfo(): ResponseEntity<*> {
        return Response.ok(instituteService.getInstitutesInfo())
    }

    @GetMapping(value = ["/instituteInfo"], produces = ["application/json"])
    fun getInstituteInfo(@RequestParam idInstitute: Long): ResponseEntity<*> {
        return Response.ok(instituteService.getInstituteInfo(idInstitute))
    }
}
