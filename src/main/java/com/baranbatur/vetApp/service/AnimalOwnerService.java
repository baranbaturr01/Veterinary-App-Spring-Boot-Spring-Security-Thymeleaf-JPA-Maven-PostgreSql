package com.baranbatur.vetApp.service;

import com.baranbatur.vetApp.model.AnimalOwner;
import com.baranbatur.vetApp.repository.IAnimalOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalOwnerService {
    @Autowired
    IAnimalOwnerRepository animalOwnerRepository;

    public AnimalOwner getAnimalOwnerById(Long id) {
        return animalOwnerRepository.findById(id).get();
    }

    public boolean saveAnimalOwner(AnimalOwner animalOwner) {

        if (animalOwner.getId() == null) {
            animalOwnerRepository.save(animalOwner);
            return true;
        } else {
            return false;
        }
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
