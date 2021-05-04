package com.comapas.ws.model

import javax.persistence.*

@Entity
@Table
class Criterion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idCriterion: Long? = null
    var name: String? = null
    var code: String? = null

    @OneToMany(mappedBy = "idCriterion", fetch = FetchType.LAZY)
    val fields: MutableList<CriterionField> = arrayListOf()
}
