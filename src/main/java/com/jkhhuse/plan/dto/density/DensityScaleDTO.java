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
@ApiModel(description = "Density 比例")
public class DensityScaleDTO {

    @ApiModelProperty(value = "name", required = true)
    @NotBlank(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value = "value", required = true)
    @NotNull(message = "值不能为空")
    private Integer value;
}
