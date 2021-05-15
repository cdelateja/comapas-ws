package com.comapas.ws.model

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import javax.persistence.*


@IdClass(CriterionFieldId::class)
@Entity
@Table
class CriterionField {

    @Id
    var idCriterion: Long? = null

    @Id
    var idField: Long? = null

    @MapsId("idField")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idField", referencedColumnName = "idField", insertable = false, updatable = false)
    var field: Field? = null

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
    }
}
