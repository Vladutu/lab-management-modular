package com.iquestint.service;

import com.iquestint.dto.DocumentDto;
import com.iquestint.dto.DocumentWithContentDto;
import com.iquestint.dto.FileBucket;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.exception.ServiceIOException;

import java.util.List;

/**
 * This interfaces provides methods for documents management.
 *
 * @author Georgian Vladutu
 */
public interface DocumentService {

    /**
     * Returns all documents which belong to the laboratory whose id is laboratoryId from the repository.
     *
     * @param laboratoryId id of the laboratory
     * @return list of documents
     */
    List<DocumentDto> getDocumentsByLaboratory(int laboratoryId);

    /**
     * Saves the document wrapped as a FileBucket which belong to the laboratory whose id is laboratoryId into the repository.
     *
     * @param fileBucket   object which wrap the document to be saved
     * @param laboratoryId id of the laboratory
     * @throws ServiceEntityAlreadyExistsException if the entity already exists
     * @throws ServiceIOException                  in case of access errors
     */
    void saveDocument(FileBucket fileBucket, int laboratoryId)
        throws ServiceEntityAlreadyExistsException, ServiceIOException;

    /**
     * Deletes the document whose id is documentId.
     *
     * @param documentId id of the document
     * @throws ServiceEntityNotFoundException if the document is not found
     */
    void deleteDocument(int documentId) throws ServiceEntityNotFoundException;

    /**
     * Returns the document with the content whose id is documentId.
     *
     * @param documentId id of the document
     * @return DocumentWithContentDto
     * @throws ServiceEntityNotFoundException if the document is not found
     */
    DocumentWithContentDto getDocument(int documentId) throws ServiceEntityNotFoundException;
}
