package com.baranbatur.vetApp.service;

import com.baranbatur.vetApp.model.AnimalOwner;
import com.baranbatur.vetApp.repository.IAnimalOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class AnimalOwnerService {
    @Autowired
    IAnimalOwnerRepository animalOwnerRepository;

    public AnimalOwner getAnimalOwnerById(Long id) {
        return animalOwnerRepository.findById(id).get();
    }

    public AnimalOwner saveAnimalOwner(AnimalOwner animalOwner) {
        return animalOwnerRepository.save(animalOwner);
    }

    public void deleteAnimalOwner(Long id) {
        animalOwnerRepository.deleteById(id);
    }

    public AnimalOwner updateAnimalOwner(AnimalOwner animalOwner) {
        return animalOwnerRepository.save(animalOwner);
    }

    public List<AnimalOwner> getAllAnimalOwners() {
        return new ArrayList<>(animalOwnerRepository.findAll());
    }

}
