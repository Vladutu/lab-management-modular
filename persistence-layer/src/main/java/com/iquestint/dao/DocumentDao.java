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

    Document getDocumentById(int id) throws DaoEntityNotFoundException;

    boolean documentExists(String name, String type);

    List<Document> getDocumentsByLaboratory(int laboratoryId);

    void saveDocument(Document document) throws DaoEntityAlreadyExistsException;

    void deleteDocument(int documentId) throws DaoEntityNotFoundException;

    void deleteDocumentsByLaboratory(int laboratoryId);
}
