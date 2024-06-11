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
 * 园区网格_巡检记录_明细信息
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_park_grid_patrol_record_dt")
@ApiModel(value = "TbParkGridPatrolRecordDt", description = "园区网格_巡检记录_明细信息")
public class TbParkGridPatrolRecordDt extends BaseEntity {

    @ApiModelProperty("巡查记录ID")
    private String patrolRecordId;

    @ApiModelProperty("巡查记录内容")
    private String patrolContent;


}
