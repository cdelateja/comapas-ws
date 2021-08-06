package com.comapas.ws.repository

import com.comapas.ws.model.Criterion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface CriterionRepository : JpaRepository<Criterion, Long> {

    fun findByName(name: String): Optional<Criterion>

    fun findByIdCategory(@Param("idCategory") idCategory: Long): List<Criterion>

    fun findByIdCategoryOrderByPositionAsc(idCategory: Long): MutableList<Criterion>

    fun findByOrderByPositionAsc(): List<Criterion>
}
