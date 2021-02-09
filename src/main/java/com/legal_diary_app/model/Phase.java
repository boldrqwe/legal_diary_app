package com.legal_diary_app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "phase")
public class Phase extends AbstractItem {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "phase")
    List<LegalCase> cases = new ArrayList<>();


    public Phase() {
    }

    public Phase(String name) {
        this.name = name;
    }


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

    @Override
    public String toString() {
        return
                name;
    }
}
