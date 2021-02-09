package com.legal_diary_app.controllers;



import com.legal_diary_app.service.PersonService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/persons")
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }



    @GetMapping
    public String showPersons(Model model){
        model.addAttribute("persons", personService.findAll());
        return "persons";
    }
}
