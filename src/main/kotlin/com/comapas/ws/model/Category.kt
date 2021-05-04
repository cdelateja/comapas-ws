package com.comapas.ws.model

import javax.persistence.*

@Entity
@Table
class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idCategory: Long? = null

    var name: String? = null
    var code: String? = null
}
