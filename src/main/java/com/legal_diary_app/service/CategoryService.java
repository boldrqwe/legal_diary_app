package com.legal_diary_app.service;

import com.legal_diary_app.model.Category;

import com.legal_diary_app.model.LegalCase;
import com.legal_diary_app.repository.CaseRep;
import com.legal_diary_app.repository.CategoryRep;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService extends AbstractService<Category, CategoryRep> {



    public CategoryService(CategoryRep categoryRep) {
        super(categoryRep);
    }


    public Optional<Category> findByLegalCaseId(Long id){
        return super.repository.findCategoryByLegalCaseId(id);
    }

    public Category add(Category category){
        return super.repository.save(category);
    }


}
