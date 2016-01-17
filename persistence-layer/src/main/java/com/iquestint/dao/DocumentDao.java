package com.iquestint.dao;

import com.iquestint.exception.DaoEntityAlreadyExistsException;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Document;

import java.util.List;

/**
 * This interfaces provides methods for working with Document entity explicitly (and Document database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface DocumentDao {

    /**
     * Returns the Document entity whose id is the same as the method parameter from the database.
     *
     * @param id id of the document
     * @return Document
     * @throws DaoEntityNotFoundException if the entity is not found in the database
     */
    Document getDocumentById(int id) throws DaoEntityNotFoundException;

    /**
     * Returns true if the document with the name and type as the method parameter exists in the database, false otherwise.
     *
     * @param name name of the document
     * @param type type of the document
     * @return true if the document exists, false otherwise
     */
    boolean documentExists(String name, String type);

    /**
     * Returns all Document entities that belong to the laboratory whose id is laboratoryId.
     *
     * @param laboratoryId id of the laboratory
     * @return list of documents
     */
    List<Document> getDocumentsByLaboratory(int laboratoryId);

    /**
     * Saves the document into the database.
     *
     * @param document document to be saved
     * @throws DaoEntityAlreadyExistsException if the document already exists in the database
     */
    void saveDocument(Document document) throws DaoEntityAlreadyExistsException;

    /**
     * Deletes the document whose id is documentId from the database.
     *
     * @param documentId id of the document
     * @throws DaoEntityNotFoundException if the document is not found in the database
     */
    void deleteDocument(int documentId) throws DaoEntityNotFoundException;

    /**
     * Deletes all documents which belong to the laboratory whose id is laboratoryId.
     *
     * @param laboratoryId id of the laboratory
     */
    void deleteDocumentsByLaboratory(int laboratoryId);
}
