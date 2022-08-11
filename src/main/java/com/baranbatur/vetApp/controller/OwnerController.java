package com.baranbatur.vetApp.controller;

import com.baranbatur.vetApp.model.AnimalOwner;
import com.baranbatur.vetApp.service.AnimalOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;

@Controller
public class OwnerController {

    @Autowired
    private AnimalOwnerService animalOwnerService;

    @GetMapping("/owner-all")
    public HashMap<String, Object> getAllAnimalOwners() {
        HashMap<String, Object> response = new HashMap<>();
        response.put("animalOwners", animalOwnerService.getAllAnimalOwners());
        return response;
    }


    @GetMapping("/add-owner")
    public String addOwner2(Model model) {
        model.addAttribute("ownerForm", new AnimalOwner());
        return "create-owner";
    }

    @PostMapping("/add-owner")
    public String addOwner(@ModelAttribute("ownerForm") AnimalOwner owner, Model model) {
        System.out.println(owner.getName());
        boolean response = animalOwnerService.saveAnimalOwner(owner);
        if (response) {
            model.addAttribute("success", "Owner added successfully");
            return "redirect:/add-owner";
        } else {
            model.addAttribute("error", "Owner not added");
            return "redirect:/add-owner";
        }
    }

}
