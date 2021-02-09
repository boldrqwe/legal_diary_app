package com.legal_diary_app.controllers;

import com.legal_diary_app.model.LegalCase;
import com.legal_diary_app.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;

@Controller
@RequestMapping("/legal_cases")
public class CaseController {
    private static final Logger logger = LoggerFactory.getLogger(CaseController.class);
    private CaseService caseService;
    private EventService eventService;
    private PersonService personService;
    private CategoryService categoryService;
    private PhaseService phaseService;

    public CaseController(CaseService caseService, EventService eventService, PersonService personService,
                          CategoryService categoryService, PhaseService phaseService) {
        this.caseService = caseService;
        this.eventService = eventService;
        this.personService = personService;
        this.categoryService = categoryService;
        this.phaseService = phaseService;
    }

    @GetMapping
    public String showCases(Model model) {
        model.addAttribute("legal_cases", caseService.findAll());
        model.addAttribute("activePage", "Cases");
        return "legal_cases";
    }

    @GetMapping("/add")
    public String addCase(Model model) {
        model.addAttribute("activePage", "Cases");
        model.addAttribute("add", true);
        LegalCase legalCase = new LegalCase();
        model.addAttribute("legal_case", legalCase);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("phases", phaseService.findAll());
        return "case_add_form";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable Long id, Model model) {
        model.addAttribute("legal_case", caseService.findById(id).orElseThrow(RuntimeException::new));
        model.addAttribute("activePage", "Cases");
        model.addAttribute("edit", true);
        model.addAttribute("eventList", eventService.findAllByLegalCaseId(id));
        model.addAttribute("persons", personService.findAllByLegalCaseId(id));
        model.addAttribute("documents", Arrays.asList("документ1", "документ2", "документ3", "документ4", "документ5"));
        return "case_show_form";
    }

    @GetMapping("/edit/{id}")
    public String createCase(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Cases");
        model.addAttribute("edit", true);
        model.addAttribute("legal_case", caseService.findById(id).orElseThrow(RuntimeException::new));
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("phases", phaseService.findAll());
        return "case_edit_form";
    }

    @PostMapping("/add")
    public String addOrEditProduct(Model model, RedirectAttributes redirectAttributes, LegalCase legalCase) {
        model.addAttribute("activePage", "Cases");

        try {
            caseService.add(legalCase);
        } catch (Exception ex) {
            logger.error("Problem with creating or update case", ex);
            redirectAttributes.addFlashAttribute("error", true);
            if (legalCase.getId() == null) {
                return "redirect:/legal_cases/add";
            }
            return "redirect:/legal_cases/edit/" + legalCase.getId();
        }
        return "redirect:/legal_cases";
    }
}
