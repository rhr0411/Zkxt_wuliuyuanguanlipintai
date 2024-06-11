package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 园区网格_巡检记录查询
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbParkGridPatrolRecordQueryDTO", description = "园区网格_巡检记录查询")
public class TbParkGridPatrolRecordQueryDTO implements Serializable {

    @ApiModelProperty("负责片区ID")
    private String gridId;

    @ApiModelProperty("巡查状态：1-已完成 0-未完成")
    private String patrolStatus;

}
