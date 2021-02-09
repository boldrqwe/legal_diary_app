package com.legal_diary_app.controllers;

import com.legal_diary_app.repository.PhaseRep;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/phase")
public class PhaseController {

    private PhaseRep phaseRep;

    public PhaseController(PhaseRep phaseRep) {
        this.phaseRep = phaseRep;
    }

    @GetMapping
    public String showPersons(Model model){
        model.addAttribute("phases", phaseRep.findAll());
        return "persons";
    }
}
