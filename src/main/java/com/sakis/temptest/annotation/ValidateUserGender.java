package com.sakis.temptest.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserGenderValidation.class)
public @interface ValidateUserGender {
    public String message() default "Invalid gender: must be MALE or FEMALE";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
