package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 园区网格_完成巡检
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbParkGridPatrolCompleteDTO", description = "园区网格_完成巡检")
public class TbParkGridPatrolCompleteDTO implements Serializable {

    @ApiModelProperty("记录ID")
    @NotEmpty(message = "记录ID不能为空")
    private String id;

    @ApiModelProperty("巡查实际路线")
    @NotEmpty(message = "巡查实际路线不能为空")
    private String patrolActualRoute;

    @ApiModelProperty("巡查内容记录列表")
    @NotNull(message = "不能为空")
    private List<TbParkGridPatrolCompleteDtDTO> list;

}
