package com.legal_diary_app.repository;

import com.legal_diary_app.model.LegalCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaseRep extends CommonRep<LegalCase> {



    @Query(value = "select l from  LegalCase l join l.users u where u.username = ?1")
    List<LegalCase> findAllByUserName(String name);
}
