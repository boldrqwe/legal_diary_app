package com.legal_diary_app.repository;

import com.legal_diary_app.model.AbstractItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CommonRep <E extends AbstractItem> extends JpaRepository<E, Long> {
}
