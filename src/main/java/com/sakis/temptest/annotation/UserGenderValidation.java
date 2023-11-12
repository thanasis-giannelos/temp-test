package com.sakis.temptest.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserGenderValidation implements ConstraintValidator<ValidateUserGender, String> {

    @Override
    public boolean isValid(String gender, ConstraintValidatorContext constraintValidatorContext) {
        return (gender == "Male" || gender == "Female");
    }
}
