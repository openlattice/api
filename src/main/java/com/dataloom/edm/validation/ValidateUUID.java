package com.dataloom.edm.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Payload;
import javax.validation.constraints.Pattern;

@Target( { ElementType.FIELD, ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.TYPE_USE } )
@Retention( RetentionPolicy.RUNTIME )
@Pattern(regexp="^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$", message = "{UUID.ValidateUUID.message}")
@Documented
public @interface ValidateUUID {
    String message() default "{UUID.ValidateUUID.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
