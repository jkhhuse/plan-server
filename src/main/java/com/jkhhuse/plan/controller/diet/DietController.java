package com.jkhhuse.plan.controller.diet;

import com.jkhhuse.plan.common.CommonResponse;
import com.jkhhuse.plan.dto.diet.DietDTO;
import com.jkhhuse.plan.service.diet.DietService;
import com.jkhhuse.plan.vo.diet.DietVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Api("desc of class")
@RestController
@RequestMapping("/diet")
public class DietController {

    @Resource
    private DietService dietService;

    @ApiOperation(value = "新增饮食记录", notes = "新增一条记录")
    @PostMapping(value = "/add", consumes = "application/json")
    CommonResponse<List<DietVO>> addDiet(
            @RequestHeader("userId") String userId,
            @ApiParam(value = "饮食记录", required = true) @Valid @RequestBody DietDTO dietDTO) {
        String uuid = "";
        try {
            uuid = dietService.addDiet(userId, dietDTO);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new CommonResponse("200", uuid, "");
    }

    @ApiOperation(value = "修改饮食记录", notes = "新增一条记录")
    @PutMapping(value = "/update/:dietId", consumes = "application/json")
    CommonResponse<List<DietVO>> updateDiet(
            @ApiParam(value = "饮食记录ID", required = true) @Valid @PathVariable String dietId,
            @ApiParam(value = "血值信息", required = true) @Valid @RequestBody DietDTO dietDTO) {
        try {
            dietService.updateDiet(dietId, dietDTO);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new CommonResponse("200", "", "");
    }





}
