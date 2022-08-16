package com.baranbatur.vetApp.validator;

import com.baranbatur.vetApp.model.AnimalOwner;
import com.baranbatur.vetApp.model.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@Lazy
public class OwnerValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return AnimalOwner.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AnimalOwner owner = (AnimalOwner) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        if (owner.getName().length() < 2 || owner.getName().length() > 32) {
            errors.rejectValue("name", "Size.ownerForm.name");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "NotEmpty");
        if (owner.getSurname().length() < 2 || owner.getSurname().length() > 32) {
            errors.rejectValue("surname", "Size.ownerForm.surname");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");

        String email = owner.getEmail();
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        if (!email.matches(regex)) {
            errors.rejectValue("email", "Pattern.ownerForm.email");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "NotEmpty");
        if (owner.getPhoneNumber().length() != 10) {
            errors.rejectValue("phoneNumber", "Size.ownerForm.phone");
        }


    }
}
