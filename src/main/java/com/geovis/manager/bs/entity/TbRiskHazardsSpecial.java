package com.geovis.manager.bs.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.geovis.common.core.entity.BaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 风险隐患管理_特殊作业报备
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_risk_hazards_special")
@ApiModel(value = "TbRiskHazardsSpecial对象", description = "风险隐患管理_特殊作业报备")
public class TbRiskHazardsSpecial extends BaseEntity {

    @ApiModelProperty("作业单位")
    private String taskUnit;

    @ApiModelProperty("作业类型：1-动火作业 2-受限空间作业 3-盲板抽堵作业 4-高处作业 5-吊装作业 6-临时用电作业 7-动图作业 8-断路作业")
    private String taskType;

    @ApiModelProperty("作业状态：1-待施工 2-施工中 3-已验收")
    private String taskStatus;

    @ApiModelProperty("经度")
    private Double longitude;

    @ApiModelProperty("纬度")
    private Double latitude;

    @ApiModelProperty("上报时间")
    private LocalDateTime reportTime;

    @ApiModelProperty("实施时间")
    private LocalDateTime handleTime;

    @ApiModelProperty("作业负责人")
    private String taskMaster;

    @ApiModelProperty("验收人")
    private String acceptance;

    @ApiModelProperty("现场作业人员")
    private String sceneMaster;

    @ApiModelProperty("安全措施")
    private String handleDesc;

    @ApiModelProperty("企业ID")
    private String enterpriseId;


}
