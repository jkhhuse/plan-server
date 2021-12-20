package com.jkhhuse.plan.dto.specialmilk;

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
@ApiModel(description = "SpecialMilk DTO")
public class SpecialMilkDTO {

    @ApiModelProperty(value = "name", required = true)
    @NotBlank(message = "奶粉名称不能为空")
    private String name;

    @ApiModelProperty(value = "type", required = true)
    @NotBlank(message = "奶粉类型不能为空")
    private Integer type;

    @ApiModelProperty(value = "protein", required = true)
    @NotBlank(message = "蛋白质含量不能为空")
    private Float protein;

    @ApiModelProperty(value = "content", required = false)
    private String content;
}
