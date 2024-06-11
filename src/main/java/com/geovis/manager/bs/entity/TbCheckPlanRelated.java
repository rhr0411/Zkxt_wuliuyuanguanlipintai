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
 * 检查计划_企业关联表
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_check_plan_related")
@ApiModel(value = "TbCheckPlanRelated对象", description = "检查计划_企业关联表")
public class TbCheckPlanRelated extends BaseEntity {

    @ApiModelProperty("检查计划ID")
    private String checkPlanId;

    @ApiModelProperty("企业ID")
    private String enterpriseId;


}
