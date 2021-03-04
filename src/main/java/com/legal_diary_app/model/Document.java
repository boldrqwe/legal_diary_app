package com.legal_diary_app.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "documents")
public class Document extends AbstractItem {

    @Column(name = "name")
    private String name;

    @Column(name = "file_path")
    private String filePath;

    @ManyToMany(mappedBy = "documents", cascade = CascadeType.PERSIST)
    List<LegalCase> legalCases = new ArrayList<>();

    @ManyToMany(mappedBy = "documents", cascade = CascadeType.MERGE)
    List<Event> events = new ArrayList<>();

    public Document(String name, String filePath) {
        this.name = name;
        this.filePath = filePath;
    }

    public Document() {
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

    public List<LegalCase> getLegalCases() {
        return legalCases;
    }

    public void setLegalCases(List<LegalCase> legalCases) {
        this.legalCases = legalCases;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
