package com.comapas.ws.service

import com.comapas.ws.model.CriterionField
import com.comapas.ws.repository.CriterionFieldRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class CriterionFieldService(
    private val criterionFieldRepository: CriterionFieldRepository
) {

    fun findAll(): List<CriterionField> {
        return criterionFieldRepository.findAll()
    }

    @Transactional
    fun createCriterionField(criterionField: CriterionField): CriterionField {
        return criterionFieldRepository.save(criterionField)
    }
}
