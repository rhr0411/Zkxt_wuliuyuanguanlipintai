package com.geovis.manager.bs.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 评价评估报告表更新或保存
 *
 * @author zengds
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbEqpSaveOrUpdateDTO", description = "评价评估报告表更新或保存")
public class TbEvaluateReportSaveOrUpdateDTO implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("报告编号")
    @NotEmpty(message = "报告编号不能为空")
    private String reportCode;

    @ApiModelProperty("项目名称")
    @NotEmpty(message = "项目名称不能为空")
    private String projectName;

    @ApiModelProperty("报告类型：1-定性评估报告 2-定量评估报告 3-综合评估报告 4-定期评估报告 5-专项评估报告")
    @NotEmpty(message = "报告类型不能为空")
    private String reportType;

    @ApiModelProperty("评估时间")
    @NotNull(message = "评估时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date evaluateTime;

    @ApiModelProperty("危险源等级：1-一级 2-二级 3-三级 4-四级 0-非重大")
    @NotEmpty(message = "危险源等级不能为空")
    private String dangerLevel;

    @ApiModelProperty("企业ID")
    @NotEmpty(message = "企业ID不能为空")
    private String enterpriseId;

    @ApiModelProperty("评审意见")
    private String reviewComments;

    @ApiModelProperty("附件列表")
    private List<String> fileIdList;

}
