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
 * 园区网格_巡查模版_成员表
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_park_grid_patrol_tmp_user")
@ApiModel(value = "TbParkGridPatrolTmpUser", description = "园区网格_巡查模版_成员表")
public class TbParkGridPatrolTmpUser extends BaseEntity {

    @ApiModelProperty("巡查模版ID")
    private String patrolTmpId;

    @ApiModelProperty("成员ID")
    private String userId;

    @ApiModelProperty("成员类型：1-队长 0-成员")
    private String userType;


}
