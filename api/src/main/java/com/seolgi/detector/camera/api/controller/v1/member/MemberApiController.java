package com.seolgi.detector.camera.api.controller.v1.member;

import com.seolgi.detector.camera.api.common.auth.Role;
import com.seolgi.detector.camera.api.common.exception.ApplicationException;
import com.seolgi.detector.domain.member.Member;
import com.seolgi.detector.domain.member.MemberService;
import com.seolgi.detector.domain.utils.ModelMapperUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Api(tags = "MemberApi" , description = "회원 api")
@RestController
@RequestMapping("/api/v1/members")
public class MemberApiController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberService memberService;
    @Secured(Role.USER)
    @PutMapping("/password")
    public void passwordUpdate(@RequestBody PasswordForm form , Member member) {
        if (form.isNotValid())
            throw new ApplicationException(form.getErrorMessage());

        if (passwordEncoder.matches(form.getOriginPassword() , member.getPassword()) == false)
            throw new ApplicationException("비밀번호가 일치하지 않습니다.");

        member.updatePassword(passwordEncoder.encode(form.getNewPassword()));
        memberService.save(member);
    }
    @Secured(Role.USER)
    @PostMapping("/drop")
    public void drop(@RequestBody DropForm form , Member member , HttpServletRequest request , HttpServletResponse response) {
        if (form.isNotValid())
            throw new ApplicationException(form.getErrorMessage());

        if (passwordEncoder.matches(form.getPassword() , member.getPassword()) == false)
            throw new ApplicationException("비밀번호가 일치하지 않습니다.");

        memberService.drop(member.getLoginId());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
    }

    @GetMapping
    public ResponseEntity<MemberDto> logged(Member member) {
        return ResponseEntity.ok(ModelMapperUtils.map(member , MemberDto.class));
    }
}
