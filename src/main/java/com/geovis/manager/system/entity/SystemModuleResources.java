package com.geovis.manager.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.geovis.common.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 系统模块资源中间表
 * </p>
 *
 * @author 王响
 * @since 2021-01-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("system_module_resources")
@ApiModel(value = "SystemModuleResources对象", description = "系统模块资源中间表")
public class SystemModuleResources extends BaseEntity {

    @ApiModelProperty(value = "模块id")
    @NotNull(message = "模块id不能为空")
    private String moduleId;

    @ApiModelProperty(value = "顶级资源id")
    private String resourcesId;

}
