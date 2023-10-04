package com.globalogic.bci.usersapi.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<PasswordValidation, String> {


    private static boolean containsOneUpperCase(String s){
        return s.matches("^[^A-Z]*[A-Z][^A-Z]*$");
    }

    private static boolean containsTwoDigits(String s){
        return s.matches("^(?:[^\\d]*\\d){2}[^\\d]*$");
    }

    private static boolean between8And12chars(String s){
        return s.matches("^.{8,12}$");
    }

    @Override
    public void initialize(PasswordValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return containsOneUpperCase(value)
        && containsTwoDigits(value)
        && between8And12chars(value);
    }
}
