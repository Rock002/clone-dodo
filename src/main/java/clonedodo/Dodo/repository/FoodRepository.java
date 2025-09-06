package clonedodo.Dodo.repository;

import clonedodo.Dodo.models.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
