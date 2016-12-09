package com.dataloom.edm.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.olingo.commons.api.edm.FullQualifiedName;

public class FullQualifiedNameValidator implements ConstraintValidator<ValidateFullQualifiedName, FullQualifiedName> {

    @Override
    public void initialize( ValidateFullQualifiedName constraintAnnotation ) {}

    @Override
    public boolean isValid( FullQualifiedName fqn, ConstraintValidatorContext context ) {
        return ( fqn != null ) && StringUtils.isNotBlank( fqn.getNamespace() )
                && StringUtils.isNotBlank( fqn.getName() );
    }
}