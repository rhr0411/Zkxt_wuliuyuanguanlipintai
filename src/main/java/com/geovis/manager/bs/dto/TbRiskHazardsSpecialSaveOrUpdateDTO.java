package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 风险隐患管理_特殊作业报备保存或更新
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbRiskHazardsSpecialSaveOrUpdateDTO", description = "风险隐患管理_特殊作业报备保存或更新")
public class TbRiskHazardsSpecialSaveOrUpdateDTO implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("作业单位")
    @NotEmpty(message = "作业单位不能为空")
    private String taskUnit;

    @ApiModelProperty("作业类型：1-动火作业 2-受限空间作业 3-盲板抽堵作业 4-高处作业 5-吊装作业 6-临时用电作业 7-动图作业 8-断路作业")
    @NotEmpty(message = "作业类型不能为空")
    private String taskType;

    @ApiModelProperty("经度")
    @NotNull(message = "经度不能为空")
    private Double longitude;

    @ApiModelProperty("纬度")
    @NotNull(message = "纬度不能为空")
    private Double latitude;

    @ApiModelProperty("上报时间")
    @NotNull(message = "上报时间不能为空")
    private LocalDateTime reportTime;

    @ApiModelProperty("实施时间")
    @NotNull(message = "实施时间不能为空")
    private LocalDateTime handleTime;

    @ApiModelProperty("作业负责人")
    @NotEmpty(message = "作业负责人不能为空")
    private String taskMaster;

    @ApiModelProperty("验收人")
    @NotEmpty(message = "验收人不能为空")
    private String acceptance;

    @ApiModelProperty("现场作业人员")
    @NotEmpty(message = "现场作业人员不能为空")
    private String sceneMaster;

    @ApiModelProperty("安全措施")
    @NotEmpty(message = "安全措施不能为空")
    private String handleDesc;

    @ApiModelProperty("企业ID")
    @NotEmpty(message = "企业ID不能为空")
    private String enterpriseId;


}
