package com.legal_diary_app.controllers;

import com.legal_diary_app.data.EventData;
import com.legal_diary_app.data.PersonData;
import com.legal_diary_app.mappers.CommonMapper;
import com.legal_diary_app.model.Event;
import com.legal_diary_app.model.Person;
import com.legal_diary_app.service.EventService;
import com.legal_diary_app.service.PersonService;
import com.legal_diary_app.service.PersonStatusService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.jvm.hotspot.utilities.Assert;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

@Controller
@RequestMapping("/events")
public class EventController {

    private EventService eventService;
    private PersonService personService;
    private PersonStatusService personStatusService;


    public EventController(EventService eventService, PersonService personService,
                           PersonStatusService personStatusService) {
        this.eventService = eventService;
        this.personService = personService;
        this.personStatusService = personStatusService;
    }

    @GetMapping
    public String showEvents(Model model) {
        model.addAttribute("eventList", CommonMapper.INSTANCE.toEventDataList(eventService.findAll()));
        model.addAttribute("activePage", "Events");
        return "events";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable Long id, Model model) {
        EventData eventData = CommonMapper.INSTANCE.toEventData(eventService.findById(id).get());
        model.addAttribute("event", eventData);
        model.addAttribute("persons", CommonMapper.INSTANCE.toPersonDataList(personService.findAllByEventId(id)));
        model.addAttribute("documents", Arrays.asList("документ1", "документ2", "документ3", "документ4", "документ5"));
        model.addAttribute("person", new PersonData());
        model.addAttribute("statusList", CommonMapper.INSTANCE.toPersonStatusDataList(personStatusService.findAll()));
        return "event_show_form";
    }

    @GetMapping("/ended")
    public String showEndedEvents(Model model){
        model.addAttribute("eventList", CommonMapper.INSTANCE.toEventDataList(eventService.findAllByEndStatus(true)));

        return "events_ended";
    }

    @PostMapping("/add_person")
    public String addPerson(Model model, PersonData personData) {
        Person person = CommonMapper.INSTANCE.toPerson(personData);
        Event event = eventService.findById(person.getEvents().get(0).getId()).get();
        event.getPersons().add(person);
        eventService.add(event);
        personService.add(person);
return "redirect:/events/"+event.getId();
    }
}
