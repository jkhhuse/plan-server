package com.jkhhuse.plan.controller.person;

import com.jkhhuse.plan.common.CommonResponse;
import com.jkhhuse.plan.dto.person.PersonDTO;
import com.jkhhuse.plan.entity.person.PersonDO;
import com.jkhhuse.plan.service.person.PersonService;
import com.jkhhuse.plan.vo.person.PersonVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Api("desc of class")
@RestController
@RequestMapping("/person")
public class PersonController {

    @Resource
    private PersonService personService;

    @ApiOperation(value = "获得用户信息", notes = "获得一条记录")
    @GetMapping(value = "/findUserInfo")
    CommonResponse<PersonVO> getPerson(
            @ApiParam(name = "user_id", value = "用户id", required = true) @RequestHeader("user_id") String userId) {
        PersonDO person = personService.findPersonById(userId);
        person.setPaaswd("");
        return new CommonResponse("200", person, "");
    }

    @ApiOperation(value = "更新用户", notes = "更新")
    @PutMapping(value = "/update", consumes = "application/json")
    CommonResponse<String> updateCPerson(
            @ApiParam(name = "user_id", value = "用户id", required = true) @RequestHeader("user_id") String userId,
            @ApiParam(value = "用户信息", required = true) @Valid @RequestBody PersonDTO personDTO) {
        String message = "";
        try {
            message = personService.updatePerson(userId, personDTO);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new CommonResponse("200", "", message);
    }

    @ApiOperation(value = "更换用户的头像", notes = "更新")
    @PutMapping(value = "/addPictureLink")
    CommonResponse<String> updatePictureLink(
            @ApiParam(name = "user_id", value = "用户id", required = true) @RequestHeader("user_id") String userId,
            @ApiParam(value = "用户信息", required = true) @Valid @RequestParam String pictureLink) {
        String result = personService.addPictureLink(userId, pictureLink);
        return new CommonResponse("200", result, "");
    }

    @ApiOperation(value = "获得当前用户的头像信息", notes = "获得一条记录")
    @GetMapping(value = "/getPictureLink")
    CommonResponse<PersonVO> getUserPictureLink(
            @ApiParam(name = "user_id", value = "用户id", required = true) @RequestHeader("user_id") String userId) {
        String link = personService.getPictureLink(userId);
        return new CommonResponse("200", link, "");
    }

}
