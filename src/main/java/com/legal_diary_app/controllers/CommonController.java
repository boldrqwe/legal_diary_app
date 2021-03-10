package com.legal_diary_app.controllers;

import com.legal_diary_app.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;

@Component
public class CommonController {
    protected CaseService caseService;
    protected EventService eventService;
    protected PersonService personService;
    protected CategoryService categoryService;
    protected PhaseService phaseService;
    protected PersonStatusService personStatusService;
    protected DocumentService documentService;
    protected final ServletContext servletContext;
    protected UserService userService;


    public CommonController(CaseService caseService, EventService eventService, PersonService personService,
                          CategoryService categoryService, PhaseService phaseService,
                          PersonStatusService personStatusService, DocumentService documentService,
                          ServletContext servletContext, UserService userService) {
        this.caseService = caseService;
        this.eventService = eventService;
        this.personService = personService;
        this.categoryService = categoryService;
        this.phaseService = phaseService;
        this.personStatusService = personStatusService;
        this.documentService = documentService;
        this.servletContext = servletContext;
        this.userService = userService;
    }


    protected String getAuthName(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        return authentication.getName();
    }
}
