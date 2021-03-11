package com.legal_diary_app.service;

import com.legal_diary_app.model.Person;
import com.legal_diary_app.model.User;
import com.legal_diary_app.repository.PersonRep;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService extends AbstractService<Person, PersonRep> {


    public PersonService(PersonRep personRep) {
        super(personRep);
    }

    public List<Person> findAllByLegalCaseId(Long id) {
        return super.repository.findAllByLegalCaseId(id);
    }

    public List<Person> findAllByEventId(long id) {
        return super.repository.findAllByEventId(id);
    }

    public List<Person> findAllByUserName(String name) {
        return super.repository.findAllByUserName(name);
    }

    public void savePersonAndUser(Person person){
        User user = userService.getCurrentUser();
        user.getPersons().add(person);
        person.getUsers().add(user);
        save(person);
        userService.save(user);
    }

}
