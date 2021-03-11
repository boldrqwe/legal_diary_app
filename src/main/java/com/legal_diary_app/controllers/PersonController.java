package com.legal_diary_app.controllers;



import com.legal_diary_app.data.PersonData;
import com.legal_diary_app.mappers.CommonMapper;
import com.legal_diary_app.model.LegalCase;
import com.legal_diary_app.model.Person;
import com.legal_diary_app.service.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;

@Controller
@RequestMapping("/persons")
public class PersonController  extends CommonController{


    public PersonController(CaseService caseService, EventService eventService, PersonService personService,
                            DocumentService documentService, ServletContext servletContext, UserService userService) {
        super(caseService, eventService, personService, documentService, servletContext, userService);
    }

    @GetMapping
    public String showPersons(Model model){
        model.addAttribute("persons", personService.findAllByUserName(getAuthName()));
        model.addAttribute("person", new PersonData());
        return "persons";
    }

    @GetMapping("/{id}/edit")
    public String showPerson(@PathVariable Long id, Model model){
        model.addAttribute("person", CommonMapper.INSTANCE.toPersonData(personService.findById(id).get()));
        model.addAttribute("case_list", CommonMapper.INSTANCE.toCaseDataList(caseService.findAllByUserName(getAuthName())));
        return "person_form";
    }

    @PostMapping("/add_person")
    public String addPerson(Model model, PersonData personData) {
        personService.savePersonAndUser(CommonMapper.INSTANCE.toPerson(personData));
        model.addAttribute("activePage", "Cases");
        return "redirect:/persons";
    }
}
