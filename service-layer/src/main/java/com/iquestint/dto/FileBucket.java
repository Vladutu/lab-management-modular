package com.iquestint.dto;

import com.iquestint.validation.FileExists;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * This class represents a data transfer object.
 *
 * @author Georgian Vladutu
 */
@Getter
@Setter
public class FileBucket {

    @FileExists
    MultipartFile file;

    String description;
}
