package com.legal_diary_app.controllers;

import com.legal_diary_app.model.Event;
import com.legal_diary_app.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public String showEvents(Model model) {
        List<Event> events = eventService.findAll();
        model.addAttribute("events", events);
        model.addAttribute("activePage", "Events");
        return "events";
    }


}
