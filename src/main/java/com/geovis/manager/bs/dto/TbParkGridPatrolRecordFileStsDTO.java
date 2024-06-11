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
 * 园区网格_巡检记录图片统计
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbParkGridPatrolRecordFileStsDTO", description = "园区网格_巡检记录图片统计")
public class TbParkGridPatrolRecordFileStsDTO implements Serializable {

    @ApiModelProperty("图片总数")
    private Integer imageNum = 0;

    @ApiModelProperty("视频总数")
    private Integer videoNum = 0;

}
