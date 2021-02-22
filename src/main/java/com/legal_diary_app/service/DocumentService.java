package com.legal_diary_app.service;

import com.legal_diary_app.model.Document;
import com.legal_diary_app.repository.DocumentRep;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService extends AbstractService<Document, DocumentRep> {

    public DocumentService(DocumentRep documentRep){ super(documentRep);}

    public Document save(String name, String path){
        Document document = new Document(name, path);
        return super.save(document);
    }

    public List<Document> findAllByLegalCaseId(Long id){
        return super.repository.findAllByLegalCaseId(id);
    }


}
