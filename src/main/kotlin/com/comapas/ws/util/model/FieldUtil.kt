package com.comapas.ws.util.model

import com.comapas.ws.dto.request.FieldReq
import com.comapas.ws.model.Field
import com.comapas.ws.util.mapper.OrikaMapper

class FieldUtil {

    companion object {
        const val INPUT = "input"
        const val DECIMAL = "decimal"
        const val CHECKBOX = "checkbox"
        const val DATE = "date"
        const val NUMBER = "number"
        const val PERCENTAGE = "percentage"
        const val SELECT = "select"

        fun toModel(req: FieldReq): Field {
            configMapperFieldReqModel()
            return OrikaMapper.transform(req, Field::class.java)
        }

        fun configMapperFieldReqModel() {
            OrikaMapper.mapperFactory.classMap(Field::class.java, FieldReq::class.java)
                .byDefault()
                .register()
        }
    }
}
