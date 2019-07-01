package com.wearewaes.demo.validation;

import org.apache.commons.codec.binary.Base64;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class Base64Validator implements ConstraintValidator<Base64Constraint, String> {


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value != null){
            return Base64.isBase64(value);
        }
        return true;
    }
}
