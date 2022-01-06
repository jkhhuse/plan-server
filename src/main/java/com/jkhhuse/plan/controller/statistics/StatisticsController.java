package com.jkhhuse.plan.controller.statistics;

import com.jkhhuse.plan.common.CommonResponse;
import com.jkhhuse.plan.service.statistics.StatisticsService;
import com.jkhhuse.plan.vo.statistics.StatisticsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

@Api("desc of class")
@RestController
@RequestMapping("/stats")
public class StatisticsController {

    @Resource
    private StatisticsService statisticsService;

    @ApiOperation(value = "获得最近N天数据", notes = "根据最近N天获得饮食统计数据信息")
    @PostMapping(value = "/latest")
    CommonResponse<StatisticsVO> getLatestStats(
            @ApiParam(name = "user_id", value = "用户id", required = true) @RequestHeader("user_id") String userId,
            @ApiParam(value = "测量时间", required = true) @Valid @RequestParam String measureTime,
            @ApiParam(value = "天数", required = true) @Valid @RequestParam Integer days
            ) {
        StatisticsVO statisticsVO = new StatisticsVO();
        try {
            BeanUtils.copyProperties(statisticsVO, statisticsService.searchLatestStats(userId, measureTime, days));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (statisticsVO != null) {
            return new CommonResponse("200", statisticsVO, "");
        } else {
            return new CommonResponse("500", null, "数据获取失败");
        }
    }

    @ApiOperation(value = "保存最近测量的3/7天数据", notes = "根据最近3/7天获得饮食统计数据信息")
    @PostMapping(value = "/latest/save")
    CommonResponse<String> saveLatestStats(
            @ApiParam(name = "user_id", value = "用户id", required = true) @RequestHeader("user_id") String userId,
            @ApiParam(value = "开始时间", required = true) @Valid @RequestParam String measureTime
    ) {
        try {
            statisticsService.saveLatestDayStats(userId, measureTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new CommonResponse("200", "", "保存成功");
    }
}
