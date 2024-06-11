package com.geovis.manager.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.geovis.common.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统模块
 * </p>
 *
 * @author 王响
 * @since 2021-01-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("system_module")
@ApiModel(value = "SystemModule对象", description = "系统模块")
public class SystemModule extends BaseEntity {

    @ApiModelProperty(value = "模块名称")
    private String name;

    @ApiModelProperty(value = "模块图标")
    private String icon;

    @ApiModelProperty(value = "数据状态（1-已经删除，0-未删除）")
    private String dataStatus;

    @ApiModelProperty(value = "排序")
    private Integer idx;

}
