package com.legal_diary_app.data;

import com.legal_diary_app.model.AbstractItem;
import com.legal_diary_app.model.LegalCase;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryData extends AbstractItem implements Serializable {


    private String name;


    private List<LegalCase> legalCases = new ArrayList<>();

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
}
