package com.seolgi.detector.domain.utils.validator;

import com.seolgi.detector.domain.utils.PasswordUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PasswordValidator implements ConstraintValidator<CheckPassword, String> {

    public void initialize(CheckPassword constraintAnnotation) {

    }

    public boolean isValid(String value, ConstraintValidatorContext constraintContext) {
        return PasswordUtil.isValid(value);
    }
}
