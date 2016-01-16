package com.iquestint.dto;

import com.iquestint.validation.FileExists;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author vladu
 */
@Getter
@Setter
public class FileBucket {

    @FileExists
    MultipartFile file;

    String description;
}
