package com.jkhhuse.plan.service.food;

import com.jkhhuse.plan.dto.food.FoodDTO;

import java.util.List;

public interface FoodService {

    List<FoodDTO> searchFood(String name);

    List<FoodDTO> searchFoodWithNull();

    FoodDTO findFood(String uuid);

}
