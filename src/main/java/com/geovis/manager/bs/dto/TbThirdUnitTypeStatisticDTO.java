package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 第三方单位类型统计
 *
 * @author zengds
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbThirdUnitTypeStatisticDTO", description = "第三方单位类型统计")
public class TbThirdUnitTypeStatisticDTO implements Serializable {

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("数量")
    private Integer count;

}
