package com.iquestint.service.impl;

import com.iquestint.dao.DocumentDao;
import com.iquestint.dto.DocumentDto;
import com.iquestint.dto.DocumentWithContentDto;
import com.iquestint.dto.FileBucket;
import com.iquestint.exception.*;
import com.iquestint.model.Document;
import com.iquestint.populator.DocumentPopulator;
import com.iquestint.service.DocumentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

/**
 * @author vladu
 */
@Service("documentService")
@Transactional
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DocumentDao documentDao;

    @Autowired
    private DocumentPopulator documentPopulator;

    @Override
    public List<DocumentDto> getDocumentsByLaboratory(int laboratoryId) {
        List<Document> documents = documentDao.getDocumentsByLaboratory(laboratoryId);

        return modelMapper.map(documents, new TypeToken<List<DocumentDto>>() {
        }.getType());
    }

    @Override
    public void saveDocument(FileBucket fileBucket, int laboratoryId)
        throws ServiceEntityAlreadyExistsException, ServiceIOException {
        Document document = null;
        try {
            document = documentPopulator.populate(fileBucket, laboratoryId);
        } catch (IOException e) {
            throw new ServiceIOException(e);
        }
        try {
            documentDao.saveDocument(document);
        } catch (DaoEntityAlreadyExistsException e) {
            throw new ServiceEntityAlreadyExistsException(e);
        }
    }

    @Override
    public void deleteDocument(int documentId) throws ServiceEntityNotFoundException {
        try {
            documentDao.deleteDocument(documentId);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public DocumentWithContentDto getDocument(int documentId) throws ServiceEntityNotFoundException {
        try {
            Document document = documentDao.getDocumentById(documentId);

            return modelMapper.map(document, DocumentWithContentDto.class);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

}
