package com.jkhhuse.plan.vo.density;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel(description = "Density Scale 实体")
public class DensityScaleVO {

    @ApiModelProperty(value = "name", required = true)
    private String name;

    @ApiModelProperty(value = "value", required = true)
    private Float value;
}
