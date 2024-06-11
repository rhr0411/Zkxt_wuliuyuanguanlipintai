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
 * 系统资源表
 * </p>
 *
 * @author 王响
 * @since 2022-03-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("system_resources")
@ApiModel(value = "SystemResources对象", description = "系统资源表")
public class SystemResources extends BaseEntity {

    @ApiModelProperty(value = "父级资源id")
    private String parentId;

    @ApiModelProperty(value = "资源名称")
    private String name;

    @ApiModelProperty(value = "授权标识")
    private String enName;

    @ApiModelProperty(value = "资源类型：1-目录， 2-菜单 ，3-按钮")
    private String type;

    @ApiModelProperty(value = "访问组件名称")
    private String componentName;

    @ApiModelProperty(value = "访问路径")
    private String url;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "菜单固定标签")
    private Boolean affix;

    @ApiModelProperty(value = "排序")
    private Integer idx;

    @ApiModelProperty(value = "备注")
    private String remark;

}
