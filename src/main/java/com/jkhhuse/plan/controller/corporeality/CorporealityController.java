package com.jkhhuse.plan.controller.corporeality;

import com.jkhhuse.plan.common.CommonResponse;
import com.jkhhuse.plan.dto.corporeality.CorporealityDTO;
import com.jkhhuse.plan.service.corporeality.CorporealityService;
import com.jkhhuse.plan.vo.corporeality.CorporealityHeightVO;
import com.jkhhuse.plan.vo.corporeality.CorporealityVO;
import com.jkhhuse.plan.vo.corporeality.CorporealityWeightVO;
import com.jkhhuse.plan.vo.density.DensityVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Api("desc of class")
@RestController
@RequestMapping("/corporeality")
public class CorporealityController {

    @Resource
    CorporealityService corporealityService;

    @ApiOperation(value = "增加体重与身高指标信息", notes = "增加身体指标")
    @PostMapping(value = "/add", consumes = "application/json")
    CommonResponse<Float> addCorporeality(
            @ApiParam(name = "user_id", value = "用户id", required = true) @RequestHeader("user_id") String userId,
            @ApiParam(value = "血值信息", required = true) @Valid @RequestBody CorporealityDTO corporealityDTO) {
        String uuid = null;
        try {
            uuid = corporealityService.addCorporeality(userId, corporealityDTO);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new CommonResponse("200", uuid, "创建成功");
    }

    @ApiOperation(value = "获得最新的体重信息（kg）", notes = "最近一次体重记录")
    @GetMapping(value = "/weight/latest")
    CommonResponse<Float> getLatestWeight(
            @ApiParam(name = "user_id", value = "用户id", required = true) @RequestHeader("user_id") String userId) {
        Float weight = corporealityService.getLatestWeight(userId);
        return new CommonResponse("200", weight, "");
    }

    @ApiOperation(value = "获得体重曲线", notes = "体重曲线")
    @GetMapping(value = "/weight/visual")
    CommonResponse<List<CorporealityVO>> weightVisual(
            @ApiParam(name = "user_id", value = "用户id", required = true) @RequestHeader("user_id") String userId) {
        List<CorporealityDTO> list = corporealityService.getWeightVisual(userId);
        Iterator<CorporealityDTO> it = list.listIterator();
        List<CorporealityWeightVO> wList = new ArrayList<>();
        while (it.hasNext()) {
            CorporealityDTO corporealityDTO = it.next();
            CorporealityWeightVO corporealityWeightVO = new CorporealityWeightVO();
            corporealityWeightVO.setWeight(corporealityDTO.getWeight());
            corporealityWeightVO.setRecordTime(corporealityDTO.getRecordTime());
            wList.add(corporealityWeightVO);
        }
        return new CommonResponse("200", list, "");
    }

    @ApiOperation(value = "获得身高曲线", notes = "身高曲线")
    @GetMapping(value = "/height/visual")
    CommonResponse<List<CorporealityVO>> heightVisual(
            @ApiParam(name = "user_id", value = "用户id", required = true) @RequestHeader("user_id") String userId) {
        List<CorporealityDTO> list = corporealityService.getHeightVisual(userId);
        Iterator<CorporealityDTO> it = list.listIterator();
        List<CorporealityHeightVO> hList = new ArrayList<>();
        while (it.hasNext()) {
            CorporealityDTO corporealityDTO = it.next();
            CorporealityHeightVO corporealityHeightVO = new CorporealityHeightVO();
            corporealityHeightVO.setHeight(corporealityDTO.getWeight());
            corporealityHeightVO.setRecordTime(corporealityDTO.getRecordTime());
            hList.add(corporealityHeightVO);
        }
        return new CommonResponse("200", list, "");
    }
}
