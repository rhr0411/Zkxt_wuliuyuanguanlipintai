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
 * 风险隐患管理_风险管控
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_risk_hazards_control")
@ApiModel(value = "TbRiskHazardsControl对象", description = "风险隐患管理_风险管控")
public class TbRiskHazardsControl extends BaseEntity {

    @ApiModelProperty("责任人名称")
    private String masterName;

    @ApiModelProperty("负责人联系电话")
    private String masterPhone;

    @ApiModelProperty("风险状态:1-管控中 0-已解除")
    private String dangerStatus;

    @ApiModelProperty("风险描述")
    private String dangerDesc;

    @ApiModelProperty("管控措施")
    private String handleDesc;

    @ApiModelProperty("风险日常检查表-文件ID")
    private String dailyCheckFileId;

    @ApiModelProperty("风险日常检查表-文件名称")
    private String dailyCheckFileName;

    @ApiModelProperty("是否有预警提醒：1-是 0-否")
    private String hasWaring;

    @ApiModelProperty("预警内容")
    private String waringContent;

    @ApiModelProperty("企业ID")
    private String enterpriseId;


}
