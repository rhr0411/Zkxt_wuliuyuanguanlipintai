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
 * 评价评估报告表
 * </p>
 *
 * @author zengds
 * @since 2024-04-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_evaluate_report")
@ApiModel(value = "TbEvaluateReport对象", description = "评价评估报告表")
public class TbEvaluateReport extends BaseEntity {

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


}
