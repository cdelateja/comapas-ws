package com.comapas.ws.repository

import com.comapas.ws.model.CriterionField
import com.comapas.ws.model.InstituteField
import com.comapas.ws.model.InstituteFieldId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface InstituteFieldRepository : JpaRepository<InstituteField, InstituteFieldId> {

    fun findByIdInstitute(@Param("idInstitute") idInstitute: Long): List<InstituteField>
}
