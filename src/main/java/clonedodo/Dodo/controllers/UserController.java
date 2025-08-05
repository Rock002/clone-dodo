package clonedodo.Dodo.controllers;


import clonedodo.Dodo.models.entity.Food;
import clonedodo.Dodo.models.entity.User;
import clonedodo.Dodo.services.FoodService;
import clonedodo.Dodo.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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

    @GetMapping("/changePassword")
    public String changePassword() {
        return "changePassword";
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

    @GetMapping("/profile")
    public String profile(Authentication authentication, Model model) {
        String username = authentication.getName();
        User user =  userService.findByUsername(username).orElseThrow();
        model.addAttribute("user", user);
        return "profile";
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
            } else {
                break;
            }
        }
        builder.deleteCharAt(index - 1);
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

    @PostMapping("/toChangeForm")
    public String toChangeForm() {
        return "redirect:/changePassword";
    }

    @PostMapping("/toChangePassword")
    public String toChangePassword(String oldPassword, String newPassword, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("not found"));
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return "redirect:/changePassword";
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userService.saveUser(user);
        return "redirect:/";
    }

    @PostMapping("/postregistration")
    public String postRegistration(User user) {
        if (userService.findByUsername(user.getName()).isPresent()) {
            return "redirect:/registration";
        }
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

    @PostMapping("/toProfile")
    public String toProfile() {
        return "redirect:/profile";
    }

    @PostMapping("/deleteUser")
    public String deleteUserProfile(Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + "not found"));
        userService.deleteUser(user);
        return "redirect:/login";
    }
}
