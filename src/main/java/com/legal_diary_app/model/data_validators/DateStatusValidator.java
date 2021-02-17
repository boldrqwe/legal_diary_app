package com.legal_diary_app.model.data_validators;

import com.legal_diary_app.model.Event;
import com.legal_diary_app.service.EventService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class DateStatusValidator {

    EventService eventService;

    public DateStatusValidator(EventService eventService) {
        this.eventService = eventService;
    }

    @Scheduled(fixedRate = 5000)
    public void validateDateStatus(){
        List<Event> eventList = eventService.findAll();
       eventList = eventList.stream().filter(event -> event.getEndingDate().before(new Date())).collect(Collectors.toList());
        for (Event event:eventList) {
            event.setEndStatus(true);
        }
        eventService.saveAll(eventList);
    }
}
