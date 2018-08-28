package com.seolgi.detector.camera.web.common.context;

import com.seolgi.detector.domain.member.Member;
import com.seolgi.detector.domain.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MemberContext {

    @Autowired
    private MemberService memberService;

    public Member getLoggedMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null)
            return null;

        String memberId = authentication.getName();

        return memberService.findOneByLoginId(memberId);
    }
}
