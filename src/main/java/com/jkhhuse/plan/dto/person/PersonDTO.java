package com.jkhhuse.plan.dto.person;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@ApiModel(description = "Person DTO")
public class PersonDTO {

    @ApiModelProperty(value = "name", required = true)
    @NotBlank(message = "昵称不能为空")
    private String name;

    @ApiModelProperty(value = "createTime", required = true)
    @NotBlank(message = "创建时间不能为空")
    private String createTime;

    @ApiModelProperty(value = "bornTime", required = true)
    @NotBlank(message = "出生日期不能为空")
    private String bornTime;

    @ApiModelProperty(value = "origin", required = true)
    @NotNull(message = "初始血值名称不能为空")
    private Float origin;

    @ApiModelProperty(value = "addr", required = true)
    @NotBlank(message = "地址不能为空")
    private String addr;

    @ApiModelProperty(value = "email", required = true)
    @NotBlank(message = "电子邮箱名称不能为空")
    private String email;

    @ApiModelProperty(value = "paaswd", required = true)
    @NotBlank(message = "密码不能为空")
    private String paaswd;
}
