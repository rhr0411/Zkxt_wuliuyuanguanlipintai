package com.geovis.manager.bs.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.geovis.common.core.entity.BaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 园区网格_巡检记录
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_park_grid_patrol_record")
@ApiModel(value = "TbParkGridPatrolRecord", description = "园区网格_巡检记录")
public class TbParkGridPatrolRecord extends BaseEntity {

    @ApiModelProperty("网格巡检模版ID")
    private String patrolTmpId;

    @ApiModelProperty("队伍名称")
    private String teamName;

    @ApiModelProperty("负责片区ID")
    private String gridId;

    @ApiModelProperty("队长Id")
    private String teamLeaderId;

    @ApiModelProperty("队长名称")
    private String teamLeaderName;

    @ApiModelProperty("队长联系电话")
    private String teamLeaderPhone;

    @ApiModelProperty("巡查任务")
    private String patrolTask;

    @ApiModelProperty("巡查计划路线")
    private String patrolPlanRoute;

    @ApiModelProperty("巡查计划开始时间")
    private LocalDateTime patrolPlanStartTime;

    @ApiModelProperty("巡查计划结束时间")
    private LocalDateTime patrolPlanEndTime;

    @ApiModelProperty("巡查实际开始时间")
    private LocalDateTime patrolActualStartTime;

    @ApiModelProperty("巡查实际结束时间")
    private LocalDateTime patrolActualEndTime;

    @ApiModelProperty("巡查状态：1-已完成 0-未完成")
    private String patrolStatus;

    @ApiModelProperty("巡查实际路线")
    private String patrolActualRoute;

    @ApiModelProperty("网格名称")
    private String gridName;

    @ApiModelProperty("记录数量")
    private Integer recordNum = 0;

}
