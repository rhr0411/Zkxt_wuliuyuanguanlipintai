package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 企业项目数量统计
 *
 * @author zengds
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "ProjectStatisticDTO", description = "企业项目数量统计")
public class ProjectStatisticDTO implements Serializable {

    @ApiModelProperty("项目总数")
    private Integer projectCount = 0;

    @ApiModelProperty("企业总数")
    private Integer enterpriseCount = 0;
}
