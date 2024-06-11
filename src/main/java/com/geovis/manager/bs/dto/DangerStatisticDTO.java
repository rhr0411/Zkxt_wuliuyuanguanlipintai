package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 危险等级数量统计
 *
 * @author zengds
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "DangerStatisticDTO", description = "危险等级数量统计")
public class DangerStatisticDTO implements Serializable {

    @ApiModelProperty("危险等级")
    private String dangerLevel;

    @ApiModelProperty("数量")
    private Integer count = 0;
}
