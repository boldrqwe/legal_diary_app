package com.legal_diary_app.data;

import com.legal_diary_app.data.utils.InvalidDateException;
import com.legal_diary_app.model.AbstractItem;
import com.legal_diary_app.model.LegalCase;
import com.legal_diary_app.model.Person;
import com.legal_diary_app.model.UserDiary;
import com.legal_diary_app.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class EventData extends AbstractItem implements Serializable {

    private CaseService caseService;

    @Autowired
    public EventData(CaseService caseService) {
        this.caseService = caseService;
    }

    public EventData() {
    }


    private String name;


    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date beginningDate;


    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date endingDate;

    private boolean endStatus = false;


    private List<Person> persons = new ArrayList<>();


    private UserDiary userDiary;


    private LegalCase legalCase;

    private Long legalCaseId;

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
       if(beginningDate != null && endingDate != null) {
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
        if(endingDate != null && beginningDate != null) {
            if (endingDate.before(beginningDate)) {
                throw new InvalidDateException("EndingDate should be after beginningDate!");
            }
        }
        this.endingDate = endingDate;
    }

    public boolean isEndStatus() {
        return endStatus;
    }

    public void setEndStatus(boolean endStatus) {
        this.endStatus = endStatus;
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

    public Long getLegalCaseId() {
        return legalCaseId;
    }

    public void setLegalCaseId(Long legalCaseId) {
        this.legalCaseId = legalCaseId;
    }

    public void setLegalCaseById(Long id) {
        this.legalCase = caseService.findById(id).get();
    }
}
