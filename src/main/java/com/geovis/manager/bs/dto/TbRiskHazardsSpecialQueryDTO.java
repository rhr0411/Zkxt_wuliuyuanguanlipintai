package com.geovis.manager.bs.dto;

import com.geovis.common.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 风险隐患管理_特殊作业报备查询
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbRiskHazardsSpecialQueryDTO", description = "风险隐患管理_特殊作业报备查询")
public class TbRiskHazardsSpecialQueryDTO extends BaseEntity {

    @ApiModelProperty("作业类型：1-动火作业 2-受限空间作业 3-盲板抽堵作业 4-高处作业 5-吊装作业 6-临时用电作业 7-动图作业 8-断路作业")
    private String taskType;

    @ApiModelProperty("作业状态：1-待施工 2-施工中 3-已验收")
    private String taskStatus;

    @ApiModelProperty("企业ID")
    private String enterpriseId;


}
