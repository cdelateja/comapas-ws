package com.comapas.ws.service

import com.comapas.ws.dto.request.FieldReq
import com.comapas.ws.model.Field
import com.comapas.ws.repository.FieldRepository
import com.comapas.ws.util.model.FieldUtil
import org.springframework.stereotype.Service

@Service
class FieldService(private val fieldRepository: FieldRepository) {

    fun findAll(): List<Field> {
        return fieldRepository.findAll()
    }

    fun createField(req: FieldReq): Field {
        val field = FieldUtil.toModel(req)
        return fieldRepository.save(field)
    }
}
