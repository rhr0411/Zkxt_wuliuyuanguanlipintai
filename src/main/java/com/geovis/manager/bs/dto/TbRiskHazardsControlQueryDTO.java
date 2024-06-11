package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 风险隐患管理_风险管控查询
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbRiskHazardsControlQueryDTO", description = "风险隐患管理_风险管控查询")
public class TbRiskHazardsControlQueryDTO implements Serializable {

    @ApiModelProperty("风险状态:1-管控中 0-已解除")
    private String dangerStatus;

    @ApiModelProperty("企业ID")
    private String enterpriseId;

}
