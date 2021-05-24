package com.comapas.ws.model

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import javax.persistence.*

@Entity
@Table
class Field: Auditable<String>() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idField: Long? = null
    var label: String? = null
    var name: String? = null
    var type: String? = null
    var catalog: String? = null
    var required: Boolean = false
    var evidence: Boolean = false
    var score: Int = 0

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
    }
}
