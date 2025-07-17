package clonedodo.Dodo.repository;

import clonedodo.Dodo.models.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
