package org.learningspringwithduc.firstspring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomController {
    @RequestMapping("/")
    public String index() {
        return "index.html";
    }
}
