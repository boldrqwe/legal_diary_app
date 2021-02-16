package com.legal_diary_app.controllers;

import com.legal_diary_app.data.EventData;
import com.legal_diary_app.data.PersonData;
import com.legal_diary_app.mappers.CommonMapper;
import com.legal_diary_app.model.Event;
import com.legal_diary_app.model.Person;
import com.legal_diary_app.service.EventService;
import com.legal_diary_app.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    private EventService eventService;
    private PersonService personService;


    public EventController(EventService eventService, PersonService personService) {
        this.eventService = eventService;
        this.personService = personService;
    }

    @GetMapping
    public String showEvents(Model model) {
        List<Event> events = eventService.findAll();
        model.addAttribute("events", events);
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
        return "event_show_form";
    }

    @PostMapping("/add_person")
    public String addPerson(Model model, PersonData personData) {
        Person person = CommonMapper.INSTANCE.toPerson(personData);
        Event event = eventService.findById(person.getEvents().get(0).getId()).get();
        event.getPersons().add(person);
        eventService.add(event);
        personService.add(person);
return "redirect:/"+event.getId();
    }
}
