package com.comapas.ws.service

import com.comapas.ws.dto.request.TestCriterionReq
import com.comapas.ws.model.Field
import com.comapas.ws.model.InstituteField
import com.comapas.ws.repository.InstituteFieldRepository
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
class InstituteFieldService(
    private val instituteFieldRepository: InstituteFieldRepository,
    private val fieldService: FieldService
) {

    @Transactional
    fun findByInstitute(idInstitute: Long): List<InstituteField> {
        return instituteFieldRepository.findByIdInstitute(idInstitute)
    }

    /**
     * saves response test and calculate score
     */
    @Transactional
    fun save(idInstitute: Long, request: List<TestCriterionReq>): Int {
        var score = 0
        request.forEach { testCriterionReq ->
            testCriterionReq.fields.forEach {
                val field = fieldService.findByName(it.name)
                val instituteField = InstituteField()
                instituteField.idInstitute = idInstitute
                instituteField.idField = field.idField
                instituteField.value = it.value
                score += if(Objects.nonNull(it.value)) calculateScore(it.value!!, field) else 0
                instituteFieldRepository.save(instituteField)
            }
        }
        return score
    }

    private fun calculateScore(value: String, field: Field): Int {
        if (Objects.nonNull(field.scoreValue) && value.trim() == field.scoreValue!!.trim()) {
            return field.score
        }
        return 0
    }

    @Transactional
    fun deleteByIdInstitute(idInstitute: Long) {
        findByInstitute(idInstitute).forEach {
            instituteFieldRepository.delete(it)
        }
    }
}
