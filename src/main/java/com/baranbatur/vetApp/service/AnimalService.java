package com.baranbatur.vetApp.service;

import com.baranbatur.vetApp.model.Animal;
import com.baranbatur.vetApp.repository.IAnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.JoinTable;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalService {

    @Autowired
    IAnimalRepository animalRepository;

    public Animal getAnimalById(Long id) {
        return animalRepository.findById(id).get();
    }

    public boolean saveAnimal(Animal animal) {

        if (animal.getId() == null) {
            animalRepository.save(animal);
            return true;
        } else {
            return false;
        }

    }

    public void deleteAnimal(Long id) {
        animalRepository.deleteById(id);
    }

    public Animal updateAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    public List<Animal> getAllAnimals() {
        return new ArrayList<>(animalRepository.findAll());
    }

}
