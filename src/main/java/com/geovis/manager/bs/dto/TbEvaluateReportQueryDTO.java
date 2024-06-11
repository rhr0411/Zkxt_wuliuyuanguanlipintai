package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 评价评估报告表查询
 *
 * @author zengds
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbEvaluateReportQueryDTO", description = "评价评估报告表查询")
public class TbEvaluateReportQueryDTO implements Serializable {

    @ApiModelProperty("企业ID")
    @NotEmpty(message = "企业ID不能为空")
    private String enterpriseId;

    @ApiModelProperty("报告类型：1-定性评估报告 2-定量评估报告 3-综合评估报告 4-定期评估报告 5-专项评估报告")
    private String reportType;

    @ApiModelProperty("评估时间-开始时间")
    private LocalDateTime evaluateTimeStart;

    @ApiModelProperty("评估时间-结束时间")
    private LocalDateTime evaluateTimeEnd;

    @ApiModelProperty("危险源等级：1-一级 2-二级 3-三级 4-四级 0-非重大")
    private String dangerLevel;


}
