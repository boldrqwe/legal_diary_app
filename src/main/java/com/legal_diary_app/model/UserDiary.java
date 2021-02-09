package com.legal_diary_app.model;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users_diaries")

public class UserDiary extends AbstractItem {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "userDiary")
    private List<Event> events = new ArrayList<>();

    @OneToMany(mappedBy = "userDiary")
    private List<Person> persons = new ArrayList<>();

public UserDiary(){

}

    public UserDiary(User user, List<Event> events, List<Person> persons) {
        this.user = user;
        this.events = events;
        this.persons = persons;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Override
    public String toString() {
        return "UserDiary{" +
                "user=" + user +
                ", events=" + events +
                ", persons=" + persons +
                '}';
    }
}
