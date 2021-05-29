package com.comapas.ws.service

import com.comapas.ws.dto.request.TestReq
import com.comapas.ws.dto.response.InstituteInfoRes
import com.comapas.ws.model.Institute
import com.comapas.ws.repository.InstituteRepository
import com.comapas.ws.util.exception.ServiceException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
class InstituteService(
    private val instituteRepository: InstituteRepository,
    private val instituteFieldService: InstituteFieldService
) {

    @Transactional
    fun findAll(): List<Institute> {
        return instituteRepository.findAll()
    }

    @Transactional
    fun findById(id: Long): Institute {
        return instituteRepository.findById(id)
            .orElseThrow { ServiceException(HttpStatus.NOT_FOUND.value(), "Institute not found by id: $id") }
    }

    @Transactional
    fun save(request: TestReq): Institute {
        var institute = Institute()
        institute.idInstitute = request.idInstitute
        institute = instituteRepository.save(institute)
        institute.score = instituteFieldService.save(institute.idInstitute!!, request.criterion)
        return instituteRepository.save(institute)
    }

    @Transactional
    fun getInstitutesInfo(): MutableList<InstituteInfoRes> {
        val info: MutableList<InstituteInfoRes> = arrayListOf()
        findAll().forEach { info.add(getInstituteInfo(it)) }
        return info
    }

    private fun getInstituteInfo(institute: Institute): InstituteInfoRes {
        var totalFields = 0
        institute.fields.forEach { if (Objects.nonNull(it.value)) totalFields++ }
        return InstituteInfoRes(institute.idInstitute!!, institute.score, totalFields)
    }


}
