package com.legal_diary_app.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cases")
public class LegalCase extends AbstractItem {

    @Column(name = "name")
    private String name;

    @Column(name = "number")
    private String number;

    @Column(name = "category")
    private String category;

    @Column(name = "phase")
    private String phase;

    @OneToMany(mappedBy = "legalCase", cascade = CascadeType.MERGE)
    private List<Event> events = new ArrayList<>();


    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "persons_cases",
            joinColumns = {@JoinColumn(name = "person_id")},
            inverseJoinColumns = {@JoinColumn(name = "legalCase_id")})
    private List<Person> persons = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "docs_cases",
            joinColumns = {@JoinColumn(name = "doc_id")},
            inverseJoinColumns = {@JoinColumn(name = "legalCase_id")})
    List<Document> documents = new ArrayList<>();

    @ManyToMany(mappedBy = "legalCases", cascade = CascadeType.MERGE)
    private List<User> users = new ArrayList<>();


    public LegalCase() {

    }

    public LegalCase(String name, String number, List<Event> events, List<Person> persons) {
        this.name = name;
        this.number = number;
        this.events = events;
        this.persons = persons;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public LegalCase(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }
}
