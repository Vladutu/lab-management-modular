package com.iquestint.service;

import com.iquestint.dto.DocumentDto;
import com.iquestint.dto.DocumentWithContentDto;
import com.iquestint.dto.FileBucket;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.exception.ServiceIOException;

import java.util.List;

/**
 * @author vladu
 */
public interface DocumentService {

    List<DocumentDto> getDocumentsByLaboratory(int laboratoryId);

    void saveDocument(FileBucket fileBucket, int laboratoryId)
        throws ServiceEntityAlreadyExistsException, ServiceIOException;

    void deleteDocument(int documentId) throws ServiceEntityNotFoundException;

    DocumentWithContentDto getDocument(int documentId) throws ServiceEntityNotFoundException;
}
