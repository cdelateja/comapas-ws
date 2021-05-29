package com.comapas.ws.controller

import com.comapas.ws.dto.response.Response
import com.comapas.ws.service.FieldFileService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@CrossOrigin
@RestController
@RequestMapping(value = ["/file"])
class FileController(private val fieldFileService: FieldFileService) {

    @PostMapping(value = ["/uploadFieldFile"], consumes = ["multipart/form-data"], produces = ["application/json"])
    fun uploadFile(
        @RequestParam file: MultipartFile,
        @RequestParam idField: Long,
        @RequestParam idInstitute: Long
    ): ResponseEntity<*>? {
        return Response.ok(fieldFileService.uploadFile(file, idField, idInstitute))
    }

    @GetMapping(value = ["/find"], produces = ["application/json"])
    fun find(@RequestParam idInstitute: Long): ResponseEntity<*> {
        return Response.ok(fieldFileService.findByIdInstitute(idInstitute))
    }

    @GetMapping(value = ["/fieldFile"], produces = ["application/json"])
    fun findFieldFile(@RequestParam idInstitute: Long, @RequestParam idField: Long): ResponseEntity<*> {
        return Response.ok(fieldFileService.getContent(idInstitute, idField))
    }
}
