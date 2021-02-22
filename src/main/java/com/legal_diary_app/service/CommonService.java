package com.legal_diary_app.service;

import com.legal_diary_app.model.AbstractItem;
import com.legal_diary_app.model.LegalCase;
import com.legal_diary_app.repository.CaseRep;

import java.util.List;
import java.util.Optional;

public interface CommonService <E extends AbstractItem> {


     List<E> findAll();

     Optional<E> findById(Long id);

     E save(E type);


     void deleteById(Long id);
}
