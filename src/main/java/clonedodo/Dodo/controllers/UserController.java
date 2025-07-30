package clonedodo.Dodo.controllers;

import clonedodo.Dodo.models.dto.FoodDto;
import clonedodo.Dodo.models.entity.Food;
import clonedodo.Dodo.models.entity.User;
import clonedodo.Dodo.services.FoodService;
import clonedodo.Dodo.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private final UserService userService;
    private final FoodService foodService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, FoodService foodService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.foodService = foodService;
        this.passwordEncoder = passwordEncoder;
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
        model.addAttribute("foodList", listOfFood);
        return "basket";
    }

    @PostMapping("/toBasket")
    public String toBasket() {
        return "redirect:/basket";
    }

    @PostMapping("/deleteFromBasket/{id}")
    public String deleteFoodFromBasket(@PathVariable String id, Authentication authentication) {
        String name = authentication.getName();
        User user = userService.findByUsername(name).orElseThrow();
        List<Food> foodList = user.getListOfFood();
        StringBuilder builder = new StringBuilder();
        int len = id.length();
        int index = 0;
        for (int i = 0; i < len; i++) {
            if (id.charAt(i) != '%') {
                builder.append(id.charAt(i));
                index++;
                System.out.println(String.valueOf(builder));
            } else {
                break;
            }
        }
        builder.deleteCharAt(index - 1);
        System.out.println(String.valueOf(builder));
        long valueOfId = Long.parseLong(String.valueOf(builder));
        Food food = foodService.findFoodById(valueOfId);
        foodList.remove(food);
        user.setListOfFood(foodList);
        userService.saveUser(user);
        return "redirect:/basket";
    }

    @PostMapping("/backToMain")
    public String backToMain() {
        return "redirect:/";
    }

    @PostMapping("/postregistration")
    public String postRegistration(User user) {
        user.setRoles("USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/login";
    }

    @PostMapping("/adminpostregistration")
    public String adminPostRegistration(User user) {
        user.setRoles("ADMIN");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/login";
    }
}
