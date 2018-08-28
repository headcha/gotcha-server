package com.seolgi.detector.camera.web.controller.policy.terms;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/policy/terms")
public class TermsController {


    @GetMapping
    public String index(Model model) {

        return "policy/terms/index";
    }
}
