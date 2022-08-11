package com.baranbatur.vetApp.controller;

import com.baranbatur.vetApp.model.Animal;
import com.baranbatur.vetApp.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;

@Controller
public class AnimalController {

    @Autowired
    AnimalService animalService;

    @GetMapping("/all")
    public HashMap<String, Object> getAllAnimals() {
        HashMap<String, Object> response = new HashMap<>();
        response.put("animals", animalService.getAllAnimals());
        return response;
    }

    @GetMapping("/add-animal")
    public String addAnimal2(Model model) {

        model.addAttribute("animalForm", new Animal());
        return "dashboard";
    }

    @PostMapping("/add-animal")
    public String addAnimal(@ModelAttribute("animalForm") Animal animal, Model model) {
        System.out.println(animal.getName());
        boolean response = animalService.saveAnimal(animal);
        if (response) {
            model.addAttribute("success", "Animal added successfully");
            return "redirect:/add-animal";
        } else {
            model.addAttribute("error", "Animal not added");
            return "redirect:/add-animal";
        }
    }


}
