package com.legal_diary_app.model;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="users")
public class User extends AbstractPersonality {


    @Column(name= "username")
    private String username;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "password")
    private String password;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles =new ArrayList<>();

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "users_persons",
    joinColumns = @JoinColumn(name = "users_id"),
    inverseJoinColumns = @JoinColumn(name = "persons_id"))
    private List<Person> persons = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "users_events",
            joinColumns = @JoinColumn(name = "users_id"),
    inverseJoinColumns = @JoinColumn(name = "events_id"))
    private List<Event> events = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "users_cases",
    joinColumns = @JoinColumn(name = "usesr_id"),
    inverseJoinColumns = @JoinColumn(name = "cases_id"))
    private List<LegalCase> legalCases = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "users_documents",
            joinColumns = @JoinColumn(name = "usesr_id"),
            inverseJoinColumns = @JoinColumn(name = "document_id"))
    private List<Document> documents = new ArrayList<>();




    public User (){

    }

    public User(boolean enabled, String password, Collection<Role> roles) {
        this.enabled = enabled;
        this.password = password;
        this.roles = roles;

    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
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

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", enabled=" + enabled +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
