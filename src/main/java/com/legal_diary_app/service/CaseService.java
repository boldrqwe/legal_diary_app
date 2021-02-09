package com.legal_diary_app.service;

import com.legal_diary_app.model.LegalCase;
import com.legal_diary_app.repository.CaseRep;
import org.springframework.stereotype.Service;

@Service
public class CaseService extends AbstractService<LegalCase, CaseRep> {


    public CaseService(CaseRep caseRep) {
        super(caseRep);
    }


    public LegalCase add(LegalCase legalCase) {
        return super.repository.save(legalCase);
    }


}
