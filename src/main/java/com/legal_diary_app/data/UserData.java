package com.legal_diary_app.data;

import com.legal_diary_app.model.Document;
import com.legal_diary_app.model.Event;
import com.legal_diary_app.model.LegalCase;
import com.legal_diary_app.model.Person;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class UserData {

    @NotNull
    private String name;
    @NotNull
    private String username;
    @NotNull
    private String password;
    private String email;
    private String phone;


    private List<Person> persons = new ArrayList<>();

    private List<Event> events = new ArrayList<>();


    private List<LegalCase> legalCases = new ArrayList<>();

    private List<Document> documents = new ArrayList<>();


    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<LegalCase> getLegalCases() {
        return legalCases;
    }

    public void setLegalCases(List<LegalCase> legalCases) {
        this.legalCases = legalCases;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
