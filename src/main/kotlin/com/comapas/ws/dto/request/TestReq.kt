package com.comapas.ws.dto.request

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

class TestReq(
    var idInstitute: Long?,
    var criterion: List<TestCriterionReq>
) {

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
    }
}

class TestCriterionReq(
    var fields: List<TestFieldReq>
) {

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
    }
}

class TestFieldReq(
    var name: String,
    var value: String?,
) {

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
    }
}
