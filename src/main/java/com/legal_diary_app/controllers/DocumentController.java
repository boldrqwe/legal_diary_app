package com.legal_diary_app.controllers;

import com.legal_diary_app.controllers.utils.MediaTypeUtils;
import com.legal_diary_app.mappers.CommonMapper;
import com.legal_diary_app.model.Document;
import com.legal_diary_app.service.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/documents")
public class DocumentController extends CommonController {


    public DocumentController(CaseService caseService, EventService eventService, PersonService personService,  DocumentService documentService, ServletContext servletContext, UserService userService) {
        super(caseService, eventService, personService, documentService, servletContext, userService);
    }

    @GetMapping
    public String showDoc(Model model) {
        model.addAttribute("documents", CommonMapper.INSTANCE.toDocDataList(documentService.findAllByUserName(getAuthName())));
        return "documents";
    }


    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") List<MultipartFile> files) {
        if (!files.isEmpty()) {
            documentService.saveDocAndUser(files);
        } else {
            return "redirect:/documents";
        }

        return "redirect:/documents";
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


