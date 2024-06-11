package com.geovis.manager.system.dto;


import com.geovis.manager.system.entity.SystemResources;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * <p>
 * 资源信息DTO
 * </p>
 *
 * @author 王响
 * @since 2020-07-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SystemResourcesDTO对象", description = "资源信息DTO")
public class SystemResourcesDTO extends SystemResources {

    @ApiModelProperty(value = "角色List")
    private Set<String> roleList;
}
