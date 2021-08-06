package com.comapas.ws.util.exception

import org.springframework.http.HttpStatus

class UnauthorizedException : ServiceException {

    constructor(message: String?): super(message) {
        this.status = HttpStatus.UNAUTHORIZED.value()
    }

    override fun toString(): String {
        return "Exception : {" +
                "status=" + status +
                " " +
                "message=" + message +
                '}'
    }
}
