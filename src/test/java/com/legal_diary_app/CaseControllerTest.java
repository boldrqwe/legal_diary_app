package com.legal_diary_app;

import com.legal_diary_app.data.CaseData;
import com.legal_diary_app.mappers.CommonMapper;
import com.legal_diary_app.model.LegalCase;
import com.legal_diary_app.model.User;
import com.legal_diary_app.repository.CaseRep;
import com.legal_diary_app.service.CaseService;
import com.legal_diary_app.service.UserService;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class CaseControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CaseRep caseRep;

    @Autowired
    UserService userService;

    @Autowired
    CaseService caseService;


    @WithMockUser(value = "admin", password = "admin", roles = {"ADMIN"})
    @Test
    public void showAllCasesTest() throws Exception {
        mvc.perform(get("/legal_cases")).andExpect(status().isOk())
                .andExpect(model().attributeExists("legal_cases"));


    }

    @WithMockUser(value = "admin", password = "admin", roles = {"ADMIN"})
    @Test
    public void addCasesTest() throws Exception {
        mvc.perform(post("/legal_cases/add")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1")
                .param("name", "newCase")
                .with(csrf())
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/legal_cases"));
    }
}
