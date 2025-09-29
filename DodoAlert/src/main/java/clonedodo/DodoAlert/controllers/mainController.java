package clonedodo.DodoAlert.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {

    @GetMapping("/topic/message")
    public String mainPage() {
        return "main";
    }
}
