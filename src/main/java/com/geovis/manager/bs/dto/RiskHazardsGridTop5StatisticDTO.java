package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 隐患网格top5
 *
 * @author zengds
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "RiskHazardsGridTop5StatisticDTO", description = "隐患网格top5")
public class RiskHazardsGridTop5StatisticDTO implements Serializable {

    @ApiModelProperty("网格名称")
    private String gridName;

    @ApiModelProperty("企业数量")
    private Integer enterpriseNum = 0;

    @ApiModelProperty("重大隐患数")
    private Integer riskHazardsNum = 0;

}
