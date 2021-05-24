package com.comapas.ws.model

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import javax.persistence.*

@Entity
@Table
class Criterion: Auditable<String>() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idCriterion: Long? = null
    var name: String? = null
    var code: String? = null

    @OneToMany(mappedBy = "idCriterion", fetch = FetchType.EAGER)
    var fields: MutableList<CriterionField> = arrayListOf()

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
    }
}
