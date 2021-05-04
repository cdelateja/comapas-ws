package com.comapas.ws.util.model

import com.comapas.ws.dto.request.CriterionFieldReq
import com.comapas.ws.dto.request.CriterionReq
import com.comapas.ws.model.Criterion
import com.comapas.ws.model.CriterionField
import com.comapas.ws.util.mapper.OrikaMapper

class CriterionUtil {

    companion object {

        fun toModel(req: CriterionReq): Criterion {
            configMapperFieldReqModel()
            return OrikaMapper.transform(req, Criterion::class.java)
        }

        fun toModelCriterionField(req: CriterionFieldReq): CriterionField {
            configMapperFieldReqModel()
            return OrikaMapper.transform(req, CriterionField::class.java)
        }

        fun configMapperFieldReqModel() {
            OrikaMapper.mapperFactory.classMap(Criterion::class.java, CriterionReq::class.java)
                .byDefault()
                .register()
        }
    }
}
