package com.geovis.manager.system.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * <p>
 * 资源信息查询DTO
 * </p>
 *
 * @author 王响
 * @since 2021-11-09
 */

@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SystemResourcesQueryDTO", description = "资源信息查询DTO")
public class SystemResourcesQueryDTO implements Serializable {

    @ApiModelProperty(value = "角色id")
    @Length(max = 64, message = "资源名称最长不能超过{64}个字符")
    private String roleId;

}
