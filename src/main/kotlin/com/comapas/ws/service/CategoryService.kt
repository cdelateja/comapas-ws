package com.comapas.ws.service

import com.comapas.ws.dto.request.CategoryCriterionReq
import com.comapas.ws.dto.request.CategoryReq
import com.comapas.ws.dto.request.PositionReq
import com.comapas.ws.model.Category
import com.comapas.ws.repository.CategoryRepository
import com.comapas.ws.util.exception.ServiceException
import com.comapas.ws.util.model.CategoryUtil
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository,
    private val criterionService: CriterionService
) {

    @Transactional
    fun findAll(): List<Category> {
        val list = categoryRepository.findByOrderByPositionAsc()
        list.forEach { it.criterionList = criterionService.findAllOrderByPosition(it.idCategory!!) }
        return list
    }

    @Transactional
    fun findById(id: Long): Category {
        return categoryRepository.findById(id)
            .orElseThrow { ServiceException(HttpStatus.NOT_FOUND.value(), "Category not found by id: $id") }
    }

    @Transactional
    fun delete(id: Long): Long {
        categoryRepository.delete(findById(id))
        return id
    }

    @Transactional
    fun createCategory(req: CategoryReq): Category {
        if (Objects.nonNull(req.idCategory)) return update(req)
        val category = CategoryUtil.toModel(req)
        category.position = lastPosition()
        return categoryRepository.save(category)
    }

    fun update(req: CategoryReq): Category {
        val category = findById(req.idCategory!!)
        category.name = req.name
        return categoryRepository.save(category)
    }

    @Transactional
    fun position(req: PositionReq): PositionReq {
        var position = 0
        req.order.forEach {
            val category = findById(it)
            category.position = position++
            categoryRepository.save(category)
        }
        return req
    }

    @Transactional
    fun lastPosition(): Int {
        return categoryRepository.count().toInt()
    }

    @Transactional
    fun addCriterion(req: CategoryCriterionReq): Category {
        val category = findById(req.idCategory)
        criterionService.removeCategory(category.idCategory!!, req.criterionList)
        category.criterionList = criterionService.addCategory(category.idCategory!!, req.criterionList)
        return category
    }
}
