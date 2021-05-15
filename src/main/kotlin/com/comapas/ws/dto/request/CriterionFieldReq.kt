package com.comapas.ws.dto.request

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

class CriterionFieldReq(
    var idCriterion: Long,
    var fields: List<Long> = arrayListOf()
) {

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
    }
}
