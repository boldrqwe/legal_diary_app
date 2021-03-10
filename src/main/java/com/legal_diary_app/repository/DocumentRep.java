package com.legal_diary_app.repository;

import com.legal_diary_app.model.Document;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DocumentRep extends CommonRep<Document> {

    @Query(value = "select d from Document d join d.legalCases l where l.id = ?1")
    List<Document> findAllByLegalCaseId(Long id);

    @Query(value = "SELECT d from Document d join d.events e where e.id =?1")
    List<Document> findAllByEventId(Long id);

    @Query(value = "select d from Document d join d.users u where u.username = ?1")
    List<Document> findAllByUserName(String name);




}
