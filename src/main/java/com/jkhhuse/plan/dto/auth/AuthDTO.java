package com.jkhhuse.plan.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@ApiModel(description = "Auth DTO")
public class AuthDTO {

    @ApiModelProperty(value = "username", required = true)
    @NotBlank(message = "username 不能为空")
    private String username;

    @ApiModelProperty(value = "passwd", required = true)
    @NotBlank(message = "passwd 不能为空")
    private String passwd;
}
