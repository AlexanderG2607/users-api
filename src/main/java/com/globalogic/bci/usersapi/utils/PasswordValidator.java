package com.globalogic.bci.usersapi.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<PasswordValidation, String> {

    public static void main(String[] args){

        String regex = "^(?=^[^A-Z]*[A-Z][^A-Z])(?=^(?:[^\\d]*\\d){2}[^\\d])(?=.{8,12}).*$";




        if(containsOneUpperCase("a2asfGf5vaa")
        && containsTwoDigits("a2asfGf5vaa")
        && between8And12chars("2asfGf5vaa")){
            System.out.println("Has one UpperCase char");
        }
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher("a2asfGf5vaa");
        matcher.groupCount();

        System.out.println(matcher.matches());
    }

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
        // Puedes realizar alguna inicialización si es necesario
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        String regexAlhpanumericBetween8And12Chars = "^(?=.*[A-Z])(?=.*\\d.*\\d)(?!.*[\\W_])(?=.{8,12}$).*$";
        Pattern pattern = Pattern.compile(regexAlhpanumericBetween8And12Chars);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();

    }
}
