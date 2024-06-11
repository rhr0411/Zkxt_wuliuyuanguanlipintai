package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 数量统计
 *
 * @author zengds
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "StatisticDTO", description = "数量统计")
public class StatisticDTO implements Serializable {

    @ApiModelProperty("维度")
    private String key;

    @ApiModelProperty("值")
    private String text;

    @ApiModelProperty("数量")
    private Integer count = 0;

}
