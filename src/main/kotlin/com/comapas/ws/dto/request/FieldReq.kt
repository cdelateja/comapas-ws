package com.comapas.ws.dto.request

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

class FieldReq(
    var idField: Long?,
    var label: String,
    var type: String,
    var catalog: List<CatalogReq> = arrayListOf(),
    var required: Boolean,
    var evidence: Boolean,
    var score: Int,
    var scoreValue: String?
) {

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
    }
}
