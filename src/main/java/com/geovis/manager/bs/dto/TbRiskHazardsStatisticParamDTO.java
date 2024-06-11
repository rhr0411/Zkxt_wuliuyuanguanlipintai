package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 风险隐患数量统计
 *
 * @author zengds
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbRiskHazardsStatisticParamDTO", description = "风险隐患数量统计")
public class TbRiskHazardsStatisticParamDTO implements Serializable {

    @ApiModelProperty("年度")
    @NotNull(message = "年度不能为空")
    private Integer year;

    @ApiModelProperty("企业ID")
    private String enterpriseId;
}
