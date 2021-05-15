package com.comapas.ws.repository

import com.comapas.ws.model.CriterionField
import com.comapas.ws.model.CriterionFieldId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
interface CriterionFieldRepository : JpaRepository<CriterionField, CriterionFieldId> {

    fun findByIdCriterion(@Param("idCriterion") idCriterion: Long): List<CriterionField>

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM CriterionField cF WHERE cF.idCriterion = :idCriterion")
    fun deleteByIdCriterion(@Param("idCriterion") idCriterion: Long): Int
}
