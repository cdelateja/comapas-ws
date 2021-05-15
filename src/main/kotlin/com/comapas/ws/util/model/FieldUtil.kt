package com.comapas.ws.util.model

import com.comapas.ws.dto.request.FieldReq
import com.comapas.ws.model.Field
import com.comapas.ws.util.mapper.OrikaMapper
import org.apache.commons.lang3.RandomStringUtils

class FieldUtil {

    companion object {
        const val INPUT = "input"
        const val DECIMAL = "decimal"
        const val CHECKBOX = "checkbox"
        const val DATE = "date"
        const val NUMBER = "number"
        const val RADIO = "radio"
        const val SELECT = "select"

        fun toModel(req: FieldReq): Field {
            configMapperFieldReqModel()
            val field = OrikaMapper.transform(req, Field::class.java)
            val catalog = arrayListOf<String>()
            req.catalog.forEach { catalog.add(it.value) }
            field.catalog = if(catalog.isNotEmpty()) catalog.toString() else null
            field.name = randomName()
            return field
        }

        private fun configMapperFieldReqModel() {
            OrikaMapper.mapperFactory.classMap(Field::class.java, FieldReq::class.java)
                .byDefault()
                .register()
        }

        fun getTypes(): List<String> {
            return arrayListOf(INPUT, DECIMAL, CHECKBOX, DATE, NUMBER, SELECT, RADIO)
        }

        private fun randomName(): String {
            return RandomStringUtils.random(10, true, false)
        }
    }
}
