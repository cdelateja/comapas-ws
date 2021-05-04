package com.comapas.ws.service

import com.comapas.ws.dto.request.CriterionReq
import com.comapas.ws.model.Criterion
import com.comapas.ws.repository.CriterionRepository
import com.comapas.ws.util.model.CriterionUtil
import com.comapas.ws.util.model.FieldUtil
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class CriterionService(
    private val criterionRepository: CriterionRepository,
    private val fieldService: FieldService,
    private val criterionFieldService: CriterionFieldService
) {

    fun findAll(): List<Criterion> {
        return criterionRepository.findAll()
    }

    @Transactional
    fun createCriterion(req: CriterionReq): Criterion {
        var criterion = CriterionUtil.toModel(req)
        criterion = criterionRepository.save(criterion)
        req.fields.forEach {
            var criterionField = CriterionUtil.toModelCriterionField(it)
            criterionField.idCriterion = criterion.idCriterion
            criterionField = criterionFieldService.createCriterionField(criterionField)
            criterion.fields.add(criterionField)
        }
        return criterion
    }
}
