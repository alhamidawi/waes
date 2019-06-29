package com.wearewaes.demo.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = Base64Validator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Base64Constraint {

    String message() default "Input is not base64 encoded";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
