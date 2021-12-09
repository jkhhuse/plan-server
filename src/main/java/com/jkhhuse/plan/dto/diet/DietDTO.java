package com.jkhhuse.plan.dto.diet;

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
@ApiModel(description = "Diet DTO")
public class DietDTO {

    @ApiModelProperty(value = "dietTime", required = true)
    @NotBlank(message = "饮食时间不能为空")
    private String dietTime;

    @ApiModelProperty(value = "pheValue", required = true)
    @NotNull(message = "苯摄入量不可为空")
    private Float pheValue;

    @ApiModelProperty(value = "dietType", required = true)
    private String dietType;

    @ApiModelProperty(value = "dietContent", required = true)
    private String dietContent;
}
