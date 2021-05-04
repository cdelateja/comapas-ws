package com.comapas.ws.dto.request

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

class CriterionReq(
    var idCriterion: Long?,
    var name: String,
    var code: String,
    var fields: List<CriterionFieldReq> = arrayListOf()
) {

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
    }
}

class CriterionFieldReq(
    var idField: Long,
    var value: String? = null,
    var evidence: Boolean = false,
    var score: Int = 0
) {

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
    }
}
