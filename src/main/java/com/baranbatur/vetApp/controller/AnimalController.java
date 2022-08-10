package com.baranbatur.vetApp.controller;

import com.baranbatur.vetApp.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    AnimalService animalService;

    @GetMapping("/all")
    public HashMap<String, Object> getAllAnimals() {
        HashMap<String, Object> response = new HashMap<>();
        response.put("animals", animalService.getAllAnimals());
        return response;
    }


}
