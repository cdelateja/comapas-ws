package com.comapas.ws.repository

import com.comapas.ws.model.Config
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ConfigRepository : JpaRepository<Config, Long> {

    fun findByName(name: String): Optional<Config>
}
