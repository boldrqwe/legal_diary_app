package com.legal_diary_app.controllers;

import com.legal_diary_app.data.EventData;
import com.legal_diary_app.data.PersonData;
import com.legal_diary_app.mappers.CommonMapper;
import com.legal_diary_app.model.Event;
import com.legal_diary_app.model.Person;
import com.legal_diary_app.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/events")
public class EventController extends CommonController {


    public EventController(CaseService caseService, EventService eventService, PersonService personService, CategoryService categoryService, PhaseService phaseService, PersonStatusService personStatusService, DocumentService documentService, ServletContext servletContext, UserService userService) {
        super(caseService, eventService, personService, categoryService, phaseService, personStatusService, documentService, servletContext, userService);
    }

    @GetMapping
    public String showEvents(Model model) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        model.addAttribute("eventList", CommonMapper.INSTANCE.toEventDataList(
                eventService.findAllByUserName(authentication.getName())));
        model.addAttribute("activePage", "Events");
        model.addAttribute("event", new EventData());
        return "events";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable Long id, Model model) {
        EventData eventData = CommonMapper.INSTANCE.toEventData(eventService.findById(id).get());
        model.addAttribute("event", eventData);
        model.addAttribute("persons", CommonMapper.INSTANCE.toPersonDataList(
                personService.findAllByEventId(id)));
        model.addAttribute("documents", documentService.findAllByEventId(id));
        model.addAttribute("person", new PersonData());
        model.addAttribute("statusList", CommonMapper.INSTANCE.toPersonStatusDataList(
                personStatusService.findAll()));
        return "event_show_form";
    }

    @GetMapping("/ended")
    public String showEndedEvents(Model model) {
        model.addAttribute("eventList", CommonMapper.INSTANCE.toEventDataList(
                eventService.findAllByEndStatusAndName(true, getAuthName())));
        return "events_ended";
    }

    @PostMapping("/add_person")
    public String addPerson(PersonData personData) {
        Person person = CommonMapper.INSTANCE.toPerson(personData);
        Event event = eventService.findById(person.getEvents().get(0).getId()).get();
        event.getPersons().add(person);
        eventService.save(event);
        personService.save(person);
        return "redirect:/events/" + event.getId();
    }

    @PostMapping("/add_event")
    public String addEvent(Model model, @Valid @ModelAttribute EventData eventData, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/events";
        }
        model.addAttribute("activePage", "Events");
        eventService.saveEventAndUser(CommonMapper.INSTANCE.toEvent(eventData));
        return "redirect:/events";
    }

    @PostMapping("/upload/{id}")
    public String handleFileUpload(@RequestParam("file") List<MultipartFile> files, @PathVariable Long id) {
        Event event = eventService.findById(id).get();
        if (!files.isEmpty()) {
            documentService.saveDocAndEventAndUser(files, event);
        } else {
            return "redirect:/events/" + event.getId();
        }
        return "redirect:/events/" + event.getId();
    }
}
