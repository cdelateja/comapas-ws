package com.comapas.ws.service

import com.comapas.ws.util.WebUtil
import com.comapas.ws.util.exception.ServiceException
import com.comapas.ws.util.exception.UnauthorizedException
import com.ct.rest.backend.client.Client
import com.ct.rest.backend.util.exception.RestException
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
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

    @Throws(UnauthorizedException::class)
    fun validate(): Boolean {
        try {
            Client.getClient(restTemplate)
                .addTokenHeaders(webUtil.getToken())
                .get("$oauthUrl/validate")
        } catch (e: RestException) {
            throw UnauthorizedException("Error on validate: ${e.message}")
        } catch (e: NullPointerException) {
            throw UnauthorizedException("Error on validate no token on petition: ${e.message}")
        }
        return true
    }
}
