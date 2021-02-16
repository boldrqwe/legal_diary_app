package com.legal_diary_app.service;


import com.legal_diary_app.model.PersonStatus;
import com.legal_diary_app.repository.PersonStatusRep;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonStatusService extends AbstractService<PersonStatus, PersonStatusRep> {

    public PersonStatusService(PersonStatusRep personStatusRep) {
        super(personStatusRep);
    }




}
