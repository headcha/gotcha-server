package com.seolgi.detector.camera.web.controller.account.search;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account/search")
public class SearchController {

    @GetMapping
    public String index() {
        return "/account/search/index";
    }
}
