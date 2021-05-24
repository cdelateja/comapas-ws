package com.comapas.ws.util

import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest


@Component
class WebUtil(
    private val request: HttpServletRequest
) {

    fun getToken(): String {
        return request.getHeader("Authorization")
    }

    fun ifOutOfWebRequest(): Boolean {
        return try {
            request.getHeader("Authorization")
            false
        } catch (e: IllegalStateException) {
            true
        }
    }

}
