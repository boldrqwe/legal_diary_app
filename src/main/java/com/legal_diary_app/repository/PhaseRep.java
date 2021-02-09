package com.legal_diary_app.repository;

import com.legal_diary_app.model.LegalCase;
import com.legal_diary_app.model.Phase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhaseRep extends CommonRep<Phase> {
}
