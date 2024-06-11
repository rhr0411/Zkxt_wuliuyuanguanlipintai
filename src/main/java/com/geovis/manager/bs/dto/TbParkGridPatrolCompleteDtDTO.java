package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 园区网格_完成巡检明细
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbParkGridPatrolCompleteDtDTO", description = "园区网格_完成巡检明细")
public class TbParkGridPatrolCompleteDtDTO implements Serializable {

    @ApiModelProperty("巡查记录内容")
    private String patrolContent;

    @ApiModelProperty("巡查记录附件ID列表")
    private List<String> fileIdList;

}
