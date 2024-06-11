package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 装置开停车和大检修管理统计
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbEqpCheckStatisticDTO", description = "装置开停车和大检修管理统计")
public class TbEqpCheckStatisticDTO implements Serializable {

    @ApiModelProperty("项目总数")
    private Integer totalNum;

    @ApiModelProperty("实施中项目")
    private Integer doingNum;

    @ApiModelProperty("完成项目")
    private Integer completeNum;

    @ApiModelProperty("预警数量")
    private Integer waringNum;

}
