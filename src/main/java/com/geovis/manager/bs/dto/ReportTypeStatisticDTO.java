package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 报告类型数量统计
 *
 * @author zengds
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "ReportTypeStatisticDTO", description = "报告类型数量统计")
public class ReportTypeStatisticDTO implements Serializable {

    @ApiModelProperty("报告类型")
    private String reportType;

    @ApiModelProperty("数量")
    private Integer count = 0;
}
