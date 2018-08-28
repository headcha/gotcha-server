package com.seolgi.detector.camera.web.controller.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {


    @GetMapping("/signup")
    public String form() {

        return "account/sign-up/form";

    }

    @GetMapping("/signin")
    public String singIn() {
        return "account/sign-in/form";
    }
}
