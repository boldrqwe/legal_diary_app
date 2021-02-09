package com.legal_diary_app.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "events")

public class Event extends AbstractItem {
    @Column(name = "name")
    private String name;

    @Column(name = "beginning_date")
    private Date beginningDate;

    @Column(name = "ending_date")
    private Date endingDate;

    @ManyToMany(mappedBy = "events")
    private List<Person> persons = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_diary_id")
    private UserDiary userDiary;

    @ManyToOne
    @JoinColumn(name = "case_id")
    private LegalCase legalCase;


    public Event() {

    }

    public Event(Date beginningDate, Date endingDate, List<Person> persons, UserDiary userDiary,
                 String name) {
        this.name = name;
        this.beginningDate = beginningDate;
        this.endingDate = endingDate;
        this.persons = persons;
        this.userDiary = userDiary;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public Date getBeginningDate() {
        return beginningDate;
    }

    public void setBeginningDate(Date beginningDate) {
        this.beginningDate = beginningDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public UserDiary getUserDiary() {
        return userDiary;
    }

    public void setUserDiary(UserDiary userDiary) {
        this.userDiary = userDiary;
    }

    public LegalCase getLegalCase() {
        return legalCase;
    }

    public void setLegalCase(LegalCase legalCase) {
        this.legalCase = legalCase;
    }

    @Override
    public String toString() {
        return "Event{" +
                "beginningDate=" + beginningDate +
                ", endingDate=" + endingDate +
                ", persons=" + persons +
                ", userDiary=" + userDiary +
                '}';
    }
}
