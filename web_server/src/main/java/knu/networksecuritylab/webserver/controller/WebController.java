package knu.networksecuritylab.webserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @GetMapping("/home")
    public String home() {
        return "Home";
    }

    @GetMapping("/member")
    public String member() {
        return "Member";
    }

    @GetMapping("/publication")
    public String publication() {
        return "Publication";
    }

    @GetMapping("/notice")
    public String notice() {
        return "Notice";
    }

    @GetMapping("/photo")
    public String photo() {
        return "Photo";
    }

    @GetMapping("/history")
    public String history() {
        return "History";
    }
}