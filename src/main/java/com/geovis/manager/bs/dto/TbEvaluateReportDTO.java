package com.geovis.manager.bs.dto;

import com.geovis.manager.system.dto.SystemFileDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 评价评估报告表
 *
 * @author zengds
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbEvaluateReportDTO", description = "评价评估报告表")
public class TbEvaluateReportDTO implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("报告编号")
    private String reportCode;

    @ApiModelProperty("项目名称")
    private String projectName;

    @ApiModelProperty("报告类型：1-定性评估报告 2-定量评估报告 3-综合评估报告 4-定期评估报告 5-专项评估报告")
    private String reportType;

    @ApiModelProperty("评估时间")
    private LocalDateTime evaluateTime;

    @ApiModelProperty("危险源等级：1-一级 2-二级 3-三级 4-四级 0-非重大")
    private String dangerLevel;

    @ApiModelProperty("企业ID")
    private String enterpriseId;

    @ApiModelProperty("评审意见")
    private String reviewComments;

    @ApiModelProperty("附件列表")
    private List<SystemFileDTO> fileList;

}
