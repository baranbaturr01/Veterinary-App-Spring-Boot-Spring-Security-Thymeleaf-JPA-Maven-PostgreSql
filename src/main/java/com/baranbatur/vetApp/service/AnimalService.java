package com.baranbatur.vetApp.service;

import com.baranbatur.vetApp.model.Animal;
import com.baranbatur.vetApp.repository.IAnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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

        animalRepository.save(animal);
        return true;

    }

    public void deleteAnimal(Long id) {
        animalRepository.deleteById(id);
    }


    public List<Animal> getAllAnimals(String keyword) {
        
        if (keyword != null) {
            return new ArrayList<>(animalRepository.search(keyword));
        } else {
            return new ArrayList<>(animalRepository.findAll());
        }

    }


    public List<Animal> getAnimalsByOwnerId(Long id) {
        return animalRepository.findByAnimalOwnerId(id);
    }


}
