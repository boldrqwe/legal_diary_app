package com.legal_diary_app.repository;

import com.legal_diary_app.model.LegalCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseRep extends CommonRep<LegalCase> {
}
