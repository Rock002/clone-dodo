package clonedodo.Dodo.services;

import clonedodo.Dodo.models.entity.Food;
import clonedodo.Dodo.repository.FoodRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class FoodServiceTest {

    @Mock
    private FoodRepository foodRepository;

    @InjectMocks
    private FoodService foodService;

    @Test
    public void returnListOfFood() {
        Mockito.when(foodService.getListOfFood()).thenReturn(getList());
        List<Food> result = foodService.getListOfFood();
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("first", result.get(0).getName());
        Assertions.assertEquals(100, result.get(0).getCost());
        Assertions.assertEquals("second", result.get(1).getName());
        Assertions.assertEquals(200, result.get(1).getCost());
    }

    private List<Food> getList() {
        Food first = new Food("first", 100);
        Food second = new Food("second", 200);
        return List.of(first, second);
    }
}
