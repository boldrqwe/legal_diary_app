package com.legal_diary_app.service;

import com.legal_diary_app.model.Event;
import com.legal_diary_app.repository.EventRep;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService extends AbstractService<Event, EventRep> {


    public EventService(EventRep eventRep) {
        super(eventRep);
    }

    public List<Event> findAllByLegalCaseId(Long id) {
        return super.repository.findAllByLegalCaseId(id);
    }


    public Event add(Event event) {
        return super.repository.save(event);
    }


}
