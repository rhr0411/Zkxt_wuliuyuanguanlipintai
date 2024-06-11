package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 风险隐患管理_隐患查询
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbRiskHazardsQueryDTO", description = "风险隐患管理_隐患查询")
public class TbRiskHazardsQueryDTO implements Serializable {

    @ApiModelProperty("督办整改状态：1-待复查 2-待整改 3-逾期未整改 4-已完成 5-延期整改中")
    private String supervisionStatus;

    @ApiModelProperty("所属行业：1-生产制造业 2-储存与批发零售 3-运输业 4-使用与处置")
    private String industry;

    @ApiModelProperty("隐患级别：1-一般隐患 2-重大隐患")
    private String level;

    @ApiModelProperty("上报处理状态：0-待处理 1-已处理")
    private String handleStatus;

    @ApiModelProperty("企业ID")
    private String enterpriseId;

    @ApiModelProperty("上报开始时间")
    private LocalDateTime reportTimeStart;

    @ApiModelProperty("上报结束时间")
    private LocalDateTime reportTimeEnd;

    @ApiModelProperty("上报时间：year-年、month-月、week-周")
    private String reportTimeTypeKey;

    @ApiModelProperty("上报时间值")
    private String reportTimeTypeValue;

}
