package com.dataloom.edm.validation;

import java.lang.annotation.Target;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target( { ElementType.FIELD, ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.TYPE_USE } )
@Retention( RetentionPolicy.RUNTIME )
@Constraint(
    validatedBy = { KeysInPropertiesValidator.class } )
@Documented

public @interface ValidateKeysInProperties {

    String message() default "{EntityType.ValidateKeysInProperties.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}