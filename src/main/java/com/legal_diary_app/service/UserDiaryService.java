package com.legal_diary_app.service;

import com.legal_diary_app.model.UserDiary;
import com.legal_diary_app.repository.UserDiaryRep;
import org.springframework.stereotype.Service;

@Service
public class UserDiaryService extends AbstractService<UserDiary, UserDiaryRep> {


    public UserDiaryService(UserDiaryRep userDiaryRep) {
        super(userDiaryRep);
    }




}
