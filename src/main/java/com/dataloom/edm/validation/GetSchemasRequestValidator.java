package com.dataloom.edm.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import com.dataloom.edm.requests.GetSchemasRequest;

public class GetSchemasRequestValidator implements ConstraintValidator<ValidateGetSchemasRequest, GetSchemasRequest> {

    @Override
    public void initialize( ValidateGetSchemasRequest constraintAnnotation ) {}

    @Override
    public boolean isValid( GetSchemasRequest req, ConstraintValidatorContext context ) {
        if ( req == null ) {
            return true;
        }

        if ( req.getName().isPresent() && StringUtils.isNotBlank( req.getName().get() ) ) {
            return req.getNamespace().isPresent() && StringUtils.isNotBlank( req.getNamespace().get() );
        } else {
            return true;
        }
    }
}