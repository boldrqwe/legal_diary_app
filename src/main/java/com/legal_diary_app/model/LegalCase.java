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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "phase_id")
    private Phase phase;

    @OneToMany(mappedBy = "legalCase", cascade = CascadeType.MERGE)
    List<Event> events = new ArrayList<>();



    @ManyToMany
    @JoinTable(name = "cases_persons",
            joinColumns = {@JoinColumn(name = "person_id")},
            inverseJoinColumns = {@JoinColumn(name = "legalCase_id")})
    List<Person> persons = new ArrayList<>();


    public LegalCase() {

    }

    public LegalCase(String name, String number, Category category, Phase phase, List<Event> events, List<Person> persons) {
        this.name = name;
        this.number = number;
        this.category = category;
        this.phase = phase;
        this.events = events;
        this.persons = persons;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
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
}
