package com.comapas.ws.model

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass


@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class Auditable<U> {

    @CreatedBy
    @Column(updatable = false)
    var createdBy: U? = null

    @CreatedDate
    @Column(updatable = false)
    var createdDate: LocalDateTime? = null

    @LastModifiedBy
    var lastModifiedBy: U? = null

    @LastModifiedDate
    var lastModifiedDate: LocalDateTime? = null
}
