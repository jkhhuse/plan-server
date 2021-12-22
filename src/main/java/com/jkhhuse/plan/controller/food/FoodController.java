package com.jkhhuse.plan.controller.food;

import com.jkhhuse.plan.common.CommonResponse;
import com.jkhhuse.plan.service.food.FoodService;
import com.jkhhuse.plan.service.specialmilk.SpecialMilkService;
import com.jkhhuse.plan.vo.specialmilk.SpecialMilkVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@Api("desc of class")
@RestController
@RequestMapping("/food")
public class FoodController {

    @Resource
    private FoodService foodService;

    @ApiOperation(value = "查询食物名称", notes = "根据食物名称进行查询")
    @GetMapping(value = "/search/{name}")
    CommonResponse<SpecialMilkVO> searchFood(
            @ApiParam(value = "食物名称", required = true) @Valid @PathVariable String name) {
        return new CommonResponse("200", foodService.findFood(name), "");
    }

    @ApiOperation(value = "查询食物详情", notes = "根据食物名称进行查询")
    @GetMapping(value = "/find/{foodId}")
    CommonResponse<SpecialMilkVO> getFoodDescription(
            @ApiParam(value = "食物 uuid", required = true) @Valid @PathVariable String foodId) {
        return new CommonResponse("200", foodService.findFood(foodId), "");
    }

}
