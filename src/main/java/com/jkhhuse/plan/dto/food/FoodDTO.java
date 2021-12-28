package com.jkhhuse.plan.dto.food;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
@ApiModel(description = "Food DTO")
public class FoodDTO {

    @ApiModelProperty(value = "uuid", required = true)
    @NotBlank(message = "uuid不能为空")
    private String uuid;

    @ApiModelProperty(value = "name", required = true)
    @NotBlank(message = "食品名称不能为空")
    private String name;

    @ApiModelProperty(value = "protein", required = true)
    @NotBlank(message = "蛋白质含量不能为空")
    private String protein;

    @ApiModelProperty(value = "phe", required = true)
    @NotBlank(message = "苯丙氨酸含量不能为空")
    private String phe;

    @ApiModelProperty(value = "rule", required = true)
    @NotBlank(message = "红绿灯饮食规则不能为空")
    private String rule;

    @ApiModelProperty(value = "type", required = true)
    @NotBlank(message = "估算值类型不能为空")
    private String type;

}
