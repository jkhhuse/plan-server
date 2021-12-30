package com.jkhhuse.plan.vo.specialmilk;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@ApiModel(description = "SpecialMilk 实体")
public class SpecialMilkVO {

    @ApiModelProperty(value = "uuid", required = true)
    private String uuid;

    @ApiModelProperty(value = "name", required = true)
    private String name;

    @ApiModelProperty(value = "type", required = true)
    private Integer type;

    @ApiModelProperty(value = "protein", required = true)
    private Float protein;

    @ApiModelProperty(value = "content")
    private String content;
}
