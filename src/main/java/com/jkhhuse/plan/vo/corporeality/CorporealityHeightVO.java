package com.jkhhuse.plan.vo.corporeality;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@ApiModel(description = "CorporealityHeight 实体")
public class CorporealityHeightVO {

    @ApiModelProperty(value = "recordTime", required = true)
    @NotBlank(message = "测量时间不能为空")
    private String recordTime;

    @ApiModelProperty(value = "height", required = true)
    private Float height;

}
