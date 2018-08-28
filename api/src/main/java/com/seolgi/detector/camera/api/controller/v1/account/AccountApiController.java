package com.seolgi.detector.camera.api.controller.v1.account;

import com.seolgi.detector.camera.api.common.exception.ApplicationException;

import com.seolgi.detector.camera.api.controller.v1.member.MemberDto;
import com.seolgi.detector.camera.api.controller.v1.member.MemberForm;
import com.seolgi.detector.domain.member.Member;
import com.seolgi.detector.domain.member.MemberService;
import com.seolgi.detector.domain.utils.ModelMapperUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Api(tags =  "AccountApi", description = "계정 api")
@RestController
@RequestMapping("/api/v1/account")
public class AccountApiController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private AuthenticationManager authenticationManager;

    @ApiOperation(value = "회원 가입")
    @PostMapping("/signup")
    public ResponseEntity<MemberDto> signUp(@RequestBody MemberForm form) {

        if (form.isNotValid())
            throw new ApplicationException(form.getErrorMessage());

        if (memberService.existById(form.getId()))
            throw new ApplicationException("존재하는 ID 입니다.");

        Member save = memberService.save(form.create(passwordEncoder));

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(form.getId(), form.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return ResponseEntity.ok(ModelMapperUtils.map(save , MemberDto.class));
    }

    /**
     * 실제 처리는 Spring security 에서 한다 본 컨트롤러는 swagger 에서 api 테스트를 할 수 있게 하기 위해 생성함
     */
    @ApiOperation(value = "로그인 테스트용" ,  nickname = "login")
    @PostMapping("/signin")
    public void signIn(@RequestParam String id , @RequestParam String password , @RequestParam(defaultValue = "on") String rememberMe){
        System.out.println(id + password);
    }

    /**
     * 실제 처리는 Spring security 에서 한다 본 컨트롤러는 swagger 에서 api 테스트를 할 수 있게 하기 위해 생성함
     */
    @ApiOperation(value = "logout" , nickname = "logout")
    @GetMapping("/logout")
    public void logout(){
    }
}
