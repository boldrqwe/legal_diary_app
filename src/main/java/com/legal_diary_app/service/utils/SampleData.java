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
    private UserDiaryRep userDiaryRep;
    private CaseRep caseRep;
    private CategoryRep categoryRep;
    private PersonStatusRep personStatusRep;
    private PhaseRep phaseRep;


    public SampleData(EventRep eventRep, PersonRep personRep, RoleRep roleRep, UserRep userRep,
                      UserDiaryRep userDiaryRep, CaseRep caseRep, CategoryRep categoryRep,
                      PersonStatusRep personStatusRep, PhaseRep phaseRep) {
        this.eventRep = eventRep;
        this.personRep = personRep;
        this.roleRep = roleRep;
        this.userRep = userRep;
        this.userDiaryRep = userDiaryRep;
        this.caseRep = caseRep;
        this.categoryRep = categoryRep;
        this.personStatusRep = personStatusRep;
        this.phaseRep = phaseRep;
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

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Date bd = sdf.parse("2021-01-01 12:30");
        Date ed = sdf.parse("2021-01-02 12:45");


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
        event3.setEndingDate(ed);
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

        Category category = new Category("Уголовный процесс");
        Category category1 = new Category("Гражданский процесс");
        Category category2 = new Category("Административный процесс");
        Category category3 = new Category("Арбитражный процесс");

        PersonStatus personStatus1 = new PersonStatus("Свидетель");
        PersonStatus personStatus2 = new PersonStatus("Подозреваемый");
        PersonStatus personStatus3 = new PersonStatus("Обвиняемый");
        PersonStatus personStatus4 = new PersonStatus("Подсудимый");
        PersonStatus personStatus5 = new PersonStatus("Истец");
        PersonStatus personStatus6 = new PersonStatus("Гражданский Истец");
        PersonStatus personStatus7 = new PersonStatus("Ответчик");
        PersonStatus personStatus8 = new PersonStatus("Третье лицо");
        PersonStatus personStatus9 = new PersonStatus("Следователь");
        PersonStatus personStatus10 = new PersonStatus("Прокурор");
        PersonStatus personStatus11 = new PersonStatus("Судья");
        PersonStatus personStatus12 = new PersonStatus("Дознаватель");
        PersonStatus personStatus13 = new PersonStatus("Дознаватель");

        Phase phase1 = new Phase("Возбуждение уголовного дела");
        Phase phase2 = new Phase("Преварительное расследование");
        Phase phase3 = new Phase("Приостановлено");
        Phase phase4 = new Phase("Подача искового заявления");
        Phase phase5 = new Phase("Аппеляция");
        Phase phase6 = new Phase("Кассация");
        Phase phase7 = new Phase("Возобновление дела по вновь открывшимся основаниям");

        phaseRep.saveAll(Arrays.asList(phase1, phase2, phase3, phase4, phase5, phase6, phase7));


        personStatusRep.saveAll(Arrays.asList(personStatus1, personStatus2, personStatus3, personStatus4, personStatus5,
                personStatus6, personStatus7, personStatus8, personStatus9, personStatus10, personStatus11, personStatus12,
                personStatus13));


        List<Event> eventList = new ArrayList<>();
        eventList.add(event);


        LegalCase legalCase = new LegalCase();
        legalCase.setCategory(category);
        legalCase.setEvents(eventList);
        legalCase.setName("Суд");
        personRep.saveAll(Arrays.asList(person1, person2, person3, person4, person5, person6, person7, person8, person9,
                person10, person11));
        legalCase.setPersons(Arrays.asList(person1));


        eventRep.saveAll(Arrays.asList(event, event2, event3));
        event.setLegalCase(legalCase);
        event2.setLegalCase(legalCase);
        event3.setLegalCase(legalCase);
        legalCase.setEvents(eventList);
        caseRep.save(legalCase);
        eventRep.saveAll(Arrays.asList(event, event2, event3));


        categoryRep.saveAll(Arrays.asList(category, category1, category2, category3));


        roleRep.save(role1);
        userRep.save(user1);

        roleRep.save(role2);
        userRep.save(user2);


    }
}
