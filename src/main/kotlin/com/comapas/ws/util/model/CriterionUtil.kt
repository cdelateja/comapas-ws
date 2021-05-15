package com.comapas.ws.util.model

import com.comapas.ws.dto.request.CriterionReq
import com.comapas.ws.model.Criterion
import com.comapas.ws.util.mapper.OrikaMapper
import org.apache.commons.lang3.StringUtils

class CriterionUtil {

    companion object {

        fun toModel(req: CriterionReq): Criterion {
            configMapperFieldReqModel()
            val criterion = OrikaMapper.transform(req, Criterion::class.java)
            criterion.code = toCamelCase(criterion.name!!)
            return criterion
        }

        private fun configMapperFieldReqModel() {
            OrikaMapper.mapperFactory.classMap(Criterion::class.java, CriterionReq::class.java)
                .byDefault()
                .register()
        }

        private fun toCamelCase(myValue: String): String{
            val clean = myValue.replace("ñ", "n")
            val parts = clean.split(" ")
            val newValue = StringBuffer()
            parts.forEach {
                newValue.append(StringUtils.capitalize(it))
            }
            return newValue.toString()
        }
    }
}
