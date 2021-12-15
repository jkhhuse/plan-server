package com.jkhhuse.plan.dto.diet;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
@ApiModel(description = "Diet DTO")
public class DietDTO {

    @ApiModelProperty(value = "uuid", required = false)
    private String uuid;

    @ApiModelProperty(value = "dietTime", required = true)
    @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}$", message = "饮食时间格式不正确")
    @NotBlank(message = "饮食时间不能为空")
    private String dietTime;

    @ApiModelProperty(value = "pheValue", required = true)
    @NotNull(message = "苯摄入量不可为空")
    private Float pheValue;

    @ApiModelProperty(value = "dietType", required = true)
    @NotNull(message = "饮食类型不可为空")
    private Integer dietType;

    @ApiModelProperty(value = "dietContent", required = true)
    private String dietContent;

    @ApiModelProperty(value = "specialMilk", required = false)
    private Integer specialMilk;

    @ApiModelProperty(value = "breastMilk", required = false)
    private Integer breastMilk;
}
