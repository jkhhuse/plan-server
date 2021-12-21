package com.jkhhuse.plan.vo.specialmilk;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@ApiModel(description = "SpecialMilk 实体")
public class SpecialMilkVO {

    @ApiModelProperty(value = "uuid", required = true)
    private String uuid;

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
