package com.seolgi.detector.camera.api.controller.v1.member;


import lombok.Getter;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
public class DropForm {
    @NotNull(message = "비밀번호를 입력해 주세요.")
    private String password;
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public boolean isNotValid() {
        Set<ConstraintViolation<DropForm>> constraintViolations = validator.validate( this );
        return constraintViolations.isEmpty() == false;
    }

    public String getErrorMessage() {
        Set<ConstraintViolation<DropForm>> constraintViolations = validator.validate( this );
        return constraintViolations.iterator().next().getMessage();
    }
}
