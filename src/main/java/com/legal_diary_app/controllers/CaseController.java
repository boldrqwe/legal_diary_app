package com.legal_diary_app.controllers;

import com.legal_diary_app.data.CaseData;
import com.legal_diary_app.data.EventData;
import com.legal_diary_app.data.PersonData;
import com.legal_diary_app.data.PhaseData;
import com.legal_diary_app.mappers.CommonMapper;
import com.legal_diary_app.model.Event;
import com.legal_diary_app.model.LegalCase;
import com.legal_diary_app.model.Person;
import com.legal_diary_app.model.Phase;
import com.legal_diary_app.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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
    private PersonStatusService personStatusService;


    public CaseController(CaseService caseService, EventService eventService, PersonService personService,
                          CategoryService categoryService, PhaseService phaseService,
                          PersonStatusService personStatusService) {
        this.caseService = caseService;
        this.eventService = eventService;
        this.personService = personService;
        this.categoryService = categoryService;
        this.phaseService = phaseService;
        this.personStatusService = personStatusService;
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
        CaseData caseData = CommonMapper.INSTANCE.toCaseData(caseService.findById(id).orElseThrow(RuntimeException::new));
        model.addAttribute("legal_case", caseData);
        model.addAttribute("activePage", "Cases");
        model.addAttribute("edit", true);
        model.addAttribute("eventList", CommonMapper.INSTANCE.toEventDataList(eventService.findAllByLegalCaseId(id)));
        model.addAttribute("persons", CommonMapper.INSTANCE.toPersonDataList(personService.findAllByLegalCaseId(id)));
        model.addAttribute("documents", Arrays.asList("документ1", "документ2", "документ3", "документ4", "документ5"));
        model.addAttribute("event", new EventData());
        model.addAttribute("person", new PersonData());
        model.addAttribute("statusList", CommonMapper.INSTANCE.toPersonStatusDataList(personStatusService.findAll()));
        return "case_show_form";
    }

    @GetMapping("/edit/{id}")
    public String createCase(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Cases");
        model.addAttribute("edit", true);
        model.addAttribute("legal_case",
                caseService.findById(id).orElseThrow(RuntimeException::new));
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

    @PostMapping("/add_event")
    public String addEvent(Model model, @Valid @ModelAttribute EventData eventData, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "redirect:/legal_cases/" + eventData.getLegalCase().getId();
        }
        model.addAttribute("activePage", "Cases");
        Event event = CommonMapper.INSTANCE.toEvent(eventData);
        eventService.add(event);
        return "redirect:/legal_cases/" + eventData.getLegalCase().getId();
    }

    @PostMapping("/add_person")
    public String addPerson(Model model, PersonData personData) {
        Person person = CommonMapper.INSTANCE.toPerson(personData);
        personService.add(person);
        LegalCase legalCase = caseService.findById(personData.getCases().get(0).getId()).get();
        legalCase.getPersons().add(person);
        caseService.add(legalCase);
        model.addAttribute("activePage", "Cases");
        return "redirect:/legal_cases/" + personData.getCases().get(0).getId();
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        caseService.deleteById(id);
        return "redirect:/legal_cases";
    }

    @GetMapping("/add_phase")
    public String addPhase(Model model) {
        model.addAttribute("phase", new PhaseData());
        return "phase_add_form";
    }

    @PostMapping("/add_phase")
    public String addPhase(PhaseData phaseData) {
        Phase phase = CommonMapper.INSTANCE.toPhase(phaseData);
        phaseService.add(phase);
        return "redirect:/phase_add_form";
    }


}
