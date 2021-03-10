package com.legal_diary_app.data;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class FileBucket {
    MultipartFile file;


    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

}
