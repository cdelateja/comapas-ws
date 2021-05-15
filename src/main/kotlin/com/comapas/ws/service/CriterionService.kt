package com.comapas.ws.service

import com.comapas.ws.dto.request.CriterionFieldReq
import com.comapas.ws.dto.request.CriterionReq
import com.comapas.ws.model.Criterion
import com.comapas.ws.model.CriterionField
import com.comapas.ws.repository.CriterionRepository
import com.comapas.ws.util.exception.ServiceException
import com.comapas.ws.util.model.CriterionUtil
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class CriterionService(
    private val criterionRepository: CriterionRepository,
    private val fieldService: FieldService,
    private val criterionFieldService: CriterionFieldService
) {

    @Transactional
    fun findAll(): List<Criterion> {
        return criterionRepository.findAll()
    }

    @Transactional
    fun findById(id: Long): Criterion {
        return criterionRepository.findById(id)
            .orElseThrow { ServiceException(HttpStatus.NOT_FOUND.value(), "Criterion not found by id: $id") }
    }

    @Transactional
    fun createCriterion(req: CriterionReq): Criterion {
        val criterion = CriterionUtil.toModel(req)
        return criterionRepository.save(criterion)
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
}
