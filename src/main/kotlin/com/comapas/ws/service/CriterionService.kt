package com.comapas.ws.service

import com.comapas.ws.dto.request.CriterionFieldReq
import com.comapas.ws.dto.request.CriterionReq
import com.comapas.ws.dto.request.IdReq
import com.comapas.ws.dto.request.PositionReq
import com.comapas.ws.model.Criterion
import com.comapas.ws.model.CriterionField
import com.comapas.ws.repository.CriterionRepository
import com.comapas.ws.util.exception.ServiceException
import com.comapas.ws.util.model.CriterionUtil
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
class CriterionService(
    private val criterionRepository: CriterionRepository,
    private val criterionFieldService: CriterionFieldService
) {

    @Transactional
    fun findAll(): List<Criterion> {
        return criterionRepository.findAll()
    }

    @Transactional
    fun findAllOrderByPosition(idCategory: Long): MutableList<Criterion> {
        return criterionRepository.findByIdCategoryOrderByPositionAsc(idCategory)
    }

    @Transactional
    fun findById(id: Long): Criterion {
        return criterionRepository.findById(id)
            .orElseThrow { ServiceException(HttpStatus.NOT_FOUND.value(), "Criterion not found by id: $id") }
    }

    @Transactional
    fun createCriterion(req: CriterionReq): Criterion {
        return if (Objects.isNull(req.idCriterion)) {
            val criterion = CriterionUtil.toModel(req)
            criterionRepository.save(criterion)
        } else {
            val criterion = findById(req.idCriterion!!)
            criterion.name = req.name
            criterionRepository.save(criterion)
        }
    }

    @Transactional
    fun addFields(req: CriterionFieldReq): Criterion {
        val criterion = findById(req.idCriterion)
        criterion.fields = arrayListOf()
        criterionFieldService.deleteByIdCriterion(criterion.idCriterion!!)
        req.fields.forEach {
            val criterionField = CriterionField()
            criterionField.idField = it
            criterionField.idCriterion = criterion.idCriterion
            criterion.fields.add(criterionFieldService.createCriterionField(criterionField))
        }
        return findById(req.idCriterion)
    }

    @Transactional
    fun removeCategory(idCategory: Long, criterionList: List<Long>) {
        val categories = criterionRepository.findByIdCategory(idCategory)
        categories.forEach {
            val found = criterionList.firstOrNull() { id -> id == it.idCriterion }
            if (Objects.isNull(found)) {
                it.idCategory = null
                it.position = null
                criterionRepository.save(it)
            }
        }
    }

    @Transactional
    fun addCategory(idCategory: Long, criterionList: List<Long>): MutableList<Criterion> {
        var position = criterionList.size - 1
        val finalList: MutableList<Criterion> = arrayListOf()
        criterionList.forEach {
            val criterion = findById(it)
            criterion.idCategory = idCategory
            criterion.position = if (Objects.isNull(criterion.position)) position++ else criterion.position
            finalList.add(criterionRepository.save(criterion))
        }
        return finalList
    }

    @Transactional
    fun position(req: PositionReq): PositionReq {
        var position = 0
        req.order.forEach {
            val criterion = findById(it)
            criterion.position = position++
            criterionRepository.save(criterion)
        }
        return req
    }

    @Transactional
    fun removeCategory(req: IdReq): Criterion {
        val criterion = findById(req.id)
        criterion.idCategory = null
        criterion.position = null
        return criterionRepository.save(criterion)
    }
}
