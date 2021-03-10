package com.legal_diary_app.service;

import com.legal_diary_app.model.AbstractItem;
import com.legal_diary_app.repository.CommonRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public abstract class AbstractService<E extends AbstractItem, R extends CommonRep<E>> implements CommonService<E> {

    protected R repository;

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

    public E save(E type){ return repository.save(type);}

    public R getRepository() {
        return repository;
    }

    public void setRepository(R repository) {
        this.repository = repository;
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<E> saveAll(List<E> typeList){
        return repository.saveAll(typeList);
    }

    public String getAuthName() {
    SecurityContext securityContext = new SecurityContextHolder().getContext();
    Authentication authentication = securityContext.getAuthentication();
    return authentication.getName();
}
}
