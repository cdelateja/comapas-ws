package com.comapas.ws.model

import javax.persistence.*

@Entity
@Table
class Config: Auditable<String>() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idFormConfig: Long? = null
    var name: String? = null

    @Column(length = 500)
    var json: String? = null
}
