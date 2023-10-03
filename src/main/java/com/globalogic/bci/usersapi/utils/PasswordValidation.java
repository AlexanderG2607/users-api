package com.globalogic.bci.usersapi.utils;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordValidator.class) // Enlace con la clase que implementa la validación
@Documented
@JacksonAnnotationsInside
@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonSerialize(using = MyCustomValidatorSerializer.class)
public @interface PasswordValidation {
}
