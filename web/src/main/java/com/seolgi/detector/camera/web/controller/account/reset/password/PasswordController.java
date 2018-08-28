package com.seolgi.detector.camera.web.controller.account.reset.password;

import com.seolgi.detector.camera.web.common.exception.ApplicationException;
import com.seolgi.detector.domain.member.auth.one_time.password.AuthPassword;
import com.seolgi.detector.domain.member.auth.one_time.password.AuthPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account/reset/password")
public class PasswordController {

    @Autowired
    private AuthPasswordService authPasswordService;

    @GetMapping
    public String form() {
        return "/account/reset/password/form";
    }

    @GetMapping("confirm/{accessToken}")
    public String confirm(@PathVariable String accessToken , Model model) {
        AuthPassword one = authPasswordService.findOneBy(accessToken);
        authPasswordService.access(one.getId());
        validate(one);


        model.addAttribute("accessToken" , accessToken);
        model.addAttribute("resetToken" , one.getResetToken());
        return "/account/reset/password/confirm";
    }

    private void validate(AuthPassword one) {
        if (one == null)
            throw new ApplicationException("인증 정보를 찾을 수 없습니다.");

        else if (one.isResetNotValid())
            throw new ApplicationException("만료된 인증 정보 입니다.");
    }
}
