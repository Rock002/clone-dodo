package clonedodo.Dodo.controllers;

import clonedodo.Dodo.models.entity.Food;
import clonedodo.Dodo.models.entity.User;
import clonedodo.Dodo.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @GetMapping("/basket")
    public String basketFood(Authentication authentication, Model model) {
        String name = authentication.getName();
        User user = userService.findByUsername(name).orElseThrow();
        model.addAttribute("foodList", user.getListOfFood());
        return "basket";
    }

    @PostMapping("/postregistration")
    public String postRegistration(User user) {
        user.setRoles("USER");
        userService.saveUser(user);
        return "redirect:/login";
    }
}
