package com.jkhhuse.plan.service.food;

import com.jkhhuse.plan.dto.food.FoodDTO;

import java.util.List;

public interface FoodService {

    List<FoodDTO> searchFood(String name);

    FoodDTO findFood(String uuid);
}
