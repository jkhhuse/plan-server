package com.jkhhuse.plan.controller.density;

import com.jkhhuse.plan.common.CommonResponse;
import com.jkhhuse.plan.dto.density.DensityDTO;
import com.jkhhuse.plan.dto.density.DensityDimensionDTO;
import com.jkhhuse.plan.service.density.DensityService;
import com.jkhhuse.plan.vo.density.DensityVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Api("desc of class")
@RestController
@RequestMapping("/density")
public class DensityController {

    @Resource
    private DensityService densityService;

    @ApiOperation(value = "新增血值数据", notes = "新增一条记录")
    @PostMapping(value = "/add", consumes = "application/json")
    CommonResponse<List<DensityVO>> addDensity(
            @RequestHeader("userId") String userId,
            @ApiParam(value = "血值信息", required = true) @Valid @RequestBody DensityDTO densityDTO) {
        String message = "";
        try {
            message = densityService.addDensity(userId, densityDTO);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new CommonResponse("200", "", message);
    }

    @ApiOperation(value = "删除血值数据", notes = "删除一条记录")
    @DeleteMapping(value = "/delete", consumes = "application/json")
    CommonResponse<List<DensityVO>> deleteDensity(
            @ApiParam(value = "血值信息", required = true) @Valid @PathVariable String densityUuid) {
        try {
            densityService.deleteDensity(densityUuid);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new CommonResponse("200", "", "删除成功");
    }

    @ApiOperation(value = "判断血值数据是否存在重复", notes = "查询值范围")
    @PostMapping(value = "/measure/range")
    CommonResponse<List<DensityVO>> isDensityDuplicate(
            @ApiParam(value = "采血开始时间", required = true) @Valid @RequestParam String startTime,
            @ApiParam(value = "采血结束时间", required = true) @Valid @RequestParam String endTime) {
        int count = 0;
        try {
            count = densityService.countMeasureDuplicate(startTime, endTime);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new CommonResponse("200", count, "");
    }

    @ApiOperation(value = "根据时间段获取血值的情况", notes = "血值情况查询")
    @PostMapping(value = "/measure/dimension")
    CommonResponse<List<DensityVO>> searchDensitySet(
            @ApiParam(value = "采血开始时间", required = true) @Valid @RequestParam String startTime,
            @ApiParam(value = "采血结束时间", required = true) @Valid @RequestParam String endTime) {
        List<DensityDimensionDTO> list = new ArrayList<>();
        list = densityService.getDensitySet(startTime, endTime);
        return new CommonResponse("200", list, "");
    }

    @ApiOperation(value = "新增血值数据", notes = "新增一条记录")
    @PostMapping(value = "/update/:densityId", consumes = "application/json")
    CommonResponse<List<DensityVO>> updateDensity(
            @ApiParam(value = "血值信息", required = true) @Valid @RequestBody DensityDTO densityDTO,
            @ApiParam(value = "血值数据ID", required = true) @Valid @PathVariable String densityId) {
        String message = "";
        try {
            message = densityService.updateDensity(densityDTO, densityId);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new CommonResponse("200", "", message);
    }

}
