package clonedodo.Dodo.controllers;

import clonedodo.Dodo.models.entity.Food;
import clonedodo.Dodo.models.entity.User;
import clonedodo.Dodo.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
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

    @GetMapping("/adminregistration")
    public String adminRegistration() {
        return "adminregistration";
    }

    @GetMapping("/basket")
    public String basketFood(Authentication authentication, Model model) {
        String name = authentication.getName();
        User user = userService.findByUsername(name).orElseThrow();
        List<Food> listOfFood = user.getListOfFood();
//        System.out.println(listOfFood);
        if (listOfFood.isEmpty()) {
            return "emptyBasket";
        }
        model.addAttribute("foodList", user.getListOfFood());
        return "basket";
    }

    @PostMapping("/toBasket")
    public String toBasket() {
        return "redirect:/basket";
    }

    @PostMapping("/backToMain")
    public String backToMain() {
        return "redirect:/";
    }

    @PostMapping("/postregistration")
    public String postRegistration(User user) {
        user.setRoles("USER");
        userService.saveUser(user);
        return "redirect:/login";
    }

    @PostMapping("/adminpostregistration")
    public String adminPostRegistration(User user) {
        user.setRoles("ADMIN");
        userService.saveUser(user);
        return "redirect:/login";
    }
}
