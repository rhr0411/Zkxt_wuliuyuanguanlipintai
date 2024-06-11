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
 * 风险隐患管理_排查标准
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_risk_hazards_std")
@ApiModel(value = "TbRiskHazardsStd对象", description = "风险隐患管理_排查标准")
public class TbRiskHazardsStd extends BaseEntity {

    @ApiModelProperty("父类ID")
    private String parentId;

    @ApiModelProperty("标准名称")
    private String name;

    @ApiModelProperty("备注")
    private String remark;

}
