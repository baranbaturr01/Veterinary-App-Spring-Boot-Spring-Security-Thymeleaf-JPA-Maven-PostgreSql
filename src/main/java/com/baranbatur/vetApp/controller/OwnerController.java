package com.baranbatur.vetApp.controller;

import com.baranbatur.vetApp.model.Animal;
import com.baranbatur.vetApp.model.AnimalOwner;
import com.baranbatur.vetApp.service.AnimalOwnerService;
import com.baranbatur.vetApp.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;

@Controller
public class OwnerController {

    @Autowired
    private AnimalOwnerService animalOwnerService;

    @Autowired
    private AnimalService animalService;

    @GetMapping("/get-all-owners")
    public String getAllAnimalOwners(Model model) {
        model.addAttribute("animalOwners", animalOwnerService.getAllAnimalOwners());
        return "list-owners";
    }


    @GetMapping("/add-owner")
    public String addOwner(Model model) {
        model.addAttribute("ownerForm", new AnimalOwner());
        return "create-owner";
    }

    @PostMapping("/add-owner")
    public String addOwner(@ModelAttribute("ownerForm") AnimalOwner owner, BindingResult result, Model model) {
        System.out.println(owner.getName());
        if (result.hasErrors()) {
            return "create-owner";
        } else {
            animalOwnerService.saveAnimalOwner(owner);
            return "redirect:/get-all-owners";
        }
    }

    @GetMapping("/owner/edit/{id}")
    public String editOwner(@PathVariable("id") Long id, Model model) {
        AnimalOwner owner = animalOwnerService.getAnimalOwnerById(id);
        model.addAttribute("ownerForm", owner);
        return "create-owner";
    }

    @GetMapping("/owner/delete/{id}")
    public String deleteOwner(@PathVariable("id") Long id, Model model) {
        animalOwnerService.deleteAnimalOwner(id);
        return "redirect:/get-all-owners";
    }

    @GetMapping("/owner/{id}")
    public String getOwnerAnimalsByOwnerId(@PathVariable("id") Long id, Model model) {
        List<Animal> animals = animalService.getAnimalsByOwnerId(id);
        model.addAttribute("ownerAnimals", animals);
        return "list-of-owner-animals";
    }

}
