package com.geovis.manager.bs.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.geovis.common.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 风险隐患管理_排查标准_明细内容
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_risk_hazards_std_detail")
@ApiModel(value = "TbRiskHazardsStdDetail对象", description = "风险隐患管理_排查标准_明细内容")
public class TbRiskHazardsStdDetail extends BaseEntity {

    @ApiModelProperty("标准ID")
    private String stdId;

    @ApiModelProperty("标准内容")
    private String content;

    @ApiModelProperty("检查依据")
    private String checkDependent;


}
