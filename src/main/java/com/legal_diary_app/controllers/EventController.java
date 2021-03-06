package com.legal_diary_app.controllers;

import com.legal_diary_app.data.EventData;
import com.legal_diary_app.data.PersonData;
import com.legal_diary_app.mappers.CommonMapper;
import com.legal_diary_app.model.Document;
import com.legal_diary_app.model.Event;
import com.legal_diary_app.model.LegalCase;
import com.legal_diary_app.model.Person;
import com.legal_diary_app.service.DocumentService;
import com.legal_diary_app.service.EventService;
import com.legal_diary_app.service.PersonService;
import com.legal_diary_app.service.PersonStatusService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/events")
public class EventController {

    private EventService eventService;
    private PersonService personService;
    private PersonStatusService personStatusService;
    private DocumentService documentService;


    public EventController(EventService eventService, PersonService personService,
                           PersonStatusService personStatusService,
                           DocumentService documentService) {
        this.eventService = eventService;
        this.personService = personService;
        this.personStatusService = personStatusService;
        this.documentService = documentService;
    }

    @GetMapping
    public String showEvents(Model model) {
        model.addAttribute("eventList", CommonMapper.INSTANCE.toEventDataList(eventService.findAll()));
        model.addAttribute("activePage", "Events");
        model.addAttribute("event", new EventData());
        return "events";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable Long id, Model model) {
        EventData eventData = CommonMapper.INSTANCE.toEventData(eventService.findById(id).get());
        model.addAttribute("event", eventData);
        model.addAttribute("persons", CommonMapper.INSTANCE.toPersonDataList(personService.findAllByEventId(id)));
        model.addAttribute("documents", documentService.findAllByEventId(id));
        model.addAttribute("person", new PersonData());
        model.addAttribute("statusList", CommonMapper.INSTANCE.toPersonStatusDataList(personStatusService.findAll()));
        return "event_show_form";
    }

    @GetMapping("/ended")
    public String showEndedEvents(Model model) {
        model.addAttribute("eventList", CommonMapper.INSTANCE.toEventDataList(eventService.findAllByEndStatus(true)));
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
        model.addAttribute("activePage", "Cases");
        Event event = CommonMapper.INSTANCE.toEvent(eventData);
        eventService.save(event);
        return "redirect:/events";
    }

    @PostMapping("/upload/{id}")
    public String handleFileUpload(@RequestParam("file") List<MultipartFile> files, @PathVariable Long id) {
        Event event = eventService.findById(id).get();
        if (!files.isEmpty()) {
            for (MultipartFile file : files) {
                String filePath = "files/" + UUID.randomUUID() + (file.getOriginalFilename()).substring(file
                        .getOriginalFilename().lastIndexOf("."));
                try (BufferedOutputStream stream =
                             new BufferedOutputStream(new FileOutputStream(new File(filePath)))) {
                    byte[] bytes = file.getBytes();
                    stream.write(bytes);
                    Document document = new Document(file.getOriginalFilename(), filePath);
                    document.getEvents().add(event);
                    event.getDocuments().add(document);
                    event.getLegalCase().getDocuments().add(document);
                    documentService.save(document);
                    eventService.save(event);
                } catch (Exception e) {
                    throw new RuntimeException("Вам не удалось загрузить " + file.getOriginalFilename() + " => " + e.getMessage());
                }
            }
        } else {
            return "redirect:/events/" + event.getId();
        }
        return "redirect:/events/" + event.getId();
    }
}
