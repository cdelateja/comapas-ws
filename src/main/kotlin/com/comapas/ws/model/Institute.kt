package com.comapas.ws.model

import javax.persistence.*


@Entity
@Table
class Institute: Auditable<String>() {

    @Id
    var idInstitute: Long? = null

    var score: Int = 0

    @OneToMany(mappedBy = "idInstitute", fetch = FetchType.EAGER)
    var fields: MutableList<InstituteField> = arrayListOf()

}
