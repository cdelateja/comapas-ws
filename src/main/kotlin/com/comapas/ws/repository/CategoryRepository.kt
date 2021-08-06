package com.comapas.ws.repository

import com.comapas.ws.model.Category
import com.comapas.ws.model.Criterion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CategoryRepository : JpaRepository<Category, Long> {

    fun findByName(name: String): Optional<Category>

    fun findByOrderByPositionAsc(): List<Category>
}
