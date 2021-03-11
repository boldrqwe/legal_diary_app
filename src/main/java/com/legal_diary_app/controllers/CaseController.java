package com.legal_diary_app.controllers;

import com.legal_diary_app.controllers.utils.MediaTypeUtils;
import com.legal_diary_app.data.CaseData;
import com.legal_diary_app.data.EventData;
import com.legal_diary_app.data.PersonData;
import com.legal_diary_app.mappers.CommonMapper;
import com.legal_diary_app.model.*;
import com.legal_diary_app.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.*;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/legal_cases")
public class CaseController extends CommonController {
    private static final Logger logger = LoggerFactory.getLogger(CaseController.class);

    public CaseController(CaseService caseService, EventService eventService, PersonService personService, DocumentService documentService,
                          ServletContext servletContext, UserService userService) {
        super(caseService, eventService, personService,
                documentService, servletContext, userService);
    }


    @GetMapping
    public String showCases(Model model) {
        model.addAttribute("legal_cases", CommonMapper.INSTANCE.toCaseDataList(
                caseService.findAllByUserName(getAuthName())));
        model.addAttribute("activePage", "Cases");
        return "legal_cases";
    }

    @GetMapping("/add")
    public String addCase(Model model) {
        model.addAttribute("activePage", "Cases");
        model.addAttribute("add", true);
        LegalCase legalCase = new LegalCase();
        model.addAttribute("legal_case", legalCase);
        return "case_add_form";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable Long id, Model model) {
        CaseData caseData = CommonMapper.INSTANCE.toCaseData(caseService.findById(id).orElseThrow(RuntimeException::new));
        model.addAttribute("legal_case", caseData);
        model.addAttribute("activePage", "Cases");
        model.addAttribute("edit", true);
        model.addAttribute("eventList", CommonMapper.INSTANCE.toEventDataList(eventService.findAllByLegalCaseId(id)));
        model.addAttribute("persons", CommonMapper.INSTANCE.toPersonDataList(personService.findAllByLegalCaseId(id)));
        model.addAttribute("documents", documentService.findAllByLegalCaseId(id));
        model.addAttribute("event", new EventData());
        model.addAttribute("person", new PersonData());
        return "case_show_form";
    }

    @PostMapping("/add")
    public String addCase(Model model, LegalCase legalCase) {
        model.addAttribute("activePage", "Cases");
        User user = userService.getCurrentUser();
        user.getLegalCases().add(legalCase);
        legalCase.getUsers().add(user);
        userService.save(user);
        caseService.save(legalCase);
        return "redirect:/legal_cases";
    }

    @PostMapping("/{id}/edit")
    public String editCase(CaseData caseData) {
        caseService.save(CommonMapper.INSTANCE.toCase(caseData));
        return "redirect:/legal_cases/" + caseData.getId();
    }


    @PostMapping("/add_event")
    public String addEvent(Model model, @Valid @ModelAttribute EventData eventData, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/legal_cases/" + eventData.getLegalCase().getId();
        }
        model.addAttribute("activePage", "Case");
        Event event = CommonMapper.INSTANCE.toEvent(eventData);
        User user = userService.findByUsername(getAuthName());
        event.getUsers().add(user);
        user.getEvents().add(event);
        eventService.save(event);
        userService.save(user);
        return "redirect:/legal_cases/" + event.getLegalCase().getId();
    }

    @PostMapping("/add_person")
    public String addPerson(Model model, PersonData personData) {
        Person person = CommonMapper.INSTANCE.toPerson(personData);
        personService.save(person);
        LegalCase legalCase = caseService.findById(personData.getCases().get(0).getId()).get();
        legalCase.getPersons().add(person);
        caseService.save(legalCase);
        model.addAttribute("activePage", "Cases");
        return "redirect:/legal_cases/" + personData.getCases().get(0).getId();
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        caseService.deleteById(id);
        return "redirect:/legal_cases";
    }

    @PostMapping("/upload/{id}")
    public String handleFileUpload(@RequestParam("file") List<MultipartFile> files, @PathVariable Long id) {
        LegalCase legalCase = caseService.findById(id).get();
        if (!files.isEmpty()) {
            for (MultipartFile file : files) {
                String filePath = "files/" + UUID.randomUUID() + (file.getOriginalFilename()).substring(file
                        .getOriginalFilename().lastIndexOf("."));
                try (BufferedOutputStream stream =
                             new BufferedOutputStream(new FileOutputStream(new File(filePath)))) {
                    byte[] bytes = file.getBytes();
                    stream.write(bytes);
                    Document document = new Document(file.getOriginalFilename(), filePath);
                    document.getLegalCases().add(legalCase);
                    legalCase.getDocuments().add(document);
                    documentService.save(document);
                    caseService.save(legalCase);
                } catch (Exception e) {
                    throw new RuntimeException("Вам не удалось загрузить " + file.getOriginalFilename() + " => " + e.getMessage());
                }
            }
        } else {
            return "redirect:/legal_cases/" + legalCase.getId();
        }
        return "redirect:/legal_cases/" + legalCase.getId();
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable Long id) throws IOException {
        Document document = documentService.findById(id).get();
        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, document.getName());
        File file = new File(document.getFilePath());
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(mediaType)
                .contentLength(file.length())
                .body(resource);
    }
}
