package com.project.ticket_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/")
public class WelcomeController {

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }
}
