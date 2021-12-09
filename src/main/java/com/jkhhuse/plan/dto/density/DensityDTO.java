package com.jkhhuse.plan.dto.density;

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
@ApiModel(description = "Density DTO")
public class DensityDTO {

    @ApiModelProperty(value = "measureTime", required = true)
    @NotBlank(message = "测量时间不能为空")
    private String measureTime;

    @ApiModelProperty(value = "measureValue", required = true)
    @NotNull(message = "测量值不能为空")
    private Float measureValue;
}
