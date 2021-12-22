package com.jkhhuse.plan.dao.food;

import com.jkhhuse.plan.entity.food.FoodDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FoodDao extends JpaRepository<FoodDO, Long> {

    FoodDO findByUuid(String uuid);

    @Query(value="select from food where name like '%?%'")
    List<FoodDO> searchFoodByName(String name);
}