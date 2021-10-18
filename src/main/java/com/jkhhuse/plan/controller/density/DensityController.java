package com.jkhhuse.plan.controller.density;

import com.jkhhuse.plan.common.CommonResponse;
import com.jkhhuse.plan.dto.density.DensityDTO;
import com.jkhhuse.plan.service.density.DensityService;
import com.jkhhuse.plan.vo.density.DensityVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Api("desc of class")
@RestController
@RequestMapping("/density")
public class DensityController {

  @Resource
  private DensityService densityService;

  @ApiOperation(value = "新增血值数据", notes = "新增一条记录")
  @PostMapping(value = "/add", consumes = "application/json")
  CommonResponse<List<DensityVO>> addCustomer(
          @ApiParam(value = "血值信息", required = true) @Valid @RequestBody DensityDTO densityDTO,
          @ApiParam(value = "用户UUID", required = true) @Valid @PathVariable String person_uuid) {
    String message = "";
    try {
      message = densityService.addDensity(densityDTO, person_uuid);
    } catch (Exception e) {
      System.out.println(e);
    }
    return new CommonResponse("200", "", message);
  }

  @ApiOperation(value = "删除血值数据", notes = "删除一条记录")
  @PostMapping(value = "/delete", consumes = "application/json")
  CommonResponse<List<DensityVO>> addCustomer(
          @ApiParam(value = "血值信息", required = true) @Valid @RequestBody String density_uuid) {
    try {
      densityService.deleteDensity(density_uuid);
    } catch (Exception e) {
      System.out.println(e);
    }
    return new CommonResponse("200", "", "删除失败");
  }
}
