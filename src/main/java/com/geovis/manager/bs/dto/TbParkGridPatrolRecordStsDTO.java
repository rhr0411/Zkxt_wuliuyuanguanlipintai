package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 园区网格_巡检记录条数统计
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbParkGridPatrolRecordStsDTO", description = "园区网格_巡检记录条数统计")
public class TbParkGridPatrolRecordStsDTO implements Serializable {

    @ApiModelProperty("记录总数")
    private Integer totalNum;

    @ApiModelProperty("完成总数")
    private Integer completeNum;

    @ApiModelProperty("完成率")
    private BigDecimal completeRate;

}
