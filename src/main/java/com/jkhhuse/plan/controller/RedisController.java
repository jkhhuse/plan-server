package com.jkhhuse.plan.controller;

import com.jkhhuse.plan.common.CommonResponse;
import com.jkhhuse.plan.service.redis.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Api("desc of class")
@RestController
@RequestMapping("/redisApi")
public class RedisController {

  @Resource
  private RedisService redisService;

  @ApiOperation(value = "set", notes = "插入值")
  @PutMapping(value = "/redis/set/{value}")
  CommonResponse<String> getValue(
          @ApiParam(value = "客户信息", required = true) @Valid @PathVariable String value) {
    redisService.set("value", "value");
    Object v = redisService.get(value);
    return new CommonResponse("200", v, value);
  }
}
