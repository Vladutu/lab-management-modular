package com.iquestint.populator;

import com.iquestint.dto.FileBucket;
import com.iquestint.model.Document;
import com.iquestint.model.Laboratory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * This class is used to populate a Document entity.
 *
 * @author Georgian Vladutu
 */
@Component
public class DocumentPopulator {

    public Document populate(FileBucket fileBucket, int laboratoryId) throws IOException {
        Document document = new Document();
        Laboratory laboratory = new Laboratory();
        MultipartFile file = fileBucket.getFile();

        laboratory.setId(laboratoryId);
        document.setName(file.getOriginalFilename());
        document.setType(file.getContentType());
        document.setContent(file.getBytes());
        document.setLaboratory(laboratory);

        return document;
    }
}
