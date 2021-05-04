package com.comapas.ws.model

import java.io.Serializable
import java.util.*

class InstituteFieldId : Serializable {

    private val idInstitute: Long? = null
    private val idCriterion: Long? = null
    private val idField: Long? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as InstituteFieldId

        if (idInstitute != other.idInstitute) return false
        if (idCriterion != other.idCriterion) return false
        if (idField != other.idField) return false

        return true
    }

    override fun hashCode(): Int {
        var result = idInstitute?.hashCode() ?: 0
        result = 31 * result + (idCriterion?.hashCode() ?: 0)
        result = 31 * result + (idField?.hashCode() ?: 0)
        return result
    }

}
