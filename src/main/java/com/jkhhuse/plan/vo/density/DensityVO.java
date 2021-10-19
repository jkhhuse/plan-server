package com.jkhhuse.plan.vo.density;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel(description = "Density 实体")
public class DensityVO {

    @ApiModelProperty(value = "personUuid", required = true)
    private String personUuid;

    @ApiModelProperty(value = "measureTime", required = true)
    private String measureTime;

    @ApiModelProperty(value = "measureValue", required = true)
    private Float measureValue;
}
