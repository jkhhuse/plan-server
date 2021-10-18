package com.jkhhuse.plan.controller.person;

import com.jkhhuse.plan.common.CommonResponse;
import com.jkhhuse.plan.dto.person.PersonDTO;
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

  @ApiOperation(value = "新增用户", notes = "新增一条记录")
  @PostMapping(value = "/add", consumes = "application/json")
  CommonResponse<List<PersonVO>> addCustomer(
          @ApiParam(value = "用户信息", required = true) @Valid @RequestBody PersonDTO personDTO) {
    String message = "";
    try {
      message = personService.addPerson(personDTO);
    } catch (Exception e) {
      System.out.println(e);
    }
    return new CommonResponse("200", "", message);
  }
}
