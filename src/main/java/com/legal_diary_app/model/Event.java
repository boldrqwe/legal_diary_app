package com.legal_diary_app.model;

import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH")
    private Date beginningDate;

    @Column(name = "ending_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH")
    private Date endingDate;

    @Column(name = "remider_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH")
    private Date reminderDate;

    @Column(name = "end_status")
    private Boolean endStatus = false;

    @Column(name = "complete_status")
    private Boolean complete = false;

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

    public Date getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(Date reminderDate) {
        this.reminderDate = reminderDate;
    }


    public Boolean getEndStatus() {
        return endStatus;
    }

    public void setEndStatus(Boolean endStatus) {
        this.endStatus = endStatus;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
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


}
