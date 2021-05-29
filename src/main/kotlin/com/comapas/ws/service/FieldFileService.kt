package com.comapas.ws.service

import com.comapas.ws.dto.response.FileFieldRes
import com.comapas.ws.model.FieldFile
import com.comapas.ws.model.FieldFileId
import com.comapas.ws.repository.FieldFileRepository
import com.comapas.ws.util.exception.ServiceException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import javax.transaction.Transactional

@Service
class FieldFileService(
    private val fieldFileRepository: FieldFileRepository
) {

    @Throws(ServiceException::class)
    fun uploadFile(file: MultipartFile, idField: Long, idInstitute: Long): FileFieldRes {
        var fieldFile = FieldFile()
        fieldFile.idInstitute = idInstitute
        fieldFile.idField = idField
        fieldFile.filename = file.originalFilename
        fieldFile.content = file.bytes
        fieldFile.contentType = file.contentType
        fieldFile.mbSize = mbSize(file.size)
        fieldFile = fieldFileRepository.save(fieldFile)
        return fieldFileRepository.findByIdInstituteAAndIdField(fieldFile.idInstitute!!, fieldFile.idField!!)
    }

    fun findByIdInstitute(idInstitute: Long): List<FileFieldRes> {
        return fieldFileRepository.findByIdInstitute(idInstitute)
    }

    @Transactional
    fun getContent(idInstitute: Long, idField: Long): ByteArray? {
        val fieldFileId = FieldFileId()
        fieldFileId.idInstitute = idInstitute
        fieldFileId.idField = idField
        return findById(fieldFileId).content
    }

    @Transactional
    fun findById(id: FieldFileId): FieldFile {
        return fieldFileRepository.findById(id)
            .orElseThrow { ServiceException(HttpStatus.NOT_FOUND.value(), "FieldFile not found by id: $id") }
    }

    private fun mbSize(sizeInBytes: Long): Long {
        return sizeInBytes / (1024 * 1024);

    }
}
