package com.seolgi.detector.camera.web.controller.policy.privacy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/policy/privacy")
public class PrivacyController {

    @GetMapping
    public String index() {
        return "policy/privacy/index";
    }
}
