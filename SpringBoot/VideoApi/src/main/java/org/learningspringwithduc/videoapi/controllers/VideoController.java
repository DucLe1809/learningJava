package org.learningspringwithduc.videoapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VideoController {
    @RequestMapping("/")
    public String index() {

        return "index.html";
    }
}
