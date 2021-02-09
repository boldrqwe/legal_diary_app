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

   @OneToMany
   @JoinTable(
           name = "user_diary",
   joinColumns = @JoinColumn(name = "user_id"),
   inverseJoinColumns = @JoinColumn(name = "user_diary_id"))

   private List<UserDiary> userDiaries = new ArrayList<>();


    public User (){

    }

    public User(boolean enabled, String password, Collection<Role> roles,
                List<UserDiary> userDiaries) {
        this.enabled = enabled;
        this.password = password;
        this.roles = roles;
        this.userDiaries = userDiaries;
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

    public List<UserDiary> getUserDiaries() {
        return userDiaries;
    }

    public void setUserDiaries(List<UserDiary> userDiaries) {
        this.userDiaries = userDiaries;
    }

    @Override
    public String toString() {
        return "User{" +
                "enabled=" + enabled +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", userDiaries=" + userDiaries +
                '}';
    }
}
