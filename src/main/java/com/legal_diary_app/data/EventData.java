package com.legal_diary_app.data;

import com.legal_diary_app.data.utils.InvalidDateException;
import com.legal_diary_app.model.AbstractItem;
import com.legal_diary_app.model.LegalCase;
import com.legal_diary_app.model.Person;
import com.legal_diary_app.model.UserDiary;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class EventData extends AbstractItem {



    private String name;


    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH")
    private Date beginningDate;


    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH")
    private Date endingDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH")
    private Date reminderDate;

    private Boolean endStatus = false;

    private Boolean complete = false;

    private List<Person> persons = new ArrayList<>();


    private UserDiary userDiary;


    private LegalCase legalCase;

    public EventData() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBeginningDate() {
        return beginningDate;
    }


    public void setBeginningDate(Date beginningDate) {
        if (beginningDate != null && endingDate != null) {
            if (beginningDate.after(endingDate)) {
                throw new InvalidDateException("Beginning date should be before endingDate!");
            }
        }
        this.beginningDate = beginningDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        if (endingDate != null && beginningDate != null) {
            if (endingDate.before(beginningDate)) {
                throw new InvalidDateException("EndingDate should be after beginningDate!");
            }
        }
        this.endingDate = endingDate;
    }

    public Date getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(Date reminderDate) {
        this.reminderDate = reminderDate;
    }

    public Boolean getEndStatus() {
        return endStatus;
    }

    public void setEndStatus(Boolean endStatus) {
        this.endStatus = endStatus;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public UserDiary getUserDiary() {
        return userDiary;
    }

    public void setUserDiary(UserDiary userDiary) {
        this.userDiary = userDiary;
    }

    public LegalCase getLegalCase() {
        return legalCase;
    }

    public void setLegalCase(LegalCase legalCase) {
        this.legalCase = legalCase;
    }


}
