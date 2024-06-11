package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * <p>
 * 风险隐患管理_特殊作业报备变更状态
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbRiskHazardsSpecialChangeStatusDTO", description = "风险隐患管理_特殊作业报备变更状态")
public class TbRiskHazardsSpecialChangeStatusDTO implements Serializable {

    @ApiModelProperty("主键ID")
    @NotEmpty(message = "主键ID不能为空")
    private String id;

    @ApiModelProperty("作业状态：1-待施工 2-施工中 3-已验收")
    @NotEmpty(message = "作业状态不能为空")
    private String taskStatus;

}
