package com.legal_diary_app.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person_status")
public class PersonStatus extends AbstractItem {

    @Column(name = "name")
    private String name="";

    @OneToMany(mappedBy = "personStatus")
    private List<Person> persons = new ArrayList<>();

    public PersonStatus() {
    }

    public PersonStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return name ;
    }
}
