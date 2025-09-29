package clonedodo.DodoAlert.models.dto;


import clonedodo.DodoAlert.models.entity.Food;
import java.util.List;

public record UserDto(
        Long id,
        String username,
        String password,
        String roles,
        List<Food> listOfFood
)
{}
