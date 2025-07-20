package clonedodo.Dodo.controllers;

import clonedodo.Dodo.models.entity.User;
import clonedodo.Dodo.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping("/postregistration")
    public String postRegistration(User user) {
        user.setRoles("USER");
        userService.saveUser(user);
        return "redirect:/login";
    }
}
