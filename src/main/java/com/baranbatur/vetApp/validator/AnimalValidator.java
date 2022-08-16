package com.baranbatur.vetApp.validator;

import com.baranbatur.vetApp.model.Animal;
import com.baranbatur.vetApp.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
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
        if (animal.getName().length() < 2 || animal.getName().length() > 32) {
            errors.rejectValue("name", "Size.animalForm.name");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type", "NotEmpty");
        if (animal.getType().length() < 2 || animal.getType().length() > 32) {
            errors.rejectValue("type", "Size.animalForm.type");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "NotEmpty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "animalOwner", "NotEmpty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "genus", "NotEmpty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty");

    }
}