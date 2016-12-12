package com.dataloom.edm.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Credits: http://stackoverflow.com/questions/4308224/hibernate-validation-of-collections-of-primitives
 * @author Ho Chung Siu
 *
 */
@Target( { ElementType.FIELD, ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.TYPE_USE } )
@Retention( RetentionPolicy.RUNTIME )
@Constraint(
    validatedBy = { CollectionValidator.class } )
@Documented

public @interface ValidateCollection {

    Class<?> elementType();

    /*
     * Specify constraints when collection element type is NOT constrained
     * validator.getConstraintsForClass(elementType).isBeanConstrained();
     */
    Class<?>[] constraints() default {};

    boolean allViolationMessages() default true;

    String message() default "{ValidCollection.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
