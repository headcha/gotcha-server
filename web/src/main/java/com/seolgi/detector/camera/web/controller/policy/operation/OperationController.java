package com.seolgi.detector.camera.web.controller.policy.operation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/policy/operation")
public class OperationController {


    @GetMapping
    public String index() {

        return "policy/operation/index";
    }
}
