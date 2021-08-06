package com.comapas.ws.model

import javax.persistence.*

@Entity
@Table
class Category: Auditable<String>() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idCategory: Long? = null

    var name: String? = null

    var position: Int? = null

    @OneToMany(mappedBy = "idCategory", fetch = FetchType.LAZY)
    var criterionList: MutableList<Criterion> = arrayListOf()
}
