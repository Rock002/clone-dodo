package clonedodo.Dodo.services;

import clonedodo.Dodo.models.Food;
import clonedodo.Dodo.repository.FoodRepository;
import org.springframework.stereotype.Service;

@Service
public class FoodService {

    private FoodRepository foodRepository;

    public void saveFoodToDB(Food food) {
        foodRepository.save(food);
    }

    public void deleteFood(Food food) {
        foodRepository.delete(food);
    }

}
