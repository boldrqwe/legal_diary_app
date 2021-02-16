package com.legal_diary_app.data;

import com.legal_diary_app.model.AbstractItem;
import com.legal_diary_app.model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonStatusData extends AbstractItem {


    private String name="";


    private List<Person> persons = new ArrayList<>();


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
}
