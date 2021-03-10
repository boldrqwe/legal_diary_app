package com.legal_diary_app.data;


import com.legal_diary_app.model.AbstractItem;
import com.legal_diary_app.model.Document;
import com.legal_diary_app.model.LegalCase;
import com.legal_diary_app.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DocumentData extends AbstractItem implements Serializable {


    private String name;


    private String filePath;

    List<Document> documents = new ArrayList<>();


    private List<User> users = new ArrayList<>();

    public DocumentData(String name, String filePath) {
        this.name = name;
        this.filePath = filePath;
    }

    public DocumentData() {
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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
