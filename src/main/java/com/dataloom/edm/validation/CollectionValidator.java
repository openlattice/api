package com.dataloom.edm.validation;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorContext;

public class CollectionValidator
        implements ConstraintValidator<ValidateCollection, Collection>, ValidatorContextAwareConstraintValidator {

    private ValidatorContext validatorContext;

    private Class<?>         elementType;
    private Class<?>[]       constraints;
    private boolean          allViolationMessages;

    @Override
    public void setValidatorContext( ValidatorContext validatorContext ) {
        this.validatorContext = validatorContext;
    }

    @Override
    public void initialize( ValidateCollection constraintAnnotation ) {
        //Debug by Ho Chung
        System.out.println("Got here: initialized");
        elementType = constraintAnnotation.elementType();
        constraints = constraintAnnotation.constraints();
        allViolationMessages = constraintAnnotation.allViolationMessages();
    }

    @Override
    public boolean isValid( Collection collection, ConstraintValidatorContext context ) {
        //Debug by Ho Chung
        System.out.println("Got here: validation");
        boolean valid = true;

        if ( collection == null ) {
            // null collection cannot be validated
            return false;
        }

        Validator validator = validatorContext.getValidator();

        boolean beanConstrained = validator.getConstraintsForClass( elementType ).isBeanConstrained();

        for ( Object element : collection ) {
            Set<ConstraintViolation<?>> violations = new HashSet<ConstraintViolation<?>>();

            if ( beanConstrained ) {
                violations.addAll( validator.validate( element ) );
            } else {
                violations.addAll( validator.validate( element, constraints ) );
            }

            if ( !violations.isEmpty() ) {
                valid = false;
            }

            if ( allViolationMessages ) {
                for ( ConstraintViolation<?> violation : violations ) {
                    ConstraintViolationBuilder violationBuilder = context
                            .buildConstraintViolationWithTemplate( violation.getMessage() );
                    violationBuilder.addConstraintViolation();
                }
            }
        }

        return valid;
    }
}