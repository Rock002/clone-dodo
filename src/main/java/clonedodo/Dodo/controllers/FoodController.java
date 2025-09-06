package clonedodo.Dodo.controllers;


import clonedodo.Dodo.kafka.KafkaProducerService;
import clonedodo.Dodo.models.dto.UserDto;
import clonedodo.Dodo.models.entity.Food;
import clonedodo.Dodo.models.entity.User;
import clonedodo.Dodo.services.FoodService;
import clonedodo.Dodo.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
public class FoodController {

    private final FoodService foodService;
    private final UserService userService;
    private final KafkaProducerService kafkaProducerService;

    public FoodController(FoodService foodService, UserService userService, KafkaProducerService kafkaProducerService) {
        this.foodService = foodService;
        this.userService = userService;
        this.kafkaProducerService = kafkaProducerService;
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        List<Food> foodList = foodService.getListOfFood();
        model.addAttribute("foodList", foodList);
        return "main";
    }

    @GetMapping("/createFood")
    public String createFood() {
        return "addFood";
    }

    @GetMapping("/product/{id}")
    public String product(@PathVariable Long id, Model model, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("not found"));
        String[] roles = user.getRoles().split(",");
        Food food = foodService.findFoodById(id);
        model.addAttribute("food", food);
        for (String role : roles) {
            if (role.equals("ADMIN")) {
                return "adminProduct";
            }
        }
        return "product";
    }

    @PostMapping("/postbook")
    public String postBook(Authentication authentication) throws JsonProcessingException {
        User user = userService.findByUsername(authentication.getName())
                        .orElseThrow(() -> new UsernameNotFoundException("not found"));
        kafkaProducerService.sendMessage(user);
        return "redirect:/";
    }

    @PostMapping("/product/{id}")
    public String openProduct() {
        return "redirect:/product/{id}";
    }

    @PostMapping("/postCreateFood")
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
        if (!foodList.contains(food)) {
            foodList.add(food);
            user.setListOfFood(foodList);
            userService.saveUser(user);
        }
        return "redirect:/";
    }

    @PostMapping("/remove/{id}")
    public String removeProduct(@PathVariable Long id) {
        Food food = foodService.findFoodById(id);
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            List<Food> foodList = user.getListOfFood();
            foodList.remove(food);
            user.setListOfFood(foodList);
            userService.saveUser(user);
        }
        foodService.deleteFood(food);
        return "redirect:/";
    }

    @PostMapping("/postUpdate/{id}")
    public String updateProduct(@PathVariable Long id, Food newFood) {
        Food food = foodService.findFoodById(id);
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            List<Food> foodList = user.getListOfFood();
            foodList.remove(food);
            user.setListOfFood(foodList);
            userService.saveUser(user);
        }
        foodService.deleteFood(food);
        foodService.saveFoodToDB(newFood);
        return "redirect:/";
    }
}
