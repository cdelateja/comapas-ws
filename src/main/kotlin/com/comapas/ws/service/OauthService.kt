package com.comapas.ws.service

import com.comapas.ws.util.WebUtil
import com.comapas.ws.util.exception.ServiceException
import com.ct.rest.backend.client.Client
import com.ct.rest.backend.util.exception.RestException
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class OauthService(
    private val restTemplate: RestTemplate,
    private val webUtil: WebUtil
) {

    companion object {
        private val log = LogManager.getLogger(OauthService::class)
    }

    @Value("\${oauth.url}")
    private val oauthUrl: String = ""

    @Throws(ServiceException::class)
    fun validate(): Boolean {
        try {
            Client.getClient(restTemplate)
                .addTokenHeaders(webUtil.getToken())
                .get("$oauthUrl/validate")
        } catch (e: RestException) {
            log.error("Error on validate: ${e.message}")
            return false
        }
        return true
    }
}
