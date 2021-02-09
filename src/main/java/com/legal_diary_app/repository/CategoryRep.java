package com.legal_diary_app.repository;


import com.legal_diary_app.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRep  extends CommonRep<Category> {

    @Query(value = "select c from Category c join c.legalCases lc where lc.id = ?1 ")
    Optional<Category> findCategoryByLegalCaseId(Long id);
}
