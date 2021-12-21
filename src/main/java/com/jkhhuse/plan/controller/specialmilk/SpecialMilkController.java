package com.jkhhuse.plan.controller.specialmilk;

import com.jkhhuse.plan.common.CommonResponse;
import com.jkhhuse.plan.service.specialmilk.SpecialMilkService;
import com.jkhhuse.plan.vo.specialmilk.SpecialMilkVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Api("desc of class")
@RestController
@RequestMapping("/smilk")
public class SpecialMilkController {

    @Resource
    private SpecialMilkService specialMilkService;

    @ApiOperation(value = "查询特奶类型", notes = "根据类型获得数据信息")
    @GetMapping(value = "/find/{type}")
    CommonResponse<SpecialMilkVO> findType(
            @ApiParam(value = "特奶类型标识", required = true) @Valid @PathVariable Integer type) {
        return new CommonResponse("200", specialMilkService.findSpecialMilk(type), "");
    }

}
