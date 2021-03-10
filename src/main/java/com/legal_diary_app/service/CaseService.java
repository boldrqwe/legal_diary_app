package com.legal_diary_app.service;

import com.legal_diary_app.model.LegalCase;
import com.legal_diary_app.repository.CaseRep;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseService extends AbstractService<LegalCase, CaseRep> {


    public CaseService(CaseRep caseRep) {
        super(caseRep);
    }

    public List<LegalCase> findAllByUserName(String name){
        return super.repository.finaAllByUserName(name);
    }

}
