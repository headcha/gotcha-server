package com.seolgi.detector.camera.web.api.v1.member;


import com.seolgi.detector.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberForm {
    @NotNull(message = "아이디를 입력해주세요.")
    @Length(min = 8 , max = 50 , message = "아이디는 8자 이상 50자 이하로 구성해주세요.")
    @Email(message = "이메일 형식과 다릅니다.")
    private String id;

    @NotNull(message = "비밀번호를 입력해 주세요.")
    @Length(min = 8 , max = 30 , message = "비밀번호는 8자 이상 30자 이하로 구성해주세요.")
    private String password;


    @AssertTrue(message = "서비스 이용약관 및 개인정보 보호 방침에 동의 해주세요")
    private boolean confirm;


    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


    public Member create(PasswordEncoder passwordEncoder) {
        return Member.builder()
                    .loginId(this.id)
                    .password(passwordEncoder.encode(this.password))
                    .termsOfServiceConfirm(this.confirm)
                    .privacyPolicyConfirm(this.confirm)
                .build();
    }

    public boolean isNotValid() {
        Set<ConstraintViolation<MemberForm>> constraintViolations = validator.validate( this );
        return constraintViolations.isEmpty() == false;
    }

    public String getErrorMessage() {
        Set<ConstraintViolation<MemberForm>> constraintViolations = validator.validate( this );
        return constraintViolations.iterator().next().getMessage();
    }
}
