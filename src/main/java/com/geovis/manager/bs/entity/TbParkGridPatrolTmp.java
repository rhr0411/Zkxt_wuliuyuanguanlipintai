package com.geovis.manager.bs.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.geovis.common.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 园区网格_巡检模版
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_park_grid_patrol_tmp")
@ApiModel(value = "TbParkGridPatrolTmp对象", description = "园区网格_巡检模版")
public class TbParkGridPatrolTmp extends BaseEntity {

    @ApiModelProperty("队伍名称")
    private String teamName;

    @ApiModelProperty("负责片区ID")
    private String gridId;

    @ApiModelProperty("队长联系电话")
    private String teamLeaderPhone;

    @ApiModelProperty("巡查任务")
    private String patrolTask;

    @ApiModelProperty("巡查路线")
    private String patrolRoute;

    @ApiModelProperty("巡查频率")
    private String patrolFrequency;


}
