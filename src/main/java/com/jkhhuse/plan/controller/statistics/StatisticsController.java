package com.jkhhuse.plan.controller.statistics;

import com.jkhhuse.plan.common.CommonResponse;
import com.jkhhuse.plan.dto.statistics.StatisticsDTO;
import com.jkhhuse.plan.service.statistics.StatisticsService;
import com.jkhhuse.plan.vo.specialmilk.SpecialMilkVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Api("desc of class")
@RestController
@RequestMapping("/stats")
public class StatisticsController {

    @Resource
    private StatisticsService statisticsService;

    @ApiOperation(value = "获得最近N天数据", notes = "根据最近N天获得饮食统计数据信息")
    @PostMapping(value = "/latest")
    CommonResponse<SpecialMilkVO> getLatestStats(
            @ApiParam(value = "开始时间", required = true) @Valid @PathVariable String startDate,
            @ApiParam(value = "截止时间", required = true) @Valid @PathVariable String endDate,
            @ApiParam(value = "天数", required = true) @Valid @PathVariable Integer days
            ) {
        StatisticsDTO statisticsDTO = statisticsService.latestDayStats(startDate, endDate, days);
        return new CommonResponse("200", statisticsDTO, "");
    }
}
