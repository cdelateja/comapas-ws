package com.comapas.ws.repository

import com.comapas.ws.model.CriterionField
import com.comapas.ws.model.CriterionFieldId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CriterionFieldRepository : JpaRepository<CriterionField, CriterionFieldId> {
}
