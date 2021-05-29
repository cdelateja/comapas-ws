package com.comapas.ws.service

import com.comapas.ws.dto.request.FieldReq
import com.comapas.ws.dto.response.FieldsInfoRes
import com.comapas.ws.model.Field
import com.comapas.ws.repository.FieldRepository
import com.comapas.ws.util.exception.ServiceException
import com.comapas.ws.util.model.FieldUtil
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class FieldService(private val fieldRepository: FieldRepository) {

    @Transactional
    fun findAll(): List<Field> {
        return fieldRepository.findAll()
    }

    @Transactional
    fun findById(id: Long): Field {
        return fieldRepository.findById(id)
            .orElseThrow { ServiceException(HttpStatus.NOT_FOUND.value(), "Criterion not found by id: $id") }
    }

    @Transactional
    fun findByName(name: String): Field {
        return fieldRepository.findByName(name)
            .orElseThrow { ServiceException(HttpStatus.NOT_FOUND.value(), "Criterion not found by name: $name") }
    }

    @Transactional
    fun createField(req: FieldReq): Field {
        val field = FieldUtil.toModel(req)
        return fieldRepository.save(field)
    }

    @Transactional
    fun getTypes(): List<String> {
        return FieldUtil.getTypes()
    }

    @Transactional
    fun getFieldsInfo(): FieldsInfoRes {
        val fields = findAll()
        var totalScore = 0
        fields.forEach { totalScore += it.score }
        return FieldsInfoRes(totalScore, fields.size)
    }

}
