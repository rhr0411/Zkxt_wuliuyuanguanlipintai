package com.geovis.manager.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 字典数据查询DTO
 * </p>
 *
 * @author 王响
 * @since 2021-11-09
 */

@Data
@Accessors(chain = true)
@ApiModel(value = "SystemDictDataQueryDTO", description = "字典数据查询DTO")
public class SystemDictDataQueryDTO implements Serializable {

    @ApiModelProperty(value = "字典id", required = true)
    private String dictId;
}
