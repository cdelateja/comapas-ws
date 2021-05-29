package com.comapas.ws.repository

import com.comapas.ws.dto.response.FileFieldRes
import com.comapas.ws.model.FieldFile
import com.comapas.ws.model.FieldFileId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface FieldFileRepository : JpaRepository<FieldFile, FieldFileId> {

    @Query(
        "select new com.comapas.ws.dto.response.FileFieldRes" +
                "(f.idInstitute, f.idField, f.filename, f.contentType, f.mbSize) " +
                "from FieldFile f where f.idInstitute = :idInstitute"
    )
    fun findByIdInstitute(@Param("idInstitute") idInstitute: Long): List<FileFieldRes>

    @Query(
        "select new com.comapas.ws.dto.response.FileFieldRes" +
                "(f.idInstitute, f.idField, f.filename, f.contentType, f.mbSize) " +
                "from FieldFile f where f.idInstitute = :idInstitute " +
                "and f.idField = :idField"
    )
    fun findByIdInstituteAAndIdField(
        @Param("idInstitute") idInstitute: Long,
        @Param("idField") idField: Long
    ): FileFieldRes
}
