package com.baranbatur.vetApp.helper;

import com.baranbatur.vetApp.model.Animal;
import com.baranbatur.vetApp.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@Lazy
public class AnimalValidator implements Validator {
    @Autowired
    private AnimalService animalService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Animal.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Animal animal = (Animal) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        if (animal.getName().length() < 6 || animal.getName().length() > 32) {
            errors.rejectValue("name", "Size.animalForm.name");
        }

        if (animalService.findByName(animal.getName()) != null) {
            errors.rejectValue("name", "Duplicate.animalForm.name");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type", "NotEmpty");
        if (animal.getType().length() < 6 || animal.getType().length() > 32) {
            errors.rejectValue("type", "Size.animalForm.type");
        }

    }
}