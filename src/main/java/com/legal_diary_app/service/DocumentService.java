package com.legal_diary_app.service;

import com.legal_diary_app.model.Document;
import com.legal_diary_app.model.Event;
import com.legal_diary_app.model.User;
import com.legal_diary_app.repository.DocumentRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DocumentService extends AbstractService<Document, DocumentRep> {
    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private CaseService caseService;

    public DocumentService(DocumentRep documentRep) {
        super(documentRep);
    }

    public Document save(String name, String path) {
        Document document = new Document(name, path);
        return super.save(document);
    }

    public List<Document> findAllByUserName(String name){
        return repository.findAllByUserName(name);
    }

    public void saveDocAndEventAndUser(List<MultipartFile> files, Event event){
        List<Document> documents = new ArrayList<>();
        User user = userService.findByUsername(getAuthName());
        for (MultipartFile file : files) {
            Document document = saveDoc(file, user);
            documents.add(document);
            event.getDocuments().add(document);
        }
        saveAll(documents);
        userService.save(user);
        eventService.save(event);
    }

    public void saveDocAndUser(List<MultipartFile> files) {
        List<Document> documents =  new ArrayList<>();
        User user = userService.findByUsername(getAuthName());
        for (MultipartFile file : files) {
            documents.add(saveDoc(file, user));
        }
        saveAll(documents);
        userService.save(user);
    }

    public List<Document> findAllByLegalCaseId(Long id) {
        return super.repository.findAllByLegalCaseId(id);
    }

    public List<Document> findAllByEventId(Long id) {
        return super.repository.findAllByEventId(id);
    }

    private Document saveDoc(MultipartFile file, User user){
        String filePath = "files/" + UUID.randomUUID() + (file.getOriginalFilename()).substring(file
                .getOriginalFilename().lastIndexOf("."));
        try (BufferedOutputStream stream =
                     new BufferedOutputStream(new FileOutputStream(new File(filePath)))) {
            byte[] bytes = file.getBytes();
            stream.write(bytes);
            Document document = save(file.getOriginalFilename(), filePath);
            document.getUsers().add(user);
            user.getDocuments().add(document);
            return document;
        } catch (Exception e) {
            throw new RuntimeException("Вам не удалось загрузить " + file.getOriginalFilename() + " => " + e.getMessage());
        }
    }

}
