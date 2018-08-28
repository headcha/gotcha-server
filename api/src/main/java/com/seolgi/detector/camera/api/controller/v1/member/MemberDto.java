package com.seolgi.detector.camera.api.controller.v1.member;


import com.seolgi.detector.domain.member.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
    private int id;
    private String loginId;
    private String password;
    private String phoneNumber;
    private String name;
    private boolean termsOfServiceConfirm;
    private boolean privacyPolicyConfirm;
}
