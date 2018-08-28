package com.seolgi.detector.camera.web.controller.account.search.password;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account/search/password")
public class SearchPasswordController {

    @RequestMapping("/password")
    public String passwordForm() {
        return "account/search/password/form";
    }
}
