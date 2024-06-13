package com.geovis.manager.bs.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.geovis.common.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

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
@ApiModel(value = "TbRiskHazardsStdTree", description = "风险隐患管理_排查标准树型目录")
public class TbRiskHazardsStdTree extends BaseEntity {

    @ApiModelProperty("父类ID")
    private String parentId;

    @ApiModelProperty("标准名称")
    private String name;

    @ApiModelProperty("子类列表")
    private List<TbRiskHazardsStdTree> tbRiskHazardsStdTrees;

}
