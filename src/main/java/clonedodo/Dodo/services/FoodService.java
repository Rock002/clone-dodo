package clonedodo.Dodo.services;

import clonedodo.Dodo.models.entity.Food;
import clonedodo.Dodo.repository.FoodRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public void saveFoodToDB(Food food) {
        foodRepository.save(food);
    }

    public void deleteFood(Food food) {
        foodRepository.delete(food);
    }

    public List<Food> getListOfFood() {
        return foodRepository.findAll();
    }

    public Food findFoodById(Long id) {
        return foodRepository.findById(id).orElse(null);
    }

}
