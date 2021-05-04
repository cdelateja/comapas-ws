package com.comapas.ws.model

import javax.persistence.*

@Entity
@Table
class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idField: Long? = null
    var name: String? = null
    var label: String? = null
    var type: String? = null
    var value: String? = null
    var caption: String? = null
    var catalog: String? = null
}
