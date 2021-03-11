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


    CaseData toCaseData(LegalCase legalCase);

    default List<CaseData> toCaseDataList(List<LegalCase> caseList) {
        return caseList.stream().map(legalCase -> toCaseData(legalCase)).collect(Collectors.toList());
    }

    LegalCase toCase(CaseData caseData);

    default List<LegalCase> toCaseList(List<CaseData> caseDataList){
        return caseDataList.stream().map(caseData -> toCase(caseData)).collect(Collectors.toList());
    }


    Event toEvent(EventData eventData);

    default List<Event> toEvenList(List<EventData> events) {
        return events.stream().map(event -> toEvent(event)).collect(Collectors.toList());
    }

    EventData toEventData(Event event);

    default List<EventData> toEventDataList(List<Event> events) {
        return events.stream().map(event -> toEventData(event)).collect(Collectors.toList());
    }

    DocumentData toDocData(Document document);

    default List<DocumentData> toDocDataList(List<Document> documentList) {
        return documentList.stream().map(document -> toDocData(document)).collect(Collectors.toList());
    }

    Document toDocument(DocumentData documentData);

    default List<Document> toDocList(List<DocumentData> documentDataList) {
        return documentDataList.stream().map(documentData -> toDocument(documentData)).collect(Collectors.toList());
    }

}
