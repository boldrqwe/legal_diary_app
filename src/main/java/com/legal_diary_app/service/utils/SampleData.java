package com.legal_diary_app.service.utils;

import com.legal_diary_app.model.*;
import com.legal_diary_app.repository.*;
import org.springframework.data.jpa.repository.config.JpaRepositoryNameSpaceHandler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component

public class SampleData {
    private EventRep eventRep;
    private PersonRep personRep;
    private RoleRep roleRep;
    private UserRep userRep;
    private CaseRep caseRep;

    private DocumentRep documentRep;


    public SampleData(EventRep eventRep, PersonRep personRep, RoleRep roleRep, UserRep userRep,
                      CaseRep caseRep,
                      DocumentRep documentRep) {
        this.eventRep = eventRep;
        this.personRep = personRep;
        this.roleRep = roleRep;
        this.userRep = userRep;
        this.caseRep = caseRep;
        this.documentRep = documentRep;
    }

    @PostConstruct
    public void init() throws ParseException {
        User user1 = new User();
        user1.setUsername("alex");
        user1.setPassword("$2y$12$kppL/79H63sx3NoXlZhY/uDW2EiB18ByX8YeENyFwyxAnHjrCT4pK");

        User user2 = new User();
        user2.setUsername("pupa");
        user2.setPassword("$2y$12$kppL/79H63sx3NoXlZhY/uDW2EiB18ByX8YeENyFwyxAnHjrCT4pK");

        Role role1 = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER");
        user1.getRoles().add(role1);
        user2.getRoles().add(role2);

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        Date bd = sdf.parse("01.01.2021 12:30");
        Date ed = sdf.parse("01.01.2021 12:45");
        Date ed2 = sdf.parse("28.02.2021 12:45");


        Event event = new Event();
        event.setBeginningDate(bd);
        event.setEndingDate(ed);
        event.setName("Продажа квартиры");


        Event event2 = new Event();
        event2.setBeginningDate(bd);
        event2.setEndingDate(ed);
        event2.setName("Продажа квартиры2");


        Event event3 = new Event();
        event3.setBeginningDate(bd);
        event3.setEndingDate(ed2);
        event3.setName("Продажа квартиры3");


        Person person1 = new Person("Филлипенко", "Владимир", "Рудольфович");
        Person person2 = new Person("Капрошин", "Алексей", "Ольгович");
        Person person3 = new Person("Резаков", "Дмитрий", "Владимирович");
        Person person4 = new Person("Столяров", "Валентин", "Олегович");
        Person person5 = new Person("Стояков", "Антон", "Антоныч");
        Person person6 = new Person("Шусунбаев", "Зумбуруб", "Эрникович");
        Person person7 = new Person("Жибидерян", "Жрик", "Каласан оглы");
        Person person8 = new Person("Вавилов", "Унтер", "Маншинович");
        Person person9 = new Person("Лопорылов", "Лемар", "Кустонбалаев");
        Person person10 = new Person("Боярышникова", "Людмила", "Карласовна");
        Person person11 = new Person("Троекурово", "Алена", "Гагамедова");





        List<Event> eventList = new ArrayList<>();
        eventList.add(event);


        LegalCase legalCase = new LegalCase();

        legalCase.setEvents(eventList);
        legalCase.setName("Суд");
        personRep.saveAll(Arrays.asList(person1, person2, person3, person4, person5, person6, person7, person8, person9,
                person10, person11));
        legalCase.setPersons(Arrays.asList(person1));


        eventRep.saveAll(Arrays.asList(event, event2, event3));
        event.setLegalCase(legalCase);
        event2.setLegalCase(legalCase);
        event3.setLegalCase(legalCase);
        user1.getLegalCases().add(legalCase);
        legalCase.getUsers().add(user1);
        legalCase.setEvents(eventList);
        caseRep.save(legalCase);
        eventRep.saveAll(Arrays.asList(event, event2, event3));





        roleRep.save(role1);
        userRep.save(user1);

        roleRep.save(role2);
        userRep.save(user2);

        Document document = new Document("file","asd");

        documentRep.save(document);


    }
}
