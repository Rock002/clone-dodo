package clonedodo.Dodo.controllers;

import clonedodo.Dodo.models.dto.FoodDto;
import clonedodo.Dodo.models.entity.Food;
import clonedodo.Dodo.models.entity.User;
import clonedodo.Dodo.services.FoodService;
import clonedodo.Dodo.services.UserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FoodController {

    private final FoodService foodService;
    private final UserService userService;

    public FoodController(FoodService foodService, UserService userService) {
        this.foodService = foodService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        List<Food> foodList = foodService.getListOfFood();
        model.addAttribute("foodList", foodList);
        return "main";
    }

    @GetMapping("/create")
    public String createFood() {
        return "addFood";
    }

    @GetMapping("/product/{id}")
    public String product(@PathVariable Long id, Model model) {
        Food food = foodService.findFoodById(id);
        model.addAttribute("food", food);
        return "product";
    }

    @PostMapping("/postbook")
    public String postBook() {
        return "redirect:/";
    }

    @PostMapping("/product/{id}")
    public String openProduct() {
        return "redirect:/product/{id}";
    }

    @PostMapping("/createfood")
    public String postCreateFood(Food food) {
        foodService.saveFoodToDB(food);
        return "redirect:/";
    }

    @PostMapping("/addToList/{id}")
    public String addToList(@PathVariable Long id, Authentication authentication) {
        Food food = foodService.findFoodById(id);
        String name = authentication.getName();
        User user = userService.findByUsername(name).orElseThrow();
        List<Food> foodList = user.getListOfFood();
        foodList.add(food);
        user.setListOfFood(foodList);
        userService.saveUser(user);
        return "redirect:/";
    }
}
