package com.legal_diary_app.repository;

import com.legal_diary_app.model.PersonStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonStatusRep extends CommonRep<PersonStatus> {


}
