package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 第三方单位等级统计
 *
 * @author zengds
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbThirdUnitLevelStatisticDTO", description = "第三方单位等级统计")
public class TbThirdUnitLevelStatisticDTO implements Serializable {

    @ApiModelProperty("等级")
    private String level;

    @ApiModelProperty("数量")
    private Integer count;

}
