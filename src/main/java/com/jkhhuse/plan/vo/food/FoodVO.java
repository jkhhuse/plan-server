package com.jkhhuse.plan.vo.food;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel(description = "Food 实体")
public class FoodVO {

    @ApiModelProperty(value = "uuid", required = true)
    private String uuid;

    @ApiModelProperty(value = "name", required = true)
    private String name;

    @ApiModelProperty(value = "protein", required = true)
    private String protein;

    @ApiModelProperty(value = "phe", required = true)
    private String phe;

    @ApiModelProperty(value = "rule", required = true)
    private Integer rule;

    @ApiModelProperty(value = "type", required = true)
    private String type;

}
