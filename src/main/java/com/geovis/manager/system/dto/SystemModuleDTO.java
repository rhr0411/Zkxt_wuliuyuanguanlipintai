package com.geovis.manager.system.dto;

import com.geovis.manager.system.entity.SystemModule;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;


/**
 * <p>
 * 系统模块DTO
 * </p>
 *
 * @author 王响
 * @since 2021-01-18
 */

@Data
@Accessors(chain = true)
@ApiModel(value = "SystemModuleDTO", description = "系统模块DTO")
public class SystemModuleDTO extends SystemModule {

    @ApiModelProperty(value = "资源list")
    private List<String> resourceIdList;
}
