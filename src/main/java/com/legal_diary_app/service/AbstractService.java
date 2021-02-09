package com.legal_diary_app.service;

import com.legal_diary_app.model.AbstractItem;

import com.legal_diary_app.repository.CommonRep;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public abstract class AbstractService<E extends AbstractItem, R extends CommonRep<E>> implements CommonService {

    public R repository;

    @Autowired
    public AbstractService(R repository) {
        this.repository = repository;
    }


    public List<E> findAll() {
        return repository.findAll();
    }

    public Optional<E> findById(Long id) {
        return repository.findById(id);
    }




    public  void deleteById(Long id){repository.deleteById(id);};

}
