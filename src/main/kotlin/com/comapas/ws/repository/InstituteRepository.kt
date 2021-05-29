package com.comapas.ws.repository

import com.comapas.ws.model.Institute
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InstituteRepository : JpaRepository<Institute, Long> {

}
