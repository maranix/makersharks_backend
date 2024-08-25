package com.example.makersharks.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValueValidator implements ConstraintValidator<Enum, String> {

    private Object[] enumValues;
    private boolean nullable;

    @Override
    public void initialize(Enum annotation) {
        this.enumValues = annotation.enumClass().getEnumConstants();
        this.nullable = annotation.nullable();
    }

    @Override
    public boolean isValid(String valueForValidation, ConstraintValidatorContext constraintValidatorContext) {
        if (nullable) {
            return true;
        }

        if (enumValues != null) {
            for (Object enumValue : enumValues) {
                if (valueForValidation.equalsIgnoreCase(((java.lang.Enum<?>) enumValue).name())) {
                    return true;
                }
            }
        }

        return false;
    }
}