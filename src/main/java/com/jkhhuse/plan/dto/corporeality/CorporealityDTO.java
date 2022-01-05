package com.jkhhuse.plan.dto.corporeality;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@ApiModel(description = "Corporeality Dimension DTO")
public class CorporealityDTO {

    @ApiModelProperty(value = "uuid", required = false)
    private String uuid;

    @ApiModelProperty(value = "recordTime", required = true)
    @NotBlank(message = "测量时间不能为空")
    private String recordTime;

    @ApiModelProperty(value = "weight")
    private Float weight;

    @ApiModelProperty(value = "height")
    private Float height;

}
