package com.dataloom.edm.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.dataloom.edm.internal.EntitySet;

@ValidateCollection(elementType=EntitySet.class)
@Target( { ElementType.FIELD, ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.TYPE_USE } )
@Retention( RetentionPolicy.RUNTIME )
@Constraint( validatedBy = { } )
@Documented

public @interface ValidateEntitySets {

    String message() default "{Collection.EntitySet.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
