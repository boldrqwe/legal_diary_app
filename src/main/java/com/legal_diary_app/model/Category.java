package com.legal_diary_app.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class Category extends AbstractItem {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category",cascade = CascadeType.MERGE)
    private List<LegalCase> legalCases = new ArrayList<>();


    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LegalCase> getLegalCases() {
        return legalCases;
    }

    public void setLegalCases(List<LegalCase> legalCases) {
        this.legalCases = legalCases;
    }



    @Override
    public String toString() {
        return  name;
    }
}