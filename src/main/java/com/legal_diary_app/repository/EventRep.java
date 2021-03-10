package com.legal_diary_app.repository;

import com.legal_diary_app.model.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRep extends CommonRep<Event> {

    List<Event> findAllByLegalCaseId(Long id);

    @Query(value = "select e from Event e join e.users u where e.endStatus = ?1  and u.username = ?2")
    List<Event> findAllByEndStatusAndUserName(boolean isEnd, String name);

    List<Event> findAllByEndStatus(boolean isEnd);



    @Query(value = "select e from Event e join e.users u where u.username = ?1")
    List<Event> findAllByUserName(String name);

    @Query(value = "select count (e) from Event e join e.users u where e.endStatus = ?1  and u.username = ?2")
    Long findAllByEndStatusAndNameAndCount(boolean isEnd, String name);

}
