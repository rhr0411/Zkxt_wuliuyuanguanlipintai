package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 评审意见
 *
 * @author zengds
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbEvaluateReportReviewCommentsDTO", description = "评审意见")
public class TbEvaluateReportReviewCommentsDTO implements Serializable {

    @ApiModelProperty("主键ID")
    @NotEmpty(message = "主键ID不能为空")
    private String id;

    @ApiModelProperty("评审意见")
    @NotEmpty(message = "评审意见不能为空")
    private String reviewComments;

}
