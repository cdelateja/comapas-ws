package com.comapas.ws.service

import com.comapas.ws.model.CriterionField
import com.comapas.ws.repository.CriterionFieldRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class CriterionFieldService(
    private val criterionFieldRepository: CriterionFieldRepository,
    private val fieldService: FieldService
) {

    @Transactional
    fun findAll(): List<CriterionField> {
        return criterionFieldRepository.findAll()
    }

    @Transactional
    fun findByCriterion(idCriterion: Long): List<CriterionField> {
        return criterionFieldRepository.findByIdCriterion(idCriterion)
    }

    @Transactional
    fun createCriterionField(criterionField: CriterionField): CriterionField {
        val criterionField = criterionFieldRepository.saveAndFlush(criterionField)
        criterionField.field = fieldService.findById(criterionField.idField!!)
        return criterionField
    }

    @Transactional
    fun deleteByIdCriterion(idCriterion: Long) {
        findByCriterion(idCriterion).forEach {
            criterionFieldRepository.delete(it)
        }
    }
}
