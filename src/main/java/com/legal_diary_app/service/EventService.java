package com.legal_diary_app.service;

import com.legal_diary_app.model.Event;
import com.legal_diary_app.model.User;
import com.legal_diary_app.repository.EventRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService extends AbstractService<Event, EventRep> {
    @Autowired
    UserService userService;
    @Autowired
    PersonService personService;
    @Autowired
    CaseService caseService;

    public EventService(EventRep eventRep) {
        super(eventRep);
    }

    public List<Event> findAllByLegalCaseId(Long id) {
        return super.repository.findAllByLegalCaseId(id);
    }

    public List<Event> findAllByEndStatusAndName(boolean isEnd, String name) {
        return super.repository.findAllByEndStatusAndUserName(isEnd, name);
    }

    public List<Event> findAllByEndStatus(boolean isEnd) {
        return super.repository.findAllByEndStatus(isEnd);
    }

    public Long countEndedEvents() {
        return super.repository.findAllByEndStatusAndNameAndCount(true, getAuthName());
    }

    public void saveEventAndUser(Event event) {
        User user = userService.findByUsername(getAuthName());
        event.getUsers().add(user);
        user.getEvents().add(event);
        super.repository.save(event);
        userService.save(user);
    }


    public List<Event> findAllByUserName(String name) {
        return super.repository.findAllByUserName(name);
    }


}
