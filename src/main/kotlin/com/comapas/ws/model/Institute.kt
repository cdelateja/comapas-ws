package com.comapas.ws.model

import javax.persistence.*


@Entity
@Table
class Institute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idInstitute: Long? = null
    var name: String? = null

}
