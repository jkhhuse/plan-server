package com.jkhhuse.plan.vo.statistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel(description = "Statistics 实体")
public class StatisticsVO {

    @ApiModelProperty(value = "specialProtein", required = true)
    private Float specialProtein;

    @ApiModelProperty(value = "natureProtein", required = true)
    private Float natureProtein;

    @ApiModelProperty(value = "totalProtein", required = true)
    private Float totalProtein;

    @ApiModelProperty(value = "pheValue", required = true)
    private Float pheValue;
}
