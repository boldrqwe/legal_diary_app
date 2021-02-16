package com.legal_diary_app.repository;

import ch.qos.logback.core.boolex.EvaluationException;
import com.legal_diary_app.model.Event;
import com.legal_diary_app.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRep extends CommonRep<Person>  {

    @Query(value = "select p from Person p join p.cases c where c.id = ?1")
    List<Person> findAllByLegalCaseId(Long id);

    @Query(value ="select p from Person p join p.events e where e.id = ?1")
    List<Person> findAllByEventId(Long id);

}
