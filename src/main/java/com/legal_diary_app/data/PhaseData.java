package com.legal_diary_app.data;

import com.legal_diary_app.model.AbstractItem;
import com.legal_diary_app.model.LegalCase;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class PhaseData extends AbstractItem {

    private String name;


    List<LegalCase> cases = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LegalCase> getCases() {
        return cases;
    }

    public void setCases(List<LegalCase> cases) {
        this.cases = cases;
    }
}
