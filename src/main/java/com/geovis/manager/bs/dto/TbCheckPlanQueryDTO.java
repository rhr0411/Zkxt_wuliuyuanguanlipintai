package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 检查计划查询
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbCheckPlanQueryDTO", description = "检查计划查询")
public class TbCheckPlanQueryDTO implements Serializable {

    @ApiModelProperty("检查类型：1-日常检查 2-专项检查 3-重要节假日活动 4-其他")
    private String type;

    @ApiModelProperty("检查周期_开始时间")
    private LocalDate checkStartDate;

    @ApiModelProperty("检查周期_结束时间")
    private LocalDate checkEndDate;

}
