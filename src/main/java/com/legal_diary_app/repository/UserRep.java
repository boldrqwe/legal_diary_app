package com.legal_diary_app.repository;

import com.legal_diary_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRep  extends CommonRep<User> {

    User findByUsername(String username);

}
