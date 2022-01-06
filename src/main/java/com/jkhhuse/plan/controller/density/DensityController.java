package com.jkhhuse.plan.controller.density;

import com.jkhhuse.plan.common.CommonResponse;
import com.jkhhuse.plan.dto.density.DensityDTO;
import com.jkhhuse.plan.dto.density.DensityDimensionDTO;
import com.jkhhuse.plan.dto.density.DensityScaleDTO;
import com.jkhhuse.plan.service.density.DensityService;
import com.jkhhuse.plan.vo.density.DensityScaleVO;
import com.jkhhuse.plan.vo.density.DensityVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
            @ApiParam(name = "user_id", value = "用户id", required = true) @RequestHeader("user_id") String userId,
            @ApiParam(value = "血值信息", required = true) @Valid @RequestBody DensityDTO densityDTO) {
        String uuid = "";
        try {
            uuid = densityService.addDensity(userId, densityDTO);
        } catch (Exception e) {
            System.out.println(e);
            return new CommonResponse("500", uuid, e.getMessage());
        }
        return new CommonResponse("200", uuid, "创建成功");
    }

    @ApiOperation(value = "删除血值数据", notes = "删除一条记录")
    @DeleteMapping(value = "/delete/{densityUuid}")
    CommonResponse<List<DensityVO>> deleteDensity(
            @ApiParam(value = "血值记录ID", required = true) @Valid @PathVariable String densityUuid) {
        Boolean isSuccess = true;
        try {
            isSuccess = densityService.deleteDensity(densityUuid);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new CommonResponse("200", isSuccess, isSuccess ? "删除成功" : "删除失败");
    }

    @ApiOperation(value = "判断血值数据是否存在重复", notes = "判断是否重复")
    @PostMapping(value = "/duplicate/{measureTime}")
    CommonResponse<Boolean> isDensityDuplicate(
            @ApiParam(name = "user_id", value = "用户id", required = true) @RequestHeader("user_id") String userId,
            @ApiParam(value = "采血开始时间", required = true) @Valid @RequestParam String measureTime) {
        boolean isExist = false;
        try {
            isExist = densityService.countMeasureDuplicate(userId, measureTime);
        } catch (ParseException e) {
            System.out.println(e);
        }
        return new CommonResponse("200", isExist, "");
    }

    @ApiOperation(value = "根据时间段获取血值的情况", notes = "血值情况查询")
    @GetMapping(value = "/dimension/range")
    CommonResponse<List<DensityVO>> searchDensitySet(
            @ApiParam(name = "user_id", value = "用户id", required = true) @RequestHeader("user_id") String userId,
            @ApiParam(value = "采血开始时间", required = true) @Valid @RequestParam String startTime,
            @ApiParam(value = "采血结束时间", required = true) @Valid @RequestParam String endTime) {
        List<DensityDimensionDTO> list = new ArrayList<>();
        list = densityService.getDensitySet(userId, startTime, endTime);
        return new CommonResponse("200", list, "");
    }

    @ApiOperation(value = "修改血值数据", notes = "根据时间修改血值数据")
    @PutMapping(value = "/update/{densityUuId}", consumes = "application/json")
    CommonResponse<List<DensityVO>> updateDensity(
            @ApiParam(value = "血值信息", required = true) @Valid @RequestBody DensityDTO densityDTO,
            @ApiParam(value = "血值数据ID", required = true) @Valid @PathVariable String densityUuId) {
        String data = "";
        try {
            data = densityService.updateDensity(densityDTO, densityUuId);
        } catch (Exception e) {
            return new CommonResponse("500", data, "更新失败");
        }
        return new CommonResponse("200", data, "更新成功");
    }

    @ApiOperation(value = "全量获取血值数据", notes = "血值情况查询")
    @GetMapping(value = "/dimension/all")
    CommonResponse<List<DensityVO>> getAllDensitySet(
            @ApiParam(name = "user_id", value = "用户id", required = true) @RequestHeader("user_id") String userId
    ) {
        List<DensityDTO> list = new ArrayList<>();
        list = densityService.getAllDensity(userId);
        return new CommonResponse("200", list, "");
    }

    @ApiOperation(value = "获取最近 N 次血值数据", notes = "血值情况查询")
    @GetMapping(value = "/dimension/{count}")
    CommonResponse<List<DensityVO>> getTopCountDensityList(
            @ApiParam(name = "user_id", value = "用户id", required = true) @RequestHeader("user_id") String userId,
            @ApiParam(value = "topN", required = true) @Valid @PathVariable Integer count
    ) {
        List<DensityDTO> list = new ArrayList<>();
        list = densityService.getTopDensity(userId, count);
        return new CommonResponse("200", list, "");
    }

    @ApiOperation(value = "获取血值健康度比例", notes = "血值情况查询")
    @GetMapping(value = "/dimension/scale")
    CommonResponse<List<DensityScaleVO>> getScaleDensityList(
            @ApiParam(name = "user_id", value = "用户id", required = true) @RequestHeader("user_id") String userId) {
        List<DensityScaleDTO> list = densityService.countRangeDensity(userId);
        return new CommonResponse("200", list, "");
    }

    @ApiOperation(value = "获得最近测量血值时间", notes = "获得measureTime")
    @GetMapping(value = "/latest")
    CommonResponse<Date> getLatestMeasureTime(
            @ApiParam(name = "user_id", value = "用户id", required = true) @RequestHeader("user_id") String userId) {
        Date measureTime = densityService.getLatestMeasureTime(userId);
        return new CommonResponse("200", measureTime, "");
    }

}
