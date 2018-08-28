package com.seolgi.detector.camera.web.interceptor.common.member;

import com.seolgi.detector.domain.member.Member;
import lombok.Getter;

@Getter
public class MemberDto {
    private boolean logged = false;
    private long id;
    private String loginId;

    public MemberDto(Member member) {

        if (member != null) {
            this.logged = true;
            this.id = member.getId();
            this.loginId = member.getLoginId();
        }
    }
}
