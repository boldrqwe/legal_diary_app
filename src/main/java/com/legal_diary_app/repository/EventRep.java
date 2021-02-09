package com.legal_diary_app.repository;

import com.legal_diary_app.model.Event;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRep extends CommonRep<Event> {

    List<Event> findAllByLegalCaseId(Long id);

}
