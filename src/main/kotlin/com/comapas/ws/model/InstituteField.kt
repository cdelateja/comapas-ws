package com.comapas.ws.model

import javax.persistence.*

@IdClass(InstituteFieldId::class)
@Entity
@Table
class InstituteField: Auditable<String>() {

    @Id
    var idInstitute: Long? = null

    @Id
    var idField: Long? = null

    var value: String? = null

}
