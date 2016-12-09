package com.dataloom.edm.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.dataloom.edm.requests.GetSchemasRequest;

public class GetSchemasRequestValidator implements ConstraintValidator<ValidateGetSchemasRequest, GetSchemasRequest> {

    @Override
    public void initialize( ValidateGetSchemasRequest constraintAnnotation ) {}

    @Override
    public boolean isValid( GetSchemasRequest req, ConstraintValidatorContext context ) {
        if ( req == null ) {
            return true;
        }

        return ( req.getName().isPresent() && req.getNamespace().isPresent() ) || !req.getName().isPresent();
    }
}