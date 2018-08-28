package com.seolgi.detector.camera.web.controller.account.search.id;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account/search/id")
public class SearchIdController {

    @GetMapping
    public String form() {
        return "account/search/id/form";
    }
}
