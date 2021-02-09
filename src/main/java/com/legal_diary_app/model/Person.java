package com.legal_diary_app.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "persons")

public class Person extends AbstractPersonality {

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "patronymic")
    private String patronymic;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "status_id")
    private PersonStatus personStatus;

    @Column(name = "birthday")
    private Date birthday;


    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "persons_events",
            joinColumns = {@JoinColumn(name = "person_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_id")}
    )
    private List<Event> events = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_diary_id")
    private UserDiary userDiary;

    @ManyToMany(mappedBy = "persons", cascade = CascadeType.PERSIST)
    private List<LegalCase> cases = new ArrayList<>();


    public Person() {

    }

    public Person(String surname, String name, String patronymic) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public UserDiary getUserDiary() {
        return userDiary;
    }

    public void setUserDiary(UserDiary userDiary) {
        this.userDiary = userDiary;
    }

    public PersonStatus getPersonStatus() {
        return personStatus;
    }

    public void setPersonStatus(PersonStatus personStatus) {
        this.personStatus = personStatus;
    }

    public List<LegalCase> getCases() {
        return cases;
    }

    public void setCases(List<LegalCase> cases) {
        this.cases = cases;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
