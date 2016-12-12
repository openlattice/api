package com.dataloom.edm.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorFactory;
import javax.validation.ValidatorContext;

public class ConstraintValidatorFactoryImpl implements ConstraintValidatorFactory {

    private ValidatorContext validatorContext;

    public ConstraintValidatorFactoryImpl( ValidatorContext nativeValidator ) {
        //Debug by Ho Chung
        System.out.println("Got here! ValidatorContext: " + nativeValidator );
        this.validatorContext = nativeValidator;
    }

    @Override
    public <T extends ConstraintValidator<?, ?>> T getInstance( Class<T> key ) {
        T instance = null;

        try {
            instance = key.newInstance();
        } catch ( Exception e ) {
            // could not instantiate class
            e.printStackTrace();
        }

        if ( ValidatorContextAwareConstraintValidator.class.isAssignableFrom( key ) ) {
            ValidatorContextAwareConstraintValidator validator = (ValidatorContextAwareConstraintValidator) instance;
            validator.setValidatorContext( validatorContext );
        }

        return instance;
    }

    @Override
    public void releaseInstance( ConstraintValidator<?, ?> instance ) {
        // TODO Auto-generated method stub

    }

}
