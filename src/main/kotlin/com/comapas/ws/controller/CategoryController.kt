package com.comapas.ws.controller

import com.comapas.ws.dto.request.CategoryCriterionReq
import com.comapas.ws.dto.request.CategoryReq
import com.comapas.ws.dto.request.CriterionFieldReq
import com.comapas.ws.dto.request.PositionReq
import com.comapas.ws.dto.response.Response
import com.comapas.ws.service.CategoryService
import org.apache.logging.log4j.LogManager
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping(value = ["/category"])
class CategoryController(private val categoryService: CategoryService) {

    companion object {
        private val log = LogManager.getLogger(CategoryController::class)
    }

    @GetMapping(value = ["/findAll"], produces = ["application/json"])
    fun findAll(): ResponseEntity<*> {
        return Response.ok(categoryService.findAll())
    }

    @PostMapping(value = ["/save"], consumes = ["application/json"], produces = ["application/json"])
    fun save(@RequestBody request: CategoryReq): ResponseEntity<*> {
        return Response.ok(categoryService.createCategory(request))
    }

    @DeleteMapping(value = ["/delete"], produces = ["application/json"])
    fun delete(@RequestParam idCategory: Long): ResponseEntity<*> {
        return Response.ok(categoryService.delete(idCategory))
    }

    @PostMapping(value = ["/position"], consumes = ["application/json"], produces = ["application/json"])
    fun position(@RequestBody request: PositionReq): ResponseEntity<*> {
        return Response.ok(categoryService.position(request))
    }

    @PostMapping(value = ["/addCriterion"], consumes = ["application/json"], produces = ["application/json"])
    fun addFields(@RequestBody request: CategoryCriterionReq): ResponseEntity<*> {
        return Response.ok(categoryService.addCriterion(request))
    }
}
