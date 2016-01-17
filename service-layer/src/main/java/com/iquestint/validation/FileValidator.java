package com.iquestint.validation;

import com.iquestint.dao.DocumentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * This class is a validator for the @FileExists annotation.
 *
 * @author Georgian Vladutu
 */
public class FileValidator implements ConstraintValidator<FileExists, MultipartFile> {

    @Autowired
    private DocumentDao documentDao;

    @Override
    public void initialize(FileExists fileExists) {

    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {

        if (multipartFile == null || multipartFile.getSize() == 0) {
            return false;
        }

        if (documentDao.documentExists(multipartFile.getOriginalFilename(), multipartFile.getContentType())) {
            return false;
        }

        return true;
    }
}
