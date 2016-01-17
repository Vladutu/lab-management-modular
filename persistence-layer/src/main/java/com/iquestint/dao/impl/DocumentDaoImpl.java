package com.iquestint.dao.impl;

import com.iquestint.dao.DocumentDao;
import com.iquestint.exception.DaoEntityAlreadyExistsException;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * This class implements DocumentDao interface;
 *
 * @author Georgian Vladutu
 */
@Repository("documentDao")
public class DocumentDaoImpl extends JpaDao<Document> implements DocumentDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentDaoImpl.class);

    @Override
    public Document getDocumentById(int id) throws DaoEntityNotFoundException {
        TypedQuery<Document> query = getEntityManager().createQuery("SELECT d FROM Document d WHERE d.id = :id",
            Document.class);
        query.setParameter("id", id);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            LOGGER.debug("NoResultException");
            throw new DaoEntityNotFoundException();
        }
    }

    @Override
    public boolean documentExists(String name, String type) {
        TypedQuery<Document> query = getEntityManager().createQuery(
            "SELECT d FROM Document d WHERE d.name = :name AND d.type=:type",
            Document.class);
        query.setParameter("name", name);
        query.setParameter("type", type);

        try {
            Document document = query.getSingleResult();

            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    public List<Document> getDocumentsByLaboratory(int laboratoryId) {
        TypedQuery<Document> query = getEntityManager().createQuery(
            "SELECT d FROM Document d WHERE d.laboratory.id = :id", Document.class);
        query.setParameter("id", laboratoryId);

        return query.getResultList();
    }

    @Override
    public void saveDocument(Document document) throws DaoEntityAlreadyExistsException {
        try {
            Document d = getDocumentById(document.getId());
        } catch (DaoEntityNotFoundException e) {
            persist(document);
            return;
        }

        throw new DaoEntityAlreadyExistsException();
    }

    @Override
    public void deleteDocument(int documentId) throws DaoEntityNotFoundException {
        Document document = getDocumentById(documentId);
        delete(document);
    }

    @Override
    public void deleteDocumentsByLaboratory(int laboratoryId) {
        Query query = getEntityManager().createQuery("DELETE  FROM Document d WHERE d.laboratory.id = :id");
        query.setParameter("id", laboratoryId);

        query.executeUpdate();
    }
}
