package com.legal_diary_app.service;

import com.legal_diary_app.model.Category;
import com.legal_diary_app.model.Phase;
import com.legal_diary_app.repository.PhaseRep;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhaseService extends AbstractService<Phase, PhaseRep> {


    public PhaseService(PhaseRep phaseRep) {
        super(phaseRep);
    }

    public Phase add(Phase category){
        return super.repository.save(category);
    }


}
