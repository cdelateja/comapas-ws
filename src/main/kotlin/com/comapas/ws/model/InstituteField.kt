package com.comapas.ws.model

import javax.persistence.*

@IdClass(InstituteFieldId::class)
@Entity
@Table
class InstituteField {

    @Id
    var idInstitute: Long? = null

    @Id
    var idCriterion: Long? = null

    @Id
    var idField: Long? = null

    var value: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns(
        value = [
            JoinColumn(
                name = "idCriterion", referencedColumnName = "idCriterion", insertable = false, updatable = false
            ),
            JoinColumn(name = "idField", referencedColumnName = "idField", insertable = false, updatable = false)
        ]
    )
    private val criterionField: CriterionField? = null
}
