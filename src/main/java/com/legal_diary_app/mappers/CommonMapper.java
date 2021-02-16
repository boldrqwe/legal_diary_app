package com.legal_diary_app.mappers;


import com.legal_diary_app.data.*;
import com.legal_diary_app.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface CommonMapper {

    CommonMapper INSTANCE = Mappers.getMapper(CommonMapper.class);


    PersonData toPersonData(Person person);

    Person toPerson(PersonData personData);

    default List<PersonData> toPersonDataList(List<Person> personList) {
        return personList.stream().map(person -> toPersonData(person)).collect(Collectors.toList());
    }


    PersonStatusData toPersonStatusData(PersonStatus personStatus);

    PersonStatus toPersonStatus(PersonStatusData personStatusData);

    default List<PersonStatusData> toPersonStatusDataList(List<PersonStatus> personStatusList) {
        return personStatusList.stream().map(personStatus -> toPersonStatusData(personStatus)).collect(Collectors.toList());
    }

    default List<PersonStatus> toPersonStatusList(List<PersonStatusData> personStatusDataList) {
        return personStatusDataList.stream().map(personStatusData -> toPersonStatus(personStatusData)).collect(Collectors.toList());
    }


    CaseData toCaseData(LegalCase legalCase);

    default List<CaseData> toCaseDataList(List<LegalCase> caseList) {
        return caseList.stream().map(legalCase -> toCaseData(legalCase)).collect(Collectors.toList());
    }

    PhaseData toPhaseData(Phase phase);

    Phase toPhase(PhaseData phaseData);

    default List<PhaseData> toPhaseDataList(List<Phase> phaseList) {
        return phaseList.stream().map(phase -> toPhaseData(phase)).collect(Collectors.toList());
    }


    Event toEvent(EventData eventData);

    default List<Event> toEvenList(List<EventData> events) {
        return events.stream().map(event -> toEvent(event)).collect(Collectors.toList());
    }

    EventData toEventData(Event event);

    default List<EventData> toEventDataList(List<Event> events) {
        return events.stream().map(event -> toEventData(event)).collect(Collectors.toList());
    }

}
