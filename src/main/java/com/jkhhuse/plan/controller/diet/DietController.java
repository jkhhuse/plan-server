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
import java.text.ParseException;
import java.util.ArrayList;
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
            return new CommonResponse("400", uuid, "");
        }
        return new CommonResponse("200", uuid, "");
    }

    @ApiOperation(value = "修改饮食记录", notes = "修改一条记录")
    @PutMapping(value = "/update/{dietId}", consumes = "application/json")
    CommonResponse<DietVO> updateDiet(
            @ApiParam(value = "饮食记录ID", required = true) @Valid @PathVariable String dietId,
            @ApiParam(value = "血值信息", required = true) @Valid @RequestBody DietDTO dietDTO) {
        try {
            dietService.updateDiet(dietId, dietDTO);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new CommonResponse("200", "", "");
    }

    @ApiOperation(value = "删除饮食记录", notes = "删除一条记录")
    @DeleteMapping(value = "/delete/{dietId}")
    CommonResponse<DietVO> deleteDiet(
            @ApiParam(value = "饮食记录ID", required = true) @Valid @PathVariable String dietId) {
        try {
            dietService.deleteDiet(dietId);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new CommonResponse("200", "", "删除成功");
    }

    @ApiOperation(value = "根据日期查询当日的所有饮食记录", notes = "获得指定日期的饮食记录")
    @GetMapping(value = "/list/{date}")
    CommonResponse<List<DietVO>> showSelectedDiet(
            @ApiParam(value = "选择要查看的日期", required = true) @Valid @PathVariable String date) {
        List<DietVO> list = new ArrayList<>();
        try {
            list = dietService.findFixedDateDiets(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new CommonResponse("200", list, "数据获取成功");
    }

}
