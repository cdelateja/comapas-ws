package com.comapas.ws.model

import javax.persistence.*

@IdClass(FieldFileId::class)
@Entity
@Table
class FieldFile : Auditable<String>() {

    @Id
    var idInstitute: Long? = null

    @Id
    var idField: Long? = null

    @Lob
    var content: ByteArray? = null

    var filename: String? = null

    var contentType: String? = null

    var mbSize: Long? = null
}
