package com.jkhhuse.plan.service.food.impl;

import com.jkhhuse.plan.dao.food.FoodDao;
import com.jkhhuse.plan.dto.food.FoodDTO;
import com.jkhhuse.plan.entity.food.FoodDO;
import com.jkhhuse.plan.service.food.FoodService;
import com.jkhhuse.plan.utils.CommonBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

@Service
public class FoodServiceImpl implements FoodService {

    @Resource
    private FoodDao foodDao;

    @Override
    public List<FoodDTO> searchFood(String name) {
        List<FoodDO> foods = foodDao.searchFoodByName(name);
        return CommonBeanUtils.copyListProperties(foods, FoodDTO::new);
    }

    @Override
    public List<FoodDTO> searchFoodWithNull() {
        List<FoodDO> foods = foodDao.searchFoodInNames();
        return CommonBeanUtils.copyListProperties(foods, FoodDTO::new);
    }
    @Override
    public FoodDTO findFood(String uuid) {
        Optional<FoodDO> foodDO = foodDao.findByUuid(uuid);

        if (Optional.ofNullable(foodDO).isEmpty()) {
            return null;
        } else {
            FoodDTO foodDTO = new FoodDTO();
            BeanUtils.copyProperties(foodDO.get(), foodDTO);
            return foodDTO;
        }
    }
}
