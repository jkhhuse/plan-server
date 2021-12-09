package com.jkhhuse.plan.vo.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel(description = "AUTH 实体")
public class TokenVO {

    @ApiModelProperty(value = "userId", required = true)
    private String userId;

    @ApiModelProperty(value = "token", required = true)
    private String token;

    @ApiModelProperty(value = "userName", required = true)
    private String userName;
}
