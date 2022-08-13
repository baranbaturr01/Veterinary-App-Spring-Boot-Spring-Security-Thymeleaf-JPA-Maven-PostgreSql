package com.baranbatur.vetApp;

import com.baranbatur.vetApp.controller.AnimalController;
import com.baranbatur.vetApp.service.AnimalService;
import org.apache.catalina.security.SecurityConfig;
import org.aspectj.lang.annotation.Before;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(AnimalController.class)
public class AnimalServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAnimalListTest() throws Exception {
        //test add csrf token
        mockMvc.perform(get("/list-all-animals")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString("<title>Vet App</title>")));
    }

}
