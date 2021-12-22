package com.jkhhuse.plan.service.food.impl;

import com.jkhhuse.plan.dao.food.FoodDao;
import com.jkhhuse.plan.dto.food.FoodDTO;
import com.jkhhuse.plan.dto.specialmilk.SpecialMilkDTO;
import com.jkhhuse.plan.entity.food.FoodDO;
import com.jkhhuse.plan.service.food.FoodService;
import org.dozer.DozerBeanMapper;

import javax.annotation.Resource;
import java.util.List;

public class FoodServiceImpl implements FoodService {

    @Resource
    private FoodDao foodDao;

    @Override
    public List<FoodDTO> searchFood(String name) {
        List<FoodDO> foods = foodDao.searchFoodByName(name);
        DozerBeanMapper mapper = new DozerBeanMapper();
        FoodDTO foodDTO = mapper.map(foods, FoodDTO.class);
        return null;
    }

    @Override
    public FoodDTO findFood(String uuid) {
        FoodDO foodDO = foodDao.findByUuid(uuid);
        DozerBeanMapper mapper = new DozerBeanMapper();
        FoodDTO foodDTO = mapper.map(foodDO, FoodDTO.class);
        return foodDTO;
    }
}
