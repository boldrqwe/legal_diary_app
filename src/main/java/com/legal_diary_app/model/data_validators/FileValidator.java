package com.legal_diary_app.model.data_validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileValidator implements Validator {

    List<MultipartFile> files = new ArrayList<>();

    List<String> types = Arrays.asList(
            "image/jpeg",
            "application/rtf",
            "application/msword",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
            "image/png",
            "application/pdf",
            "application/zip");

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        files.clear();
        List<MultipartFile> fileList = (List<MultipartFile>) target;
        for (MultipartFile file : fileList) {
            if (types.contains(file.getContentType())) {
                files.add(file);
            } else {
                errors.rejectValue("file", "type" + file.getContentType() + "dont support");
            }
        }
    }
}
