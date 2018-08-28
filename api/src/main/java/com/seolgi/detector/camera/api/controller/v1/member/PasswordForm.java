package com.seolgi.detector.camera.api.controller.v1.member;


import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
public class PasswordForm {
    @NotNull(message = "기존 비밀번호를 입력해 주세요.")
    private String originPassword;
    @NotNull(message = "새 비밀번호를 입력해 주세요.")
    @Length(min = 8 , max = 30 , message = "비밀번호는 8자 이상 30자 이하로 구성해주세요.")
    private String newPassword;

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


    public boolean isNotValid() {
        Set<ConstraintViolation<PasswordForm>> constraintViolations = validator.validate( this );
        return constraintViolations.isEmpty() == false;
    }

    public String getErrorMessage() {
        Set<ConstraintViolation<PasswordForm>> constraintViolations = validator.validate( this );
        return constraintViolations.iterator().next().getMessage();
    }
}
