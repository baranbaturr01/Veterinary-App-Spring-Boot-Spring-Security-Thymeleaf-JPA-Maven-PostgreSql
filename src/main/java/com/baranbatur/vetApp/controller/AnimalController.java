package com.baranbatur.vetApp.controller;

import com.baranbatur.vetApp.model.Animal;
import com.baranbatur.vetApp.model.AnimalOwner;
import com.baranbatur.vetApp.repository.IAnimalRepository;
import com.baranbatur.vetApp.service.AnimalOwnerService;
import com.baranbatur.vetApp.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;

@Controller
public class AnimalController {

    @Autowired
    AnimalService animalService;

    @Autowired
    IAnimalRepository animalRepository;

    @Autowired
    AnimalOwnerService animalOwnerService;

    //get all animals for api
    @GetMapping("/list-all-animals")
    public String getAllAnimals(Model model) {

        List<Animal> animals = animalService.getAllAnimals();
        System.out.println(animals);
        model.addAttribute("listAnimal2s", animals);
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
    public String addAnimal(@ModelAttribute("animalForm") Animal animal, Model model) {
        System.out.println(animal.getName());
        boolean response = animalService.saveAnimal(animal);
        if (response) {
            model.addAttribute("success", "Animal added successfully");
            return "redirect:/list-all-animals";
        } else {
            model.addAttribute("error", "Animal not added");
            return "redirect:/list-all-animals";
        }
    }

    @GetMapping("/animal/edit/{id}")
    public String editAnimal(@PathVariable("id") Long id, Model model) {

        Animal animal = animalService.getAnimalById(id);
        model.addAttribute("animalForm", animal);
        List<AnimalOwner> animalOwnerList = animalOwnerService.getAllAnimalOwners();
        model.addAttribute("animalOwnerList", animalOwnerList);
        return "dashboard";
    }

    @GetMapping("/animal/delete/{id}")
    public String deleteAnimal(@PathVariable("id") Long id, Model model) {
        animalService.deleteAnimal(id);
        return "redirect:/list-all-animals";
    }


}
