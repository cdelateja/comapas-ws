package com.comapas.ws.repository

import com.comapas.ws.model.Field
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface FieldRepository : JpaRepository<Field, Long> {
    fun findByName(name: String): Optional<Field>
}
