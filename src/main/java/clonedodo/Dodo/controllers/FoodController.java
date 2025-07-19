package clonedodo.Dodo.controllers;

import clonedodo.Dodo.models.dto.FoodDto;
import clonedodo.Dodo.models.entity.Food;
import clonedodo.Dodo.services.FoodService;
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

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
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

    @PostMapping("/product/{id}")
    public String openProduct() {
        return "redirect:/product/{id}";
    }

    @PostMapping("/createfood")
    public String postCreateFood(Food food) {
        foodService.saveFoodToDB(food);
        return "redirect:/";
    }
}
