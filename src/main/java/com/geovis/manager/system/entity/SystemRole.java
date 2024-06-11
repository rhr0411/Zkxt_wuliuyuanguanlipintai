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
 * 角色表
 * </p>
 *
 * @author 王响
 * @since 2022-03-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SystemRole对象", description = "角色表")
@TableName("system_role")
public class SystemRole extends BaseEntity {

    @ApiModelProperty(value = "父角色id")
    private String parentId;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色名称(英文)")
    private String enName;

    @ApiModelProperty(value = "排序")
    private Integer idx;

    @ApiModelProperty(value = "备注")
    private String remark;

}
