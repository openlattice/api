package com.dataloom.edm.validation;

import javax.validation.ValidatorContext;

public interface ValidatorContextAwareConstraintValidator {

    void setValidatorContext(ValidatorContext validatorContext);

}
