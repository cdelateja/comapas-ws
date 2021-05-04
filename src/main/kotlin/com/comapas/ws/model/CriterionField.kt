package com.comapas.ws.model

import javax.persistence.*


@IdClass(CriterionFieldId::class)
@Entity
@Table
class CriterionField {

    @Id
    var idCriterion: Long? = null

    @Id
    var idField: Long? = null

    var value: String? = null
    var evidence: Boolean = false
    var score: Int = 0

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idField", referencedColumnName = "idField", insertable = false, updatable = false)
    private val field: Field? = null
}
