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
 * 角色资源表
 * </p>
 *
 * @author 王响
 * @since 2022-03-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SystemRoleResources对象", description = "角色资源表")
@TableName("system_role_resources")
public class SystemRoleResources extends BaseEntity {

    @ApiModelProperty(value = "角色id")
    private String roleId;

    @ApiModelProperty(value = "资源id")
    private String resourcesId;

}
