package com.comapas.ws.dto.response

import com.comapas.ws.util.exception.ServiceException
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class Response {

    var responseStatus: Int? = null
    var responseError: String? = null
    var result: Any? = null

    companion object {
        fun ok(result: Any?): ResponseEntity<*> {
            return ResponseEntity(Response(HttpStatus.OK.value(), result), HttpStatus.OK)
        }
    }

    constructor() {}

    constructor(e: ServiceException) {
        setException(e)
    }

    constructor(responseStatus: Int, result: Any?) {
        this.responseStatus = responseStatus
        this.result = result
        this.responseError = "OK"
    }


    fun setException(e: ServiceException) {
        responseStatus = e.status
        responseError = e.message
    }

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
    }
}
