package com.jkhhuse.plan.dto.statistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@ApiModel(description = "Statistics DTO")
public class StatisticsDTO {

    @ApiModelProperty(value = "personUuid", required = true)
    @NotBlank(message = "personUuid 不能为空")
    private String personUuid;

    @ApiModelProperty(value = "specialProtein", required = true)
    @NotBlank(message = "特殊蛋白质含量不能为空")
    private Float specialProtein;

    @ApiModelProperty(value = "specialProteinMl", required = true)
    @NotBlank(message = "特殊蛋白质毫升不能为空")
    private Float specialProteinMl;

    @ApiModelProperty(value = "natureProtein", required = true)
    @NotBlank(message = "天然蛋白质含量不能为空")
    private Float natureProtein;

    @ApiModelProperty(value = "totalProtein", required = true)
    @NotBlank(message = "总蛋白质含量不能为空")
    private Float totalProtein;

    @ApiModelProperty(value = "pheValue", required = true)
    @NotBlank(message = "苯丙氨酸不能为空")
    private Float pheValue;
}