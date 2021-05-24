package com.comapas.ws.service

import com.comapas.ws.dto.request.ConfigReq
import com.comapas.ws.model.Config
import com.comapas.ws.repository.ConfigRepository
import com.comapas.ws.util.exception.ServiceException
import com.comapas.ws.util.model.ConfigUtil
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class ConfigService(private val configRepository: ConfigRepository) {

    @Transactional
    fun findByName(id: String): Config {
        return configRepository.findByName(id)
            .orElseThrow { ServiceException(HttpStatus.NOT_FOUND.value(), "Config not found by id: $id") }
    }

    @Transactional
    fun createConfig(req: ConfigReq): Config {
        val config = ConfigUtil.toModel(req)
        return configRepository.save(config)
    }

}
