package com.baranbatur.vetApp.controller;

import com.baranbatur.vetApp.validator.AnimalValidator;
import com.baranbatur.vetApp.model.Animal;
import com.baranbatur.vetApp.model.AnimalOwner;
import com.baranbatur.vetApp.service.AnimalOwnerService;
import com.baranbatur.vetApp.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AnimalController {

    @Autowired
    AnimalService animalService;

    @Autowired
    AnimalOwnerService animalOwnerService;

    @Autowired
    AnimalValidator animalValidator;

    @GetMapping({"/list-all-animals", "/search"})
    public String getAllAnimals(Model model, @Param("keyword") String keyword) {
        List<Animal> animals = animalService.getAllAnimals(keyword);
        model.addAttribute("listAnimal2s", animals);
        model.addAttribute("keyword", keyword);
        return "list-animals";

    }

    @GetMapping("/add-animal")
    public String addAnimal(Model model) {

        List<AnimalOwner> animalOwnerList = animalOwnerService.getAllAnimalOwners();

        model.addAttribute("animalOwnerList", animalOwnerList);
        model.addAttribute("animalForm", new Animal());
        return "dashboard";
    }

    @PostMapping("/add-animal")
    public String addAnimal(@ModelAttribute("animalForm") Animal animal, BindingResult result, Model model) {
        List<AnimalOwner> animalOwnerList = animalOwnerService.getAllAnimalOwners();
        animalValidator.validate(animal, result);
        if (result.hasErrors()) {
            model.addAttribute("animalOwnerList", animalOwnerList);
            return "dashboard";
        }
        boolean response = animalService.saveAnimal(animal);

        return "redirect:/list-all-animals";
    }

    @GetMapping("/animal/edit/{id}")
    public String editAnimal(@Valid @PathVariable("id") Long id, Model model) {

        Animal animal = animalService.getAnimalById(id);
        model.addAttribute("animalForm", animal);
        List<AnimalOwner> animalOwnerList = animalOwnerService.getAllAnimalOwners();
        model.addAttribute("animalOwnerList", animalOwnerList);
        return "dashboard";
    }

    @GetMapping("/animal/delete/{id}")
    public String deleteAnimal(@PathVariable("id") Long id, Model model) {
        model.addAttribute("success", "Animal deleted successfully");
        animalService.deleteAnimal(id);

        return "redirect:/list-all-animals";
    }

    @GetMapping("/animal-detail/{id}")
    public String getOwnerByAnimalId(@PathVariable("id") Long id, Model model) {

        Animal animal = animalService.getAnimalById(id);
        model.addAttribute("animal", animal);
        AnimalOwner animalOwner = animalOwnerService.getAnimalOwnerById(animal.getAnimalOwner().getId());
        model.addAttribute("animalOwner", animalOwner);
        return "owner-detail";
    }

    @GetMapping("/403")
    public String error403() {
        return "403";
    }


}
