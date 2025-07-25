package clonedodo.Dodo.controllers;

import clonedodo.Dodo.models.dto.FoodDto;
import clonedodo.Dodo.models.entity.Food;
import clonedodo.Dodo.services.FoodService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodRestController {

    private final FoodService foodService;

    public FoodRestController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping()
    public List<FoodDto> mainPage() {
        List<FoodDto> listOfFoodDto = new ArrayList<>();
        List<Food> foodList = foodService.getListOfFood();
        for (Food food : foodList) {
            FoodDto product = new FoodDto(
                    food.getName(),
                    food.getCost()
            );
            listOfFoodDto.add(product);
        }
        return listOfFoodDto;
    }

}
