package com.legal_diary_app.controllers;

import com.legal_diary_app.controllers.utils.MediaTypeUtils;
import com.legal_diary_app.mappers.CommonMapper;
import com.legal_diary_app.model.Document;
import com.legal_diary_app.service.DocumentService;
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
import java.util.UUID;

@Controller
@RequestMapping("/documents")
public class DocumentController {

   private final DocumentService documentService;
    private final ServletContext servletContext;


    public DocumentController(DocumentService documentService, ServletContext servletContext) {
        this.documentService = documentService;
        this.servletContext = servletContext;
    }

    @GetMapping
    public String showDoc(Model model) {
        model.addAttribute("documents", CommonMapper.INSTANCE.toDocDataList(documentService.findAll()));
        return "documents";
    }


    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("name") String name,
                                   @RequestParam("file") MultipartFile file
                                   ) {
//        if (bindingResult.hasErrors()) {
//            return "redirect:/documents";
//        } else {
            if (!file.isEmpty()) {
                String filePath = "files/" + UUID.randomUUID() + (file.getOriginalFilename()).substring(file
                        .getOriginalFilename().lastIndexOf("."));
                try (BufferedOutputStream stream =
                             new BufferedOutputStream(new FileOutputStream(new File(filePath)))) {
                    byte[] bytes = file.getBytes();
                    stream.write(bytes);
                    documentService.save(name, filePath);
                    return "redirect:/documents";
                } catch (Exception e) {
                    return "Вам не удалось загрузить " + name + " => " + e.getMessage();
                }
            } else {
                return "Вам не удалось загрузить " + name + " потому что файл пустой.";
            }
//        }

    }

    @GetMapping("/download/{id}")
    public ResponseEntity<InputStreamResource> downloadFile1(@PathVariable Long id) throws IOException {
      Document document = documentService.findById(id).get();

        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, document.getName());

        File file = new File(document.getFilePath());
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                // Content-Type
                .contentType(mediaType)
                // Contet-Length
                .contentLength(file.length()) //
                .body(resource);
    }

}


