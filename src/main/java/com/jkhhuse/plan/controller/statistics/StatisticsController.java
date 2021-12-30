package com.jkhhuse.plan.controller.statistics;

import com.jkhhuse.plan.common.CommonResponse;
import com.jkhhuse.plan.service.diet.DietService;
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
@RequestMapping("/stats")
public class StatisticsController {

    @Resource
    private

    @ApiOperation(value = "获得最近N天数据", notes = "根据最近N天获得饮食统计数据信息")
    @GetMapping(value = "/latest/{day}")
    CommonResponse<SpecialMilkVO> getLatestStats(
            @ApiParam(value = "特奶类型标识", required = true) @Valid @PathVariable Integer day) {
        return new CommonResponse("200", "", "");
    }
}
