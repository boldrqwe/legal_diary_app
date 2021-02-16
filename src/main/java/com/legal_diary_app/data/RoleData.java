package com.legal_diary_app.data;

import com.legal_diary_app.model.AbstractItem;

import javax.persistence.Column;

public class RoleData extends AbstractItem {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
