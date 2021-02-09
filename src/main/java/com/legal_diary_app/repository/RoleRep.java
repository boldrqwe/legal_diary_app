package com.legal_diary_app.repository;

import com.legal_diary_app.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRep extends CommonRep<Role> {
}
