package com.comapas.ws.init

import com.comapas.ws.dto.request.CriterionFieldReq
import com.comapas.ws.dto.request.CriterionReq
import com.comapas.ws.dto.request.FieldReq
import com.comapas.ws.service.CriterionService
import com.comapas.ws.service.FieldService
import com.comapas.ws.util.model.FieldUtil
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class InitData(
    private val fieldService: FieldService,
    private val criterionService: CriterionService
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        if (fieldService.findAll().isEmpty()) {
            var req = FieldReq(
                "field1", "¿Cuantos empleados tiene?", FieldUtil.INPUT,
                null, "", null
            )
            fieldService.createField(req)
            req = FieldReq(
                "field2", "¿Tiene carta de recomendación?", FieldUtil.INPUT,
                null, "", null
            )
            fieldService.createField(req)

        }
        if (criterionService.findAll().isEmpty()) {
            val reqCriterion = CriterionReq(
                null,
                "MiCriterion",
                "miCriterion",
                arrayListOf(CriterionFieldReq(1, "Si", true, 9),
                    CriterionFieldReq(2, "No", false, 5))
            )
            criterionService.createCriterion(reqCriterion)
        }
    }
}
