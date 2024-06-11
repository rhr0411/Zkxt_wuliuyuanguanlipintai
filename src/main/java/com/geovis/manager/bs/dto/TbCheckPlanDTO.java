package com.geovis.manager.bs.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.geovis.manager.bs.entity.TbCheckPlan;
import com.geovis.manager.bs.entity.TbEnterprise;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 检查计划
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_check_plan")
@ApiModel(value = "TbCheckPlan对象", description = "检查计划")
public class TbCheckPlanDTO extends TbCheckPlan {

    @ApiModelProperty("企业列表")
    private List<TbEnterprise> enterpriseList;

}
