package com.jkhhuse.plan.vo.diet;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel(description = "Diet 实体")
public class DietVO {

    @ApiModelProperty(value = "personUuid", required = false)
    private String personUuid;

    @ApiModelProperty(value = "uuid", required = true)
    private String uuid;

    @ApiModelProperty(value = "dietTime", required = true)
    private String dietTime;

    @ApiModelProperty(value = "pheValue", required = true)
    private Float pheValue;

    @ApiModelProperty(value = "dietType", required = true)
    private String dietType;

    @ApiModelProperty(value = "dietContent", required = true)
    private String dietContent;

    @ApiModelProperty(value = "specialMilk", required = false)
    private Float specialMilk;

    @ApiModelProperty(value = "breastMilk", required = false)
    private Float breastMilk;
}
