package com.legal_diary_app.data;


import com.legal_diary_app.model.AbstractItem;
import com.legal_diary_app.model.Document;
import com.legal_diary_app.model.LegalCase;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DocumentData extends AbstractItem implements Serializable {


    private String name;


    private String filePath;

    List<Document> documents = new ArrayList<>();

    public DocumentData(String name, String filePath) {
        this.name = name;
        this.filePath = filePath;
    }

    public DocumentData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }
}
