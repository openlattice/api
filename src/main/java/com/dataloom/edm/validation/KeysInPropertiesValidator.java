package com.dataloom.edm.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.dataloom.edm.internal.EntityType;
import com.dataloom.edm.requests.GetSchemasRequest;

public class KeysInPropertiesValidator implements ConstraintValidator<ValidateKeysInProperties, EntityType> {

    @Override
    public void initialize( ValidateKeysInProperties constraintAnnotation ) {}

    @Override
    public boolean isValid( EntityType entityType, ConstraintValidatorContext context ) {
        if ( entityType == null ) {
            return true;
        }
        
        return entityType.getProperties().containsAll( entityType.getKey() );
    }
}