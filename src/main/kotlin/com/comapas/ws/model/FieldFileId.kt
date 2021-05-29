package com.comapas.ws.model

import java.io.Serializable

class FieldFileId : Serializable {

    var idInstitute: Long? = null
    var idField: Long? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FieldFileId

        if (idInstitute != other.idInstitute) return false
        if (idField != other.idField) return false

        return true
    }

    override fun hashCode(): Int {
        var result = idInstitute?.hashCode() ?: 0
        result = 31 * result + (idField?.hashCode() ?: 0)
        return result
    }

}
